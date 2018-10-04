package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.LinkedList;
import java.util.List;

@State(Scope.Thread)
public class JMHSample_26_BatchSize {

    /*
     * Sometimes you need to evaluate operation which doesn't have
     * the steady state. The cost of a benchmarked operation may
     * significantly vary from invocation to invocation.
     *
     * In this case, using the timed measurements is not a good idea,
     * and the only acceptable benchmark mode is a single shot. On the
     * other hand, the operation may be too small for reliable single
     * shot measurement.
     *
     * We can use "batch size" parameter to describe the number of
     * benchmark calls to do per one invocation without looping the method
     * manually and protect from problems described in JMHSample_11_Loops.
     */

    /*
     * Suppose we want to measure insertion in the middle of the list.
     */

    List<String> list = new LinkedList<>();

    @Benchmark
    @Warmup(iterations = 5, time = 1)
    @Measurement(iterations = 5, time = 1)
    @BenchmarkMode(Mode.AverageTime)
    public List<String> measureWrong_1() {
        list.add(list.size() / 2, "something");
        return list;
    }

    @Benchmark
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 5, time = 5)
    @BenchmarkMode(Mode.AverageTime)
    public List<String> measureWrong_5() {
        list.add(list.size() / 2, "something");
        return list;
    }

    /*
     * This is what you do with JMH.
     *
     * batchSize: 每次操作调用的次数（不要试图自己在方法调用中使用循环，这是非常错误的，见：JMHSample_11_Loops.java）
     * Mode.SingleShotTime: 在这里代表的含义是一次批处理的总调用时间
     */
    @Benchmark
    @Warmup(iterations = 5, batchSize = 5000)
    @Measurement(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<String> measureRight() {
        list.add(list.size() / 2, "something");
        return list;
    }

    @Benchmark
    @Warmup(iterations = 5, batchSize = 10000)
    @Measurement(iterations = 5, batchSize = 10000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<String> measureRight_2() {
        list.add(list.size() / 2, "something");
        return list;
    }

    @Setup(Level.Iteration)
    public void setup() {
        list.clear();
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You can see completely different results for measureWrong_1 and measureWrong_5; this
     * is because the workload has no steady state. The result of the workload is dependent
     * on the measurement time. measureRight does not have this drawback, because it measures
     * the N invocations of the test method and measures it's time.
     *
     * We measure batch of 5000 invocations and consider the batch as the single operation.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_26 -f 1
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_26_BatchSize.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}