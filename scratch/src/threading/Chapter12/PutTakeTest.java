package threading.Chapter12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PutTakeTest {
	private final BoundedBuffer<Integer> bb;
	private final int nTrials, nPairs;
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final CyclicBarrier barrier;
	PutTakeTest(int capacity, int npairs, int ntrials) {
		bb = new BoundedBuffer<Integer>(capacity);
		nTrials = ntrials;
		nPairs = npairs;
		barrier = new CyclicBarrier(2*npairs+1);
	}
	
	void test() {
		 try {
			 for (int i = 0; i < nPairs; ++i)
			 {   
				 pool.execute(new Producer());
				 pool.execute(new Consumer());
			 }
			 barrier.await();
			 barrier.await();
			 if (putSum.get()== takeSum.get())
				 System.out.println("Success");
			 else
				 System.out.println("Failure");
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 }
	}
	
	private static int XorShift(int y)
	{
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}
	public static void main(String[] args)
	{
		new PutTakeTest(10,1,1).test();
		pool.shutdown();
	}
	
	class Producer implements Runnable {
		public void run() {
			try {
				int seed = this.hashCode() ^ (int) System.nanoTime();
				int sum = 0;
				barrier.await();
				for (int i = 0; i < nTrials; ++i)
				{
					bb.put(seed);
					sum += seed;
					seed = XorShift(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e)
			{
				throw new RuntimeException(e);
			}
		}
	}
	
	class Consumer implements Runnable {
		public void run() {
			try {
				barrier.await();
				int sum = 0;
				for (int i = 0; i < nTrials; ++i) {
					sum += bb.get();
				}
				takeSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
