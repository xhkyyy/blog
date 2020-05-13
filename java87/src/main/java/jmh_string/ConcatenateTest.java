package jmh_string;

import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class ConcatenateTest {

    private final static int SIZE = 100;

    private final static  char[] array = RandomStringUtils.randomAlphanumeric(SIZE).toCharArray();


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measure1() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10000; i++) {

            sb.append(array[i % SIZE]);

        }

        //System.out.println("measure1 size: " + sb.length());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measure2() {

        String sb = "";

        for (int i = 0; i < 10000; i++) {

            sb = sb + array[i % SIZE];

        }

       // System.out.println("measure2 size: " + sb.length());
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(ConcatenateTest.class.getSimpleName())

                .forks(1)

                .build();


        new Runner(opt).run();

    }
}
