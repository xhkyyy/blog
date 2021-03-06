package jmh;

import org.openjdk.jmh.annotations.Benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;

import org.openjdk.jmh.annotations.Fork;

import org.openjdk.jmh.annotations.Group;

import org.openjdk.jmh.annotations.Measurement;

import org.openjdk.jmh.annotations.Mode;

import org.openjdk.jmh.annotations.OutputTimeUnit;

import org.openjdk.jmh.annotations.Scope;

import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.annotations.Warmup;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


import java.util.concurrent.TimeUnit;


/**
 * 一个缓存行一般为 64 字节，java 对象头在 32 位系统中占用 8 字节，在 64 位系统中，如果不开启对象头压缩将占用 16 字节。
 * 现在 cpu 一般以 64 个字节作为一个缓存行，所以，我们只要保证我们对象的字段和字段之间（或者一个对象字段）占用大于等于 64 字节即可。
 *
 * 使用 @sun.misc.Contended (加在字段上) 记得在 java 启动参数中加入 -XX:-RestrictContended
 *
 * https://www.cnblogs.com/Binhua-Liu/p/5623089.html
 */

@BenchmarkMode(Mode.Throughput)

@OutputTimeUnit(TimeUnit.MICROSECONDS)

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

@Fork(5)

public class JMHSample_22_FalseSharing {


    /*

     * One of the unusual thing that can bite you back is false sharing.

     * If two threads access (and possibly modify) the adjacent values

     * in memory, chances are, they are modifying the values on the same

     * cache line. This can yield significant (artificial) slowdowns.

     *

     * JMH helps you to alleviate this: @States are automatically padded.

     * This padding does not extend to the State internals though,

     * as we will see in this example. You have to take care of this on

     * your own.

     */


    /*

     * Suppose we have two threads:

     *   a) innocuous reader which blindly reads its own field

     *   b) furious writer which updates its own field

     */


    /*

     * BASELINE EXPERIMENT:

     * Because of the false sharing, both reader and writer will experience

     * penalties.

     */


    @State(Scope.Group)

    public static class StateBaseline {

        int readOnly;

        int writeOnly;

    }


    @Benchmark

    @Group("baseline")

    public int reader(StateBaseline s) {

        return s.readOnly;

    }


    @Benchmark

    @Group("baseline")

    public void writer(StateBaseline s) {

        s.writeOnly++;

    }


    /*

     * APPROACH 1: PADDING

     *

     * We can try to alleviate some of the effects with padding.

     * This is not versatile because JVMs can freely rearrange the

     * field order.

     */


    @State(Scope.Group)

    public static class StatePadded {

        int readOnly;

        int p01, p02, p03, p04, p05, p06, p07, p08;

        int p11, p12, p13, p14, p15, p16, p17, p18;

        int writeOnly;

        int q01, q02, q03, q04, q05, q06, q07, q08;

        int q11, q12, q13, q14, q15, q16, q17, q18;

    }


    @Benchmark

    @Group("padded")

    public int reader(StatePadded s) {

        return s.readOnly;

    }


    @Benchmark

    @Group("padded")

    public void writer(StatePadded s) {

        s.writeOnly++;

    }


    /*

     * APPROACH 2: CLASS HIERARCHY TRICK

     *

     * We can alleviate false sharing with this convoluted hierarchy trick,

     * using the fact that superclass fields are usually laid out first.

     * In this construction, the protected field will be squashed between

     * paddings.

     */


    public static class StateHierarchy_1 {

        int readOnly;

    }


    public static class StateHierarchy_2 extends StateHierarchy_1 {

        int p01, p02, p03, p04, p05, p06, p07, p08;

        int p11, p12, p13, p14, p15, p16, p17, p18;

    }


    public static class StateHierarchy_3 extends StateHierarchy_2 {

        int writeOnly;

    }


    public static class StateHierarchy_4 extends StateHierarchy_3 {

        int q01, q02, q03, q04, q05, q06, q07, q08;

        int q11, q12, q13, q14, q15, q16, q17, q18;

    }


    @State(Scope.Group)

    public static class StateHierarchy extends StateHierarchy_4 {

    }


    @Benchmark

    @Group("hierarchy")

    public int reader(StateHierarchy s) {

        return s.readOnly;

    }


    @Benchmark

    @Group("hierarchy")

    public void writer(StateHierarchy s) {

        s.writeOnly++;

    }


    /*

     * APPROACH 3: ARRAY TRICK

     *

     * This trick relies on the contiguous allocation of an array.

     * Instead of placing the fields in the class, we mangle them

     * into the array at very sparse offsets.

     */


    @State(Scope.Group)

    public static class StateArray {

        int[] arr = new int[128];

    }


    @Benchmark

    @Group("sparse")

    public int reader(StateArray s) {

        return s.arr[0];

    }


    @Benchmark

    @Group("sparse")

    public void writer(StateArray s) {

        s.arr[64]++;

    }


    /*

     * APPROACH 4:

     *

     * @Contended (since JDK 8):

     *  Uncomment the annotation if building with JDK 8.

     *  Remember to flip -XX:-RestrictContended to enable.

     */


    /*@State(Scope.Group)
    public static class StateContended {

        //@sun.misc.Contended

        int readOnly;


       @sun.misc.Contended

        int writeOnly;

    }*/


    @Benchmark

    @Group("contended")

    public int reader(StateContended s) {

        return s.readOnly;

    }


    @Benchmark

    @Group("contended")

    public void writer(StateContended s) {

        s.writeOnly++;

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * Note the slowdowns.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -jar target/benchmarks.jar JMHSample_22 -t $CPU

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_22_FalseSharing.class.getSimpleName())

                .threads(Runtime.getRuntime().availableProcessors())

                .build();


        new Runner(opt).run();

    }


}
