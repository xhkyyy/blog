package best_practice.remainder;


import jmh.JMHSample_01_HelloWorld;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 测试余数的计算方式
 *
 * Benchmark             Mode  Cnt   Score   Error  Units
 * JMHRemainder.testBit  avgt    5   9.229 ± 2.798  ns/op
 * JMHRemainder.testExp  avgt    5  11.149 ± 0.424  ns/op
 */

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
public class JMHRemainder {

    @State(Scope.Thread)
    public static class ThreadState {
        Random random = new Random();
    }

    @Benchmark
    public void testExp(Blackhole blackhole, ThreadState threadState) {
        blackhole.consume(threadState.random.nextInt() % 8);
    }

    @Benchmark
    public void testBit(Blackhole blackhole, ThreadState threadState) {
        blackhole.consume(threadState.random.nextInt() & (8 - 1));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()

                .include(JMHRemainder.class.getSimpleName())

                .build();


        new Runner(opt).run();
    }

}
