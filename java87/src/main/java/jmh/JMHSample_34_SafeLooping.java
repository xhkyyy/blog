package jmh;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;

import org.openjdk.jmh.annotations.CompilerControl;

import org.openjdk.jmh.annotations.Fork;

import org.openjdk.jmh.annotations.Measurement;

import org.openjdk.jmh.annotations.Mode;

import org.openjdk.jmh.annotations.OutputTimeUnit;

import org.openjdk.jmh.annotations.Param;

import org.openjdk.jmh.annotations.Scope;

import org.openjdk.jmh.annotations.Setup;

import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.annotations.Warmup;

import org.openjdk.jmh.infra.Blackhole;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.TimeUnit;


/**

 执行结果如下：

 Benchmark                                (size)  Mode  Cnt     Score     Error  Units
 JMHSample_34_SafeLooping.measureRight_1       1  avgt   15     6.140 ±   0.289  ns/op
 JMHSample_34_SafeLooping.measureRight_1      10  avgt   15    34.440 ±   2.617  ns/op
 JMHSample_34_SafeLooping.measureRight_1     100  avgt   15   334.637 ±  21.104  ns/op
 JMHSample_34_SafeLooping.measureRight_1    1000  avgt   15  2986.591 ± 112.786  ns/op
 JMHSample_34_SafeLooping.measureRight_2       1  avgt   15     5.015 ±   0.206  ns/op
 JMHSample_34_SafeLooping.measureRight_2      10  avgt   15    25.606 ±   1.018  ns/op
 JMHSample_34_SafeLooping.measureRight_2     100  avgt   15   250.932 ±   5.229  ns/op
 JMHSample_34_SafeLooping.measureRight_2    1000  avgt   15  2403.857 ± 108.885  ns/op
 JMHSample_34_SafeLooping.measureWrong_1       1  avgt   15     4.123 ±   0.151  ns/op
 JMHSample_34_SafeLooping.measureWrong_1      10  avgt   15     5.495 ±   0.153  ns/op
 JMHSample_34_SafeLooping.measureWrong_1     100  avgt   15    10.271 ±   1.956  ns/op
 JMHSample_34_SafeLooping.measureWrong_1    1000  avgt   15    46.025 ±   1.805  ns/op
 JMHSample_34_SafeLooping.measureWrong_2       1  avgt   15     4.560 ±   0.779  ns/op
 JMHSample_34_SafeLooping.measureWrong_2      10  avgt   15     8.647 ±   1.084  ns/op
 JMHSample_34_SafeLooping.measureWrong_2     100  avgt   15    44.450 ±   4.955  ns/op
 JMHSample_34_SafeLooping.measureWrong_2    1000  avgt   15   387.504 ±  28.280  ns/op

 */

@State(Scope.Thread)

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

@Fork(3)

@BenchmarkMode(Mode.AverageTime)

@OutputTimeUnit(TimeUnit.NANOSECONDS)

public class JMHSample_34_SafeLooping {


    /*

     * JMHSample_11_Loops warns about the dangers of using loops in @Benchmark methods.

     * Sometimes, however, one needs to traverse through several elements in a dataset.

     * This is hard to do without loops, and therefore we need to devise a scheme for

     * safe looping.

     */


    /*

     * Suppose we want to measure how much it takes to execute work() with different

     * arguments. This mimics a frequent use case when multiple instances with the same

     * implementation, but different data, is measured.

     */


    static final int BASE = 42;


    static int work(int x) {

        return BASE + x;

    }


    /*

     * Every benchmark requires control. We do a trivial control for our benchmarks

     * by checking the benchmark costs are growing linearly with increased task size.

     * If it doesn't, then something wrong is happening.

     */


    @Param({"1", "10", "100", "1000"})

    int size;


    int[] xs;


    @Setup

    public void setup() {

        xs = new int[size];

        for (int c = 0; c < size; c++) {

            xs[c] = c;

        }

    }


    /*

     * First, the obviously wrong way: "saving" the result into a local variable would not

     * work. A sufficiently smart compiler will inline work(), and figure out only the last

     * work() call needs to be evaluated. Indeed, if you run it with varying $size, the score

     * will stay the same!

     */


    @Benchmark

    public int measureWrong_1() {

        int acc = 0;

        for (int x : xs) {

            acc = work(x);

        }

        return acc;

    }


    /*

     * Second, another wrong way: "accumulating" the result into a local variable. While

     * it would force the computation of each work() method, there are software pipelining

     * effects in action, that can merge the operations between two otherwise distinct work()

     * bodies. This will obliterate the benchmark setup.

     *

     * In this example, HotSpot does the unrolled loop, merges the $BASE operands into a single

     * addition to $acc, and then does a bunch of very tight stores of $x-s. The final performance

     * depends on how much of the loop unrolling happened *and* how much data is available to make

     * the large strides.

     */


    @Benchmark

    public int measureWrong_2() {

        int acc = 0;

        for (int x : xs) {

            acc += work(x);

        }

        return acc;

    }


    /*

        正确使用 for 循环的例子

     * Now, let's see how to measure these things properly. A very straight-forward way to

     * break the merging is to sink each result to Blackhole. This will force runtime to compute

     * every work() call in full. (We would normally like to care about several concurrent work()

     * computations at once, but the memory effects from Blackhole.consume() prevent those optimization

     * on most runtimes).

     */


    @Benchmark

    public void measureRight_1(Blackhole bh) {

        for (int x : xs) {

            bh.consume(work(x));

        }

    }


    /*

     * DANGEROUS AREA, PLEASE READ THE DESCRIPTION BELOW.

     *

     * Sometimes, the cost of sinking the value into a Blackhole is dominating the nano-benchmark score.

     * In these cases, one may try to do a make-shift "sinker" with non-inlineable method. This trick is

     * *very* VM-specific, and can only be used if you are verifying the generated code (that's a good

     * strategy when dealing with nano-benchmarks anyway).

     *

     * You SHOULD NOT use this trick in most cases. Apply only where needed.

     */


    @Benchmark

    public void measureRight_2() {

        for (int x : xs) {

            sink(work(x));

        }

    }


    @CompilerControl(CompilerControl.Mode.DONT_INLINE)

    public static void sink(int v) {

        // IT IS VERY IMPORTANT TO MATCH THE SIGNATURE TO AVOID AUTOBOXING.

        // The method intentionally does nothing.

    }



    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You might notice measureWrong_1 does not depend on $size, measureWrong_2 has troubles with

     * linearity, and otherwise much faster than both measureRight_*. You can also see measureRight_2

     * is marginally faster than measureRight_1.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_34

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_34_SafeLooping.class.getSimpleName())

                .forks(3)

                .build();


        new Runner(opt).run();

    }


}