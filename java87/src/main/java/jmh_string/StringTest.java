package jmh_string;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Here show different performance between  (A += B) and (A = A + B)
 * <p>
 * Average time.
 */
public class StringTest {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measure1() {
        int size = 3;
        String[] array = new String[size];
        Arrays.fill(array, "");

        for (int i = 0; i <= 10000; ) {
            System.out.println(i);
            array[i++ % size] += i + " ";
        }
        for (String element : array) {
            System.out.println(element);
        }
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measure2() {
        int size = 3;
        String[] array = new String[size];
        Arrays.fill(array, "");

        for (int i = 0; i <= 10000; ) {
            System.out.println(i);
            array[i++ % size] = array[i++ % size] + i + " ";
        }
        for (String element : array) {
            System.out.println(element);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measure3() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(StringTest.class.getSimpleName())

                .forks(1)

                //.threads(1)

                .build();


        new Runner(opt).run();

    }

}
