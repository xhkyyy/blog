package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Fork(jvmArgsAppend = {"-XX:+PrintCommandLineFlags", "-XX:+PrintGCDetails"}, value = 1)
public class JvmArgs {

    @Benchmark

    public void wellHelloThere(Blackhole bh) {

        bh.consume(new Object());

    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(JvmArgs.class.getSimpleName())

                .build();

        new Runner(opt).run();

    }


}
