package threading.Chapter12;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTakeBlockWhenEmpty {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread(new Runnable() {
			public void run() {
				try {
					bb.get();
					fail();
				} catch (InterruptedException e) {
					
				}
				
			}
		});
		try {
			taker.start();
			Thread.sleep(10000);
			taker.interrupt();
			taker.join(10000);
			assertFalse(taker.isAlive());
		} catch (InterruptedException e) {
			fail();
		}
	}

}
