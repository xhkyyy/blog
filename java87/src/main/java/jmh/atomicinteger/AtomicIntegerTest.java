package jmh.atomicinteger;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class AtomicIntegerTest {

    // Benchmark             Mode  Cnt    Score    Error  Units
    // AtomicIntegerTest.V1  avgt    5  242.881 ±  3.360  ns/op
    // AtomicIntegerTest.V2  avgt    5  200.694 ± 10.954  ns/op

    private static final int MAX = 10;

    private AtomicIntegerRoundRobinV1 roundRobinV1 = new AtomicIntegerRoundRobinV1(MAX);

    private AtomicIntegerRoundRobinV2 roundRobinV2 = new AtomicIntegerRoundRobinV2(MAX);

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Threads(value = 3)
    public void V1() {
        int val = roundRobinV1.get();
        if (val >= MAX) {
            throw new IllegalStateException("V1 Error!");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Threads(value = 3)
    public void V2() {
        int val = roundRobinV2.get();
        if (val >= MAX) {
            throw new IllegalStateException("V2 Error!");
        }
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(AtomicIntegerTest.class.getSimpleName())

                .forks(1)

                .build();


        new Runner(opt).run();

    }
}
