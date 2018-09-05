package jmh;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;

import org.openjdk.jmh.annotations.Fork;

import org.openjdk.jmh.annotations.Mode;

import org.openjdk.jmh.annotations.OutputTimeUnit;

import org.openjdk.jmh.annotations.Scope;

import org.openjdk.jmh.annotations.Setup;

import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.TimeUnit;


@State(Scope.Thread)

@BenchmarkMode(Mode.AverageTime)

@OutputTimeUnit(TimeUnit.MILLISECONDS)

public class JMHSample_13_RunToRun {


    /*

     * Forking also allows to estimate run-to-run variance.

     *

     * JVMs are complex systems, and the non-determinism is inherent for them.

     * This requires us to always account the run-to-run variance as the one

     * of the effects in our experiments.

     *

     * Luckily, forking aggregates the results across several JVM launches.

     */


    /*

     * In order to introduce readily measurable run-to-run variance, we build

     * the workload which performance differs from run to run. Note that many workloads

     * will have the similar behavior, but we do that artificially to make a point.

     */


    @State(Scope.Thread)

    public static class SleepyState {

        public long sleepTime;


        @Setup

        public void setup() {

            sleepTime = (long) (Math.random() * 1000);

        }

    }


    /*

     * Now, we will run this different number of times.

     */


    @Benchmark

    @Fork(1)

    public void baseline(SleepyState s) throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(s.sleepTime);

    }


    @Benchmark

    @Fork(5)

    public void fork_1(SleepyState s) throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(s.sleepTime);

    }


    @Benchmark

    @Fork(20)

    public void fork_2(SleepyState s) throws InterruptedException {

        TimeUnit.MILLISECONDS.sleep(s.sleepTime);

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * Note the baseline is random within [0..1000] msec; and both forked runs

     * are estimating the average 500 msec with some confidence.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_13 -wi 0 -i 3

     *    (we requested no warmup, 3 measurement iterations; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_13_RunToRun.class.getSimpleName())

                .warmupIterations(0)

                .measurementIterations(3)

                .build();


        new Runner(opt).run();

    }


}

