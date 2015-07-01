package threading.nonblocking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.cs.umd.edu/class/fall2010/cmsc433/lectures.html
 *
 *
 *
 */
public class AtomicPseudoRandom {
    private final AtomicInteger seed;
    private static Long total = 0L;

    AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
        while (true) {
            int s = seed.get();
            int nextSeed = calculateNext(s);
            if (seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }

    // return something simple for demonstration purposes
    private int calculateNext(int s) {
        return s + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicPseudoRandom pr = new AtomicPseudoRandom(0);
        final ExecutorService executor = Executors.newCachedThreadPool();
        final CountDownLatch startSignal = new CountDownLatch(1);
        final int size = args.length >= 1 ? new Integer(args[0]) : 5;
        final CountDownLatch stopSignal = new CountDownLatch(size);


        for (int i = 0; i < size; i++) {
            executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        int j = 0;
                        try {
                            startSignal.await();
                        } catch (InterruptedException e) {
                        }
                        while (!Thread.interrupted()) {
                            pr.nextInt(100);
                            ++j;
                        }
                        synchronized (total) {
                            total += j;
                            stopSignal.countDown();
                        }
                    }
                });
        }
        startSignal.countDown();
        Thread.sleep(500);
        executor.shutdownNow();
        stopSignal.await();
        System.out.println(total);
    }
}