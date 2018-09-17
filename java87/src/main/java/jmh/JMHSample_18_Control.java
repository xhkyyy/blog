package jmh;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.Group;

import org.openjdk.jmh.annotations.Scope;

import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.infra.Control;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.atomic.AtomicBoolean;


@State(Scope.Group)

public class JMHSample_18_Control {


    /*

     * Sometimes you need the tap into the harness mind to get the info

     * on the transition change. For this, we have the experimental state object,

     * Control, which is updated by JMH as we go.

     */


    /*

     * In this example, we want to estimate the ping-pong speed for the simple

     * AtomicBoolean. Unfortunately, doing that in naive manner will livelock

     * one of the threads, because the executions of ping/pong are not paired

     * perfectly. We need the escape hatch to terminate the loop if threads

     * are about to leave the measurement.

     */


    public final AtomicBoolean flag = new AtomicBoolean();


    @Benchmark

    @Group("pingpong")

    public void ping(Control cnt) {

        while (!cnt.stopMeasurement && !flag.compareAndSet(false, true)) {

            // this body is intentionally left blank

        }

    }


    @Benchmark

    @Group("pingpong")

    public void pong(Control cnt) {

        while (!cnt.stopMeasurement && !flag.compareAndSet(true, false)) {

            // this body is intentionally left blank

        }

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_18 -t 2 -f 1

     *    (we requested 2 threads and single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_18_Control.class.getSimpleName())

                .threads(2)

                .forks(1)

                .build();


        new Runner(opt).run();

    }


}

