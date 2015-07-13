package threading.OneLaneBridge;

import java.util.ArrayList;

import threading.OneLaneBridge.Car.Direction;

/**
 * Tester class to test the bridge crossing of cars in both left and right directions.
 * 
 * This class creates multiple cars for left and right motion. 
 * 
 * A bridge access strategy is used to cross the cars. 
 * The strategy is either fair where there are no collisions of the cars on the bridge and travel is allowed in a fair manner
 * or a default strategy where any car can pass through. The purpose of the default strategy is to test the car updates.
 * 
 * @author kg
 *
 */
public  class BridgeAccessTester {

	/**
	 * Bridge access stragegy object  
	 */
	private final IBridgeAccessStrategy bridge;
	
	public BridgeAccessTester(IBridgeAccessStrategy bridge)
	{
		 
		this.bridge = bridge;
	}
	
	/**
	 * Test number of cars on left and right side is greater than the limit of cars on the bridge.
	 * The cars move in different speed.
	 * Objective is that the cars move fairly even though the speed is different
	 * 
	 */
	public static void executeSymmerticTestVariableSpeed()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 6 car on the left, 6 car on the right.");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(6,6,25,15,200,800);
		System.out.println("---------------------------------------------------------------------------------");
	}
	
	/**
	 * Test same number of cars moving from two directions when one side (left) is moving very fast compared to the right side
	 * Objective is to see the car motion to be interleaved and one side is not starved.
	 * 
	 */
	public static void executeSlowFastSpeedTest()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 4 cars on left and right side of the bridge. Left car speed 100 units / ms, Right car speed 15 units /ms");
		System.out.println("---------------------------------------------------------------------------------\n");	
		factory.performTest(4,4, 100, 15, Car.BRIDGE_START, Car.BRIDGE_END);
	}
	
	/**
	 * The number of cars on left is 2 and right is 6. The cars move in different directions.
	 * The bridge dimensions are changed.
	 * 
	 * Objective: Test an unsymmetric case with differnet speeds 
	 * 
	 */
	public static void executeUnsymmetricVariableSpeedTest()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 2 cars on the left, 6 on the right, left car slower than the right cars");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(2,6,5,10,200,800);
	}
	
	/**
	 * Default test where any car can pass through the bridge.
	 * Test the car movement code
	 * 
	 */
	public static void executeDefaultTest()
	{
		IBridgeAccessStrategy defStrategy = new DefaultBridgeAccessStrategy();
		BridgeAccessTester factory = new BridgeAccessTester(defStrategy);
		System.out.println("Default Test: Cars can pass through the bridge at any time. Check for car moving");
		factory.performTest(10,10);
	}
	
	/**
	 * Stress test with 100 cars on left and right side
	 * Output should be interleaved
	 */
	public static void executeStressTest100()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Stress Test 100 car on the left, 100 car on the right.");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(100,100); 
	}
	
	/**
	 * One car moves from left side 
	 * Just starter to execute the whole code.
	 */
	public static void execute_left_1_right_0_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 1 car on the left, 0 car on the right");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(1,0);
	}
	
	/**
	 * Move 5 cars from left side.
	 * Objective is that after 3 cars even though the bridge expects cars from right side, since there are no 
	 * right waiters, the bridge allows cars from left side.
	 * There are no blocks here
	 */
	public static void execute_left_5_right_0_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 5 car on the left, 0 car on the right");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(5,0);
	}
	
	/**
	 * Cars from right side again. The bridge will expects cars from the left but since there are none, the right cars
	 * pass through
	 * 
	 */
	public static void execute_left_0_right_5_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 5 car on the left, 0 car on the right");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(0,5);
	}
	
	/**
	 * Symmertic number of cars from left and right side.
	 * There are 100 cars on each side
	 */
	public static void execute_left_20_right_20_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 20 cars on the left side and 20 cars on the right side of the bridge");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(20,20);
	}
	
	/**
	 * Run 5 cars from left, 3 from right.
	 * This will test the change of direction on the bridge code.
	 * 
	 */
	public static void execute_left_5_right_3_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 5 cars on the left side and 3 cars on the right side of the bridge");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(5,3);
	}
	/**
	 * Run 4 cars on left, 5 on the right.
	 * 
	 * This will test the change of direction on the bridge code.
	 * 
	 */
	public static void execute_left_4_right_5_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 5 cars on the left side and 3 cars on the right side of the bridge");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(4,5);
	}
	
	/**
	 * Run 3 cars on left, 3 on the right.
	 * 
	 * This will test the change of direction on the bridge code.
	 * 
	 */
	public static void execute_left_3_right_3_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 5 cars on the left side and 3 cars on the right side of the bridge");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(3,3);
	}
	
	/**
	 * Run 5 cars on left, 1 on the right.
	 * 
	 * Tests that the direction switches after 3 left even though there are still 2 cars on left
	 * 
	 */
	public static void execute_left_5_right_2_test()
	{
		BridgeAccessTester factory = new BridgeAccessTester(new FairBridgeAccessStrategy());
		
		System.out.println("\n---------------------------------------------------------------------------------");
		System.out.println("Test 5 cars on the left side and 3 cars on the right side of the bridge");
		System.out.println("---------------------------------------------------------------------------------\n");
		factory.performTest(5,2);
	}
	
	/**
	 * Create number of cars on left and right side and move them.
	 * 
	 * @param leftCount		Number of cars on the left side
	 * @param rightCount	Number of cars on the right side
	 * 
	 */
	public void performTest(int leftCount, int rightCount)
	{
		performTest(leftCount, rightCount, 5, 5, Car.BRIDGE_START, Car.BRIDGE_END);
	}
	
	/**
	 * Tester method that creates cars on the left and right side. The cars can move with different speeds and the bridge dimension
	 * can be varied on the road. The bridge dimensions can ont be greater than the road dimensions.
	 * 
	 * @param leftCount		The number of cars on left side
	 * @param rightCount	The number of cars on the right side
	 * @param lspeed		The speed of the left cars
	 * @param rspeed		The speed of the right cars
	 * @param bridgestart	The start point of the bridge (as seen from the left)
	 * @param bridgeend		The end point of the bridge as seen from the left 
	 * 
	 */
	public void performTest(int leftCount, int rightCount , int lspeed, int rspeed, int bridgestart, int bridgeend )
	{ 
		// create 3 cars
		Car previous = null;
		ArrayList<Car> leftCars = new ArrayList<Car>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		if (leftCount > 0)
		{
			for (int i = 0; i < leftCount; ++i)
			{
				Car car = new Car(Direction.LEFT, previous, bridge,lspeed, bridgestart, bridgeend);
				leftCars.add(car);
				previous= car;
			}
		}
		previous = null;
		ArrayList<Car> rightCars = new ArrayList<Car>();
		if (rightCount > 0)
        {
			for (int i = 0; i < rightCount; ++i)
         
			{
				Car car = new Car(Direction.RIGHT, previous, bridge,rspeed, bridgestart, bridgeend);
				rightCars.add(car);
				previous= car;
			}
        }
		// start running the cars.
		
		for (int i = 0; i < leftCars.size(); ++i)
		{
			Thread thread = new Thread(leftCars.get(i));
			threads.add(thread);
		}
		for (int i = 0; i < rightCars.size(); ++i)
		{
			Thread thread = new Thread(rightCars.get(i));
			threads.add(thread);
		}
		
		/**
		 * Start all threads
		 */
		for (int i = 0; i < threads.size(); i = i +1)
		{
			threads.get(i).start();

		}
			 
		/**
		 * Wait for the cars to stop
		 */
		for (Thread thread : threads)
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return;
	}
	
	public static void main(String[] args)
	{
		 
		executeDefaultTest();
		
		execute_left_1_right_0_test();
		
		execute_left_5_right_0_test();
		
		execute_left_0_right_5_test();
		
		execute_left_5_right_2_test();
		
		execute_left_20_right_20_test();
		
		execute_left_3_right_3_test();
		
		execute_left_5_right_3_test();
		
		executeUnsymmetricVariableSpeedTest();
		
		executeSymmerticTestVariableSpeed();

		executeStressTest100();	
	}
	 
	
}
