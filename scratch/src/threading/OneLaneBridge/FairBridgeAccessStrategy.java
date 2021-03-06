package threading.OneLaneBridge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import threading.OneLaneBridge.Car.Direction;

/**
 * A bridge access strategy.
 *
 * This strategy allows up to 3 cars to be on the bridge at the same time. Cars can wait on both ends of the bridge.
 *
 * The cars have to be moving in the same direction.
 *
 * Once 3 cars of the same direction have moved on this bridge, the direction of motion on this bridge is reversed and the bridge allows
 * cars from the other end of the bridge.
 *
 * In case there are no cars at one end of the bridge, then the bridge continues to allow cars from the end which has cars queued up.
 *
 * If the last car leaves the bridge, and then a new car arrives at the bridge, and the direction of this new car is different from the
 * direction the bridge expects, since the bridge has no "waiting" cars, the car's direction becomes the bridge's allowed motion direction.
 *
 * Special cases such as the following are handled:
 *   Left car 1 arrives - > proceeds on the bridge
 *   Left car 2 arrives - > proceeds on the bridge
 *   Right car 1 arrives -> waits for access
 *   Left car 1 leaves   -> right car waits
 *   Left car 3 arrives  -> right car waits, left 3 proceeds on the bridge.
 *   Left 2 car exits bridge -> right car waits, left car 3 is on the bridge
 *   Left car 4 arrives -> left 4 waits as 3 cars have processed already and now it is right car's turn.
 *
 *
 * Attempt is made to allow cars from one side to travel, then cars from the reverse side to travel.
 *
 * @author Sanjeev
 *
 */
public final class FairBridgeAccessStrategy implements IBridgeAccessStrategy {

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int DEFAULT_BRIDGE_LIMIT = 3;

	/** A deque of allowed cars that can stay on the bridge */
	private ArrayDeque<Car> cars =null;

	/** Access lock for this bridge */
	private final Lock accessLock = new ReentrantLock();

	/**
	 * Condition variables from left and the right sides of the bridge.
	 * The left side is 0
	 * The right side is 1
	 */
	private Condition[] directionAvailable = null;

	/**
	 * Special flag that will stop cars entering from the same direction if a total of 3 cars have already travelled on the bridge
	 * and there are waiting cars on the other side.
	 */
	private boolean stopAccepting = false;

	/**
	 * Total number of waiting cars on both ends.
	 * 0 = Left
	 * 1 = Right
	 */
	private final int[] waiters = new int[2];

	/**
	 * Size of the queue holding the cars travelling on the bridge.
	 */
	private int currentSize = 0;

	/**
	 * The current allowed direction for this bridge. Defaults to Left
	 */
	private Car.Direction bridgeDirection = Car.Direction.LEFT;

	private  int max_limit;

	/**
	 * Public constructor for the access strategy.
	 *
	 * @param limit
	 */
	public FairBridgeAccessStrategy()
	{
		this.cars = new ArrayDeque<Car>();
		directionAvailable = new Condition[2];
		directionAvailable[LEFT] = accessLock.newCondition();
		directionAvailable[RIGHT] = accessLock.newCondition();
		max_limit = DEFAULT_BRIDGE_LIMIT;
	}

	/**
	 * Public constructor for the access strategy. Allows control on how many items can be on the queue at one time.
	 *
	 * @param limit		The number of items on the bridge at one time.
	 */
	public FairBridgeAccessStrategy(int limit)
	{
		this();
		if (limit < 0)
			throw new IllegalArgumentException("Limit can not be less than 0");
		max_limit = limit;

	}

	/**
	 * A synchronized method that allows a car to enter this bridge.
	 *
	 * This method will either queue the car at the start of the bridge or will allow the car to proceed
	 *
	 *
	 * @param car
	 * @throws InterruptedException
	 */
	@Override
    public  final void enterBridge(Car car) throws InterruptedException
	{
		Direction carDir = car.getDirection();
		/** Directon car is moving in */
		int car_direction = getDirIndex(carDir);
		/** Car facing direction */
		int car_facing_direction = carDir == Direction.LEFT ? RIGHT : LEFT;
		accessLock.lock();
		try
		{
			 /**
			 * Condition variable loop for following checks:
			 *
			 * 1. If the bridge is full (currentSize == bridge_limit), wait.
			 * 2. If this car is in a different direction than the ones on the bridge wait
			 * 3. If the car direction is not same as the bridge direction and the current queue is empty BUT
			 *    there are waiters on the other side, wait.
			 * 4. If the car direction is the same as the bridge but there are waiters on the other side and at one point
			 *    3 cars went through in the same direction. Actually direction should not matter here, if stopAccepting
			 *    is true and there are waiters at the other end, this should be enough of a condition.
			 */

			boolean isSameDirection = bridgeDirection == car.getDirection();
			while ( (currentSize == max_limit 						                ||       /* #1 */
					(currentSize > 0 && !(isSameDirection)) 			            ||       /* #2 */
					(!(isSameDirection) && waiters[car_facing_direction] != 0) 		||       /* #3 */
					(isSameDirection && stopAccepting && waiters[car_facing_direction] != 0) /* #4 */
					))
			{
				// for the specific direction, wait while space becomes available.
				++waiters[car_direction];

				//System.out.println(car.toString() + " waiting" + " size: " + currentSize);
				directionAvailable[car_direction].await();

				// update the direction flag as it might have been changed
				isSameDirection = bridgeDirection == car.getDirection();
				--waiters[car_direction];
			}

			/**
			 * Default to the car's direction if there are no pending cars and there are no waiters.
			 */
			if (currentSize == 0 && waiters[LEFT] ==0 &&  waiters[RIGHT]==0)
			{
				bridgeDirection = car.getDirection();
			}
			///System.out.println(car.toString() + " entered" + " size: " + currentSize);

			if (carDir != bridgeDirection)
			{
				System.out.println("Collision: Car [" + car.toString() + "] going in reverse direction to the bridge");
			}

			/** Add the car to the queue */
			cars.add(car);

			/** Update the current size */
			++currentSize;

			/** If the current size is 3, stop accepting cars from this  direction. This flag is reset when cars exit */
			if (currentSize == max_limit)
			{
				stopAccepting = true;
			}

		}
		finally
		{
			accessLock.unlock();
		}
	}

	/**
	 * Synchronized method for exiting the bridge.
	 *
	 * @param car
	 */
	@Override
    public final void exitBridge(Car car)
	{
		accessLock.lock();
		try
		{
			// Decrement the size of the queue
			--currentSize;

			// Drain an item from the queue
			cars.poll();

			// Get the other direction compared to the diretion of the bridge */
			int otherdir = bridgeDirection == Direction.LEFT ? RIGHT : LEFT;
			System.out.println(car.toString() + " exited  " + "size: " + currentSize);
			//System.out.println(car.toString() + " exited  " + "size: " + currentSize);

			/**
			 * If the current size is 0 and there are waiters on the other side, flip the direction of travel.
			 */
			if (currentSize == 0 && waiters[otherdir] != 0)
			{
				bridgeDirection = bridgeDirection.reverse();
				directionAvailable[bridgeDirection.ordinal()].signalAll();
				stopAccepting = false;
			}
			else
			{
				/**
				 * Otherwise, wake up a waiting car on this side.
				 */
				directionAvailable[bridgeDirection.ordinal()].signal();
			}

		}
		finally
		{
			accessLock.unlock();
		}
	}

	/**
	 * Returns the current snapshot of the cars on the bridge.
	 *
	 * @return
	 */
	@Override
    public final ArrayList<Car> getCurrentSnapShot()
	{

		accessLock.lock();
		try
		{
			/**
			 * Make a copy of the current cars and return it.
			 */
			return new ArrayList<Car>(cars);
		}
		finally
		{
			accessLock.unlock();
		}
	}

	private int getDirIndex(Direction cdir )
	{
		int dir = cdir == Direction.LEFT ? 0 : 1;
		return dir;
	}

}
