package jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;

import org.openjdk.jmh.runner.RunnerException;

import org.openjdk.jmh.runner.options.Options;

import org.openjdk.jmh.runner.options.OptionsBuilder;


@State(Scope.Thread)

public class JMHSample_05_StateFixtures {


    double x;


    /*

     * Since @State objects are kept around during the lifetime of the

     * benchmark, it helps to have the methods which do state housekeeping.

     * These are usual fixture methods, you are probably familiar with them from

     * JUnit and TestNG.

     *

     * Fixture methods make sense only on @State objects, and JMH will fail to

     * compile the test otherwise.

     *

     * As with the State, fixture methods are only called by those benchmark

     * threads which are using the state. That means you can operate in the

     * thread-local context, and (not) use synchronization as if you are

     * executing in the context of benchmark thread.

     *

     * Note: fixture methods can also work with static fields, although the

     * semantics of these operations fall back out of State scope, and obey

     * usual Java rules (i.e. one static field per class).

     */


    /*

     * Ok, let's prepare our benchmark:

     */


    @Setup

    public void prepare() {

        x = Math.PI;

    }


    /*

     * And, check the benchmark went fine afterwards:

       Benchmark 运行结束后，将检查 x 值是否满足 x > Math.PI
     */
    @TearDown
    public void check() {
        System.out.println("check()!!");
        assert x > Math.PI : "Nothing changed?";

    }


    /*

     * This method obviously does the right thing, incrementing the field x

     * in the benchmark state. check() will never fail this way, because

     * we are always guaranteed to have at least one benchmark call.

     */

    @Benchmark
    public void measureRight() {
        System.out.println("measureRight()!!");
        x++;

    }



    /*

     * This method, however, will fail the check(), because we deliberately

     * have the "typo", and increment only the local variable. This should

     * not pass the check, and JMH will fail the run.

        这个方法将会抛错，因为它没有改变 x 的值，因为这个方法自己定义了一个局部变量。最终 check() 将抛出异常。
     */

    @Benchmark

    public void measureWrong() {

        double x = 0;

        x++;

    }


    /*

     * ============================== HOW TO RUN THIS TEST: ====================================

     *

     * You can see measureRight() yields the result, and measureWrong() fires

     * the assert at the end of the run.

     *

     * You can run this test:

     *

     * a) Via the command line:

     *    $ mvn clean install

     *    $ java -ea -jar target/benchmarks.jar JMHSample_05 -f 1

     *    (we requested single fork; there are also other options, see -h)

     *

     * b) Via the Java API:

     *    (see the JMH homepage for possible caveats when running from IDE:

     *      http://openjdk.java.net/projects/code-tools/jmh/)

     */


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHSample_05_StateFixtures.class.getSimpleName())

                .forks(1)

                /** Enable assertions. Assertions are disabled by default. */
                .jvmArgs("-ea")

                .build();


        new Runner(opt).run();

    }


}