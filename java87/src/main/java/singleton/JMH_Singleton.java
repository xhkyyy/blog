package singleton;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Group)
public class JMH_Singleton {

    @Group("singleton3Best")
    @Benchmark
    public void singleton3Best(Blackhole blackhole) {
        blackhole.consume(Singleton3.getInstance());
    }

    @Group("singleton4")
    @Benchmark
    public void singleton4(Blackhole blackhole) {
        blackhole.consume(Singleton4.getInstance());
    }

    @Group("Singleton2")
    @Benchmark
    public void singleton2(Blackhole blackhole) {
        blackhole.consume(Singleton2.getInstance());
    }


    public static void main(String[] args) throws RunnerException {
        int availableProcessors = Runtime.getRuntime().availableProcessors() / 2;
        if (availableProcessors < 5) {
            availableProcessors = 5;
        }
        System.out.println("availableProcessors: " + availableProcessors);
        Options opt = new OptionsBuilder()
                .include(JMH_Singleton.class.getSimpleName())
                .threads(availableProcessors)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}