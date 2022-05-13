package primeCalc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrimeCalculator {
    public static void main(String[] args) throws InterruptedException {
        for (Integer prime : getPrimes(Integer.parseInt(args[0]))) {
            System.out.print(prime + "\n");
        }
    }

    public static List<Integer> getPrimes(int maxPrime) throws InterruptedException {
        List<Integer> primeNumbers = Collections.synchronizedList(new LinkedList<>());
      
        CountDownLatch latch = new CountDownLatch(maxPrime-1);
        ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Integer candidate = 2; candidate<= maxPrime; candidate++) {
            Integer threadCandidate = candidate;
            executors.submit(() -> {
                    if (isPrime(threadCandidate))
                    {
                        primeNumbers.add(threadCandidate);
                    }
                    latch.countDown();
                });
            }

        latch.await();
        executors.shutdownNow();
        Collections.sort(primeNumbers);

        return primeNumbers;
    }

    private static boolean isPrime(Integer candidate) {
        if (candidate<2) return false;
        if ((candidate % 2 == 0) && (candidate!=2)) return false;
        for (Integer j=3; j*j<candidate; j+=2) {
            if (candidate % j == 0) {
                return false;
            }
        }
        return true;
    }
}
