package threading.Chapter10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LeftRightDeadLock {

	private final Object leftLock = new Object();
	private final Object rightLock = new Object();
	private final CyclicBarrier barrier;
	 
	public LeftRightDeadLock(CyclicBarrier barrier) {
		 
		this.barrier = barrier;
	}
	
	public void leftRightLock() throws InterruptedException {
		synchronized(leftLock) {
			synchronized(rightLock) {
				//Thread.sleep(1000);
				System.out.println("lrlock");
			}
		}
	}
	
	public void rightLeftLock() throws InterruptedException {
		synchronized(rightLock) {
			synchronized(leftLock) {
				//Thread.sleep(500);
				System.out.println("rllock");
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		final CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService pool = Executors.newCachedThreadPool();
		final LeftRightDeadLock lrlock = new LeftRightDeadLock(barrier);
		for (int i = 0; i < 2; ++i) {
			final int value = i;
			pool.execute(new Runnable() { 
				public void run() {
					try {
						barrier.await();
					} catch (Exception e) {}
					while (true) {
						try {
						if (value%2==0) {
							lrlock.leftRightLock();
						} else {
							lrlock.rightLeftLock();
						}
						} catch (InterruptedException e) {}
					} 
				}
			});
		}
		
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pool.awaitTermination(1000, TimeUnit.SECONDS);
	}
}
