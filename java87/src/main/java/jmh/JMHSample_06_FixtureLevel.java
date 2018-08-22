package jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


/**
 * 注意的一点是： Fixture methods 方法的执行开销不会作为总的性能 metric，被计算在内。
 * 所以，Fixture methods 方法中可以做一些重量级的工作。
 */
@State(Scope.Thread)
public class JMHSample_06_FixtureLevel {


    double x;


    /*

     * Fixture methods have different levels to control when they should be run.

     * There are at least three Levels available to the user. These are, from

     * top to bottom:

     *

     * Level.Trial: before or after the entire benchmark run (the sequence of iterations) （benchmark 开始前、后）

     * Level.Iteration: before or after the benchmark iteration (the sequence of invocations) （benchmark 每一次性能测试迭代前、后）

     * Level.Invocation; before or after the benchmark method invocation (WARNING: read the Javadoc before using) benchmark 方法每一次被调用前、后）

     *

     * Time spent in fixture methods does not count into the performance

     * metrics, so you can use this to do some heavy-lifting.

     */


    @TearDown(Level.Iteration)

    public void check() {
        System.out.println("check()!!");
        assert x > Math.PI : "Nothing changed?";

    }


    @Benchmark

    public void measureRight() {

        System.out.println("measureRight()!!");
        x++;

    }


    /*@Benchmark

    public void measureWrong() {

        double x = 0;

        x++;

    }*/


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You can see measureRight() yields the result, and measureWrong() fires

     * the assert at the end of first iteration! This will not generate the results

     * for measureWrong(). You can also prevent JMH for proceeding further by

     * requiring "fail on error".

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -ea -jar target/benchmarks.jar JMHSample_06 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     *    You can optionally supply -foe to fail the complete run.

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_06_FixtureLevel.class.getSimpleName())

                .forks(1)

                .jvmArgs("-ea")

                .shouldFailOnError(false) // switch to "true" to fail the complete run

                .build();


        new Runner(opt).run();

    }


}