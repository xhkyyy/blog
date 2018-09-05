package jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.TimeUnit;


@State(Scope.Thread)

@BenchmarkMode(Mode.AverageTime)

@OutputTimeUnit(TimeUnit.NANOSECONDS)

public class JMHSample_12_Forking {


    /*

     * JVMs are notoriously good at profile-guided optimizations. This is bad

     * for benchmarks, because different tests can mix their profiles together,

     * and then render the "uniformly bad" code for every test. Forking (running

     * in a separate process) each test can help to evade this issue.

     *

     * JMH will fork the tests by default.

     */


    /*

     * Suppose we have this simple counter interface, and two implementations.

     * Even though those are semantically the same, from the JVM standpoint,

     * those are distinct classes.

     */


    public interface Counter {

        int inc();

    }


    public class Counter1 implements Counter {

        private int x;


        @Override

        public int inc() {

            return x++;

        }

    }


    public class Counter2 implements Counter {

        private int x;


        @Override

        public int inc() {

            return x++;

        }

    }


    /*

     * And this is how we measure it.

     * Note this is susceptible for same issue with loops we mention in previous examples.

     */


    public int measure(Counter c) {

        int s = 0;

        for (int i = 0; i < 10; i++) {

            s += c.inc();

        }

        return s;

    }


    /*

     * These are two counters.

     */

    Counter c1 = new Counter1();

    Counter c2 = new Counter2();


    /*

     * We first measure the Counter1 alone...

     * Fork(0) helps to run in the same JVM. （注意这句话）

     */


    @Benchmark

    @Fork(0)

    public int measure_1_c1() {

        return measure(c1);

    }


    /*

     * Then Counter2...

     */


    @Benchmark

    @Fork(0)

    public int measure_2_c2() {

        return measure(c2);

    }


    /*

     * Then Counter1 again...

     */


    @Benchmark

    @Fork(0)

    public int measure_3_c1_again() {

        return measure(c1);

    }


    /*

     * These two tests have explicit @Fork annotation.

     * JMH takes this annotation as the request to run the test in the forked JVM.

     * It's even simpler to force this behavior for all the tests via the command

     * line option "-f". The forking is default, but we still use the annotation

     * for the consistency.

     *

     * This is the test for Counter1.

     */


    @Benchmark

    @Fork(1)

    public int measure_4_forked_c1() {

        return measure(c1);

    }


    /*

     * ...and this is the test for Counter2.

     */


    @Benchmark

    @Fork(1)

    public int measure_5_forked_c2() {

        return measure(c2);

    }

    @Benchmark

    @Fork(1)

    public int measure_5_forked_c1_again() {

        return measure(c1);

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * Note that C1 is faster, C2 is slower, but the C1 is slow again! This is because

     * the profiles for C1 and C2 had merged together. Notice how flawless the measurement

     * is for forked runs.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_12

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_12_Forking.class.getSimpleName())

                .build();


        new Runner(opt).run();

    }


}