package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class JMHSample_28_BlackholeHelpers {

    /**
     * Sometimes you need the black hole not in @Benchmark method, but in
     * helper methods, because you want to pass it through to the concrete
     * implementation which is instantiated in helper methods. In this case,
     * you can request the black hole straight in the helper method signature.
     * This applies to both @Setup and @TearDown methods, and also to other
     * JMH infrastructure objects, like Control.
     * <p>
     * Below is the variant of {@link org.openjdk.jmh.samples.JMHSample_08_DeadCode}
     * test, but wrapped in the anonymous classes.
     */

    public interface Worker {
        void work();
    }

    private Worker workerBaseline;
    private Worker workerRight;
    private Worker workerWrong;

    @Setup
    public void setup(final Blackhole bh) {
        workerBaseline = new Worker() {
            double x;

            @Override
            public void work() {
                // do nothing
            }
        };

        workerWrong = new Worker() {
            double x;

            @Override
            public void work() {
                Math.log(x);
            }
        };

        workerRight = new Worker() {
            double x;

            @Override
            public void work() {
                bh.consume(Math.log(x));
            }
        };

    }

    @Benchmark
    public void baseline() {
        workerBaseline.work();
    }

    @Benchmark
    public void measureWrong() {
        workerWrong.work();
    }

    @Benchmark
    public void measureRight() {
        workerRight.work();
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You will see measureWrong() running on-par with baseline().
     * Both measureRight() are measuring twice the baseline, so the logs are intact.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_28
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_28_BlackholeHelpers.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}