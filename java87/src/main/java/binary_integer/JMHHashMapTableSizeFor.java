package binary_integer;

import jmh.JMHSample_02_BenchmarkModes;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-Xmx1g", "-Xms1g"}, value = 1)
public class JMHHashMapTableSizeFor {

    static final int SIZE = 1000000;

    static final int[] arr = new int[SIZE];

    @State(Scope.Group)
    public static class Index {
        final AtomicInteger num = new AtomicInteger(2);

        public Index() {
        }

        @Setup
        public void prepare() {
            for (int i = 2; i < SIZE; i++) {
                arr[i] = i;
            }
        }

    }

    @Benchmark
    @Group("hashMap_tableSizeFor")
    public int hashMap_tableSizeFor(Index index) {
        return BinaryIntegerTest.tableSizeFor(getNum(index));
    }

    @Benchmark
    @Group("my_tableSizeFor")
    public int my_tableSizeFor(Index index) {
        return BinaryIntegerTest.tableSizeFor2(getNum(index));
    }

    private int getNum(Index index) {
        int i = index.num.incrementAndGet();
        if (i >= SIZE) {
            i = SIZE - 1;
            index.num.set(1);
        }
        return arr[i];
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JMHHashMapTableSizeFor.class.getSimpleName())

                .build();


        new Runner(opt).run();

    }
}
