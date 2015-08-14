package concurrency.Asynchronous;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncTester {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		APort<Integer> port = new APort<Integer>();
		// create two senders
		Sender sender1 = new Sender(port,latch);
		Sender sender2 = new Sender(port,latch);
		// create one receiver.
		Receiver recriver = new Receiver(port,latch);
		ExecutorService execute = Executors.newCachedThreadPool();
		execute.execute(sender1);
		execute.execute(sender2);
		execute.execute(recriver);
		latch.countDown();
		execute.shutdown();
		execute.awaitTermination(50000, TimeUnit.SECONDS);
	}
	
	
}
 class Sender implements Runnable {
	
	private final APort<Integer> port;
	private final CountDownLatch latch;
	Sender(APort<Integer> port, CountDownLatch latch) {
		this.port = port;
		this.latch = latch;
	}
	

	@Override
	public void run() {
		// do something.
		try {
			latch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			for (int i = 0; i < 1000; ++i)
			{
				port.put(new Integer(i));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

 class Receiver  implements Runnable {
	private final APort<Integer> port;
	private final CountDownLatch latch;
	public Receiver(APort<Integer> port, CountDownLatch latch) {
		this.port = port;
		this.latch = latch;
	}
	
	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while (true) {
			Integer i;
			try {
				i = port.get();
				System.out.println("Received: " + i.toString());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}