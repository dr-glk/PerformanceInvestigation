package primeCalc;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
public class Benchmark {

    @BenchmarkMode(Mode.SingleShotTime)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @Fork(3)
    @org.openjdk.jmh.annotations.Benchmark()
    public void getPrimesSingleShotBenchmark(Blackhole bh) throws InterruptedException {
        bh.consume(PrimeCalculator.getPrimes(100000));
    }

    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(1)
    @org.openjdk.jmh.annotations.Benchmark()
    public void getPrimesThroughPutBenchmark(Blackhole bh) throws InterruptedException {
        bh.consume(PrimeCalculator.getPrimes(100000));
    }
}

