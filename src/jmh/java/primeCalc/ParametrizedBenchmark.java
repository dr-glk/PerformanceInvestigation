package primeCalc;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class ParametrizedBenchmark {
    @Param({"100000", "1000000", "10000000"})
    public int max_prime;

    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2)
    @Measurement(iterations = 3)
    @Fork(1)
    @org.openjdk.jmh.annotations.Benchmark()
    public void getPrimesAverageTimeBenchmark(Blackhole bh) throws InterruptedException {
        bh.consume(PrimeCalculator.getPrimes(max_prime));
    }
}


