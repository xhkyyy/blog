package jmh;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.Scope;

import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


/*

 * Fortunately, in many cases you just need a single state object.

 * In that case, we can mark the benchmark instance itself to be

 * the @State. Then, we can reference its own fields as any

 * Java program does.

 */

@State(Scope.Thread) // 如果不使用这个注解，那么变量 x 的存在，将导致无法运行此程序。

public class JMHSample_04_DefaultState {


    double x = Math.PI;


    @Benchmark

    public void measure() {

        x++;

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You can see the benchmark runs as usual.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_04 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_04_DefaultState.class.getSimpleName())

                .forks(1)

                .build();


        new Runner(opt).run();

    }


}