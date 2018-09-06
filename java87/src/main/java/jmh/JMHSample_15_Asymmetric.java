package jmh;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;

import org.openjdk.jmh.annotations.Group;

import org.openjdk.jmh.annotations.GroupThreads;

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

import java.util.concurrent.atomic.AtomicInteger;


// 注意 当前使用的是 Group state
@State(Scope.Group)

@BenchmarkMode(Mode.AverageTime)

@OutputTimeUnit(TimeUnit.NANOSECONDS)

public class JMHSample_15_Asymmetric {


    /*

     * So far all the tests were symmetric: the same code was executed in all the threads.

     * At times, you need the asymmetric test. JMH provides this with the notion of @Group,

     * which can bind several methods together, and all the threads are distributed among

     * the test methods.

     *

     * Each execution group contains of one or more threads. Each thread within a particular

     * execution group executes one of @Group-annotated @Benchmark methods. Multiple execution

     * groups may participate in the run. The total thread count in the run is rounded to the

     * execution group size, which will only allow the full execution groups.

     *

     * Note that two state scopes: Scope.Benchmark and Scope.Thread are not covering all

     * the use cases here -- you either share everything in the state, or share nothing.

     * To break this, we have the middle ground Scope.Group, which marks the state to be

     * shared within the execution group, but not among the execution groups.

     *

     * Putting this all together, the example below means:

     *  a) define the execution group "g", with 3 threads executing inc(), and 1 thread

     *     executing get(), 4 threads per group in total;

     *  b) if we run this test case with 4 threads, then we will have a single execution

     *     group. Generally, running with 4*N threads will create N execution groups, etc.;

     *  c) each execution group has one @State instance to share: that is, execution groups

     *     share the counter within the group, but not across the groups.

     */


    private AtomicInteger counter;


    @Setup

    public void up() {

        counter = new AtomicInteger();

    }


    @Benchmark

    @Group("g")

    @GroupThreads(3)

    public int inc() {

        return counter.incrementAndGet();

    }


    @Benchmark

    @Group("g")

    @GroupThreads(1)

    public int get() {

        return counter.get();

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You will have the distinct metrics for inc() and get() from this run.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_15 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_15_Asymmetric.class.getSimpleName())

                .forks(1)
                .threads(12)

                .build();


        new Runner(opt).run();

    }


}