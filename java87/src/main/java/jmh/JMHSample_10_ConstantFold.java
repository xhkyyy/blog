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

public class JMHSample_10_ConstantFold {


    /*

     * The flip side of dead-code elimination is constant-folding.

     *

     * If JVM realizes the result of the computation is the same no matter what,

     * it can cleverly optimize it. In our case, that means we can move the

     * computation outside of the internal JMH loop.

     *

     * This can be prevented by always reading the inputs from non-final

     * instance fields of @State objects, computing the result based on those

     * values, and follow the rules to prevent DCE. (DCE means 'Dead code elimination,')

     */


    // IDEs will say "Oh, you can convert this field to local variable". Don't. Trust. Them.

    // (While this is normally fine advice, it does not work in the context of measuring correctly.)

    private double x = Math.PI;


    // IDEs will probably also say "Look, it could be final". Don't. Trust. Them. Either.

    // (While this is normally fine advice, it does not work in the context of measuring correctly.)

    private final double wrongX = Math.PI;


    @Benchmark

    public double baseline() {

        // simply return the value, this is a baseline

        return Math.PI;

    }


    /**
     * 由于 Math.PI 是一个常量，所以，Math.log(Math.PI) 的计算结果是可以预测的，所以，编译器将会「消除」掉计算的过程，直接返回结果。
     *
     * @return
     */
    @Benchmark
    public double measureWrong_1() {

        // This is wrong: the source is predictable, and computation is foldable.

        return Math.log(Math.PI);

    }


    /**
     * 由于wrongX 是一个 final 常量，所以，Math.log(Math.PI) 的计算结果是可以预测的，所以，编译器将会「消除」掉计算的过程，直接返回结果。
     *
     * @return
     */
    @Benchmark
    public double measureWrong_2() {

        // This is wrong: the source is predictable, and computation is foldable.

        return Math.log(wrongX);

    }


    /**
     * x 是一个变量，所以是不可预测的变量，因此每次执行代码都将通过 Math.log 计算得出。
     *
     * @return
     */
    @Benchmark
    public double measureRight() {

        // This is correct: the source is not predictable.

        return Math.log(x);

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You can see the unrealistically fast calculation in with measureWrong_*(),

     * while realistic measurement with measureRight().

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_10 -i 5 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_10_ConstantFold.class.getSimpleName())

                .forks(1)

                .build();


        new Runner(opt).run();

    }


}