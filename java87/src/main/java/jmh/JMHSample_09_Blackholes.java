package jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.infra.Blackhole;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)

@OutputTimeUnit(TimeUnit.NANOSECONDS)

@State(Scope.Thread)

public class JMHSample_09_Blackholes {


    /*

     * Should your benchmark require returning multiple results, you have to

     * consider two options (detailed below).

     *

     * NOTE: If you are only producing a single result, it is more readable to

     * use the implicit return, as in JMHSample_08_DeadCode. Do not make your benchmark

     * code less readable with explicit Blackholes!

     */


    double x1 = Math.PI;

    double x2 = Math.PI * 2;


    /*

     * Baseline measurement: how much single Math.log costs.

     */


    @Benchmark

    public double baseline() {

        return Math.log(x1);

    }


    /*

     * While the Math.log(x2) computation is intact, Math.log(x1)

     * is redundant and optimized out.

     */


    @Benchmark

    public double measureWrong() {

        // 这个是可以被 jvm 优化抹掉这个语句的执行
        Math.log(x1);

        // 由于有返回值，这个语句不会被优化掉，而是可以完整调执行完毕，并将结果返回。
        return Math.log(x2);

    }


    /*

     * This demonstrates Option A:

     *

     * Merge multiple results into one and return it.

     * This is OK when is computation is relatively heavyweight, and merging

     * the results does not offset the results much.

     */


    @Benchmark

    public double measureRight_1() {

        return Math.log(x1) + Math.log(x2);

    }


    /*

       使用 Blackhole 消化调结果，防止代码被优化抹掉
     * This demonstrates Option B:

     *

     * Use explicit Blackhole objects, and sink the values there.

     * (Background: Blackhole is just another @State object, bundled with JMH).

     */


    @Benchmark

    public void measureRight_2(Blackhole bh) {

        bh.consume(Math.log(x1));

        bh.consume(Math.log(x2));

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You will see measureWrong() running on-par with baseline().

     * Both measureRight() are measuring twice the baseline, so the logs are intact.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_09 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_09_Blackholes.class.getSimpleName())

                .forks(1)

                .build();


        new Runner(opt).run();

    }


}
