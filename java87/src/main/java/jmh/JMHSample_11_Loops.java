package jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.TimeUnit;

/**
 * 这是一个错误的示例，不要试图在方法体内使用循环。
 * <p>
 * 本次示例实际的执行结果如下：
 * <p>
 * Benchmark                               Mode  Cnt  Score   Error  Units
 * JMHSample_11_Loops.measureRight         avgt    5  3.973 ± 2.315  ns/op
 * JMHSample_11_Loops.measureWrong_1       avgt    5  5.430 ± 7.728  ns/op
 * JMHSample_11_Loops.measureWrong_10      avgt    5  0.356 ± 0.017  ns/op
 * JMHSample_11_Loops.measureWrong_100     avgt    5  0.051 ± 0.017  ns/op
 * JMHSample_11_Loops.measureWrong_1000    avgt    5  0.051 ± 0.020  ns/op
 * JMHSample_11_Loops.measureWrong_10000   avgt    5  0.028 ± 0.020  ns/op
 * JMHSample_11_Loops.measureWrong_100000  avgt    5  0.021 ± 0.002  ns/op
 */

@State(Scope.Thread)

@BenchmarkMode(Mode.AverageTime)

@OutputTimeUnit(TimeUnit.NANOSECONDS)

public class JMHSample_11_Loops {


    /*

     * It would be tempting for users to do loops within the benchmarked method.

     * (This is the bad thing Caliper taught everyone). These tests explain why

     * this is a bad idea.

     *

     * Looping is done in the hope of minimizing the overhead of calling the

     * test method, by doing the operations inside the loop instead of inside

     * the method call. Don't buy this argument; you will see there is more

     * magic happening when we allow optimizers to merge the loop iterations.

     */


    /*

     * Suppose we want to measure how much it takes to sum two integers:

     */


    int x = 1;

    int y = 2;


    /*

     * This is what you do with JMH.

     */


    @Benchmark

    public int measureRight() {

        return (x + y);

    }


    /*

     * The following tests emulate the naive looping.

     * This is the Caliper-style benchmark.

     */

    private int reps(int reps) {

        int s = 0;

        for (int i = 0; i < reps; i++) {

            s += (x + y);

        }

        return s;

    }


    /*

     * We would like to measure this with different repetitions count.

     * Special annotation is used to get the individual operation cost.

     */


    @Benchmark

    @OperationsPerInvocation(1)

    public int measureWrong_1() {

        return reps(1);

    }


    @Benchmark

    @OperationsPerInvocation(10)

    public int measureWrong_10() {

        return reps(10);

    }


    @Benchmark

    @OperationsPerInvocation(100)

    public int measureWrong_100() {

        return reps(100);

    }


    @Benchmark

    @OperationsPerInvocation(1000)

    public int measureWrong_1000() {

        return reps(1000);

    }


    @Benchmark

    @OperationsPerInvocation(10000)

    public int measureWrong_10000() {

        return reps(10000);

    }


    @Benchmark

    @OperationsPerInvocation(100000)

    public int measureWrong_100000() {

        return reps(100000);

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You might notice the larger the repetitions count, the lower the "perceived"

     * cost of the operation being measured. Up to the point we do each addition with 1/20 ns,

     * well beyond what hardware can actually do.

     *

     * This happens because the loop is heavily unrolled/pipelined, and the operation

     * to be measured is hoisted from the loop. Morale: don't overuse loops, rely on JMH

     * to get the measurement right.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_11 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_11_Loops.class.getSimpleName())

                .forks(1)

                .build();


        new Runner(opt).run();

    }


}