package threading.Chapter12;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BoundedBufferTest.class, 
	TestTakeBlockWhenEmpty.class 
})
public class AllTests {

}
