package threading.Chapter12;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoundedBufferTest {

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
		emptyTest();
		try {
			fullTest();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final void emptyTest()
	{
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}

	private void fullTest() throws InterruptedException
	{
		BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
		for (int i = 0; i < 10; ++i)
			bb.put(i);
		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
}
