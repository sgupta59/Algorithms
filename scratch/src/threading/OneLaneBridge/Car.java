package threading.OneLaneBridge;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Car class.
 *
 * This is a runnable class that is launched in a thread.
 *
 * The thread periodically updates the position of the car while taking into consideration the position of the car in front of it.
 * If this car will collide with the car in front of it (with the same direction), then the car does not move.
 *
 * The car attempts to cross a bridge and while on the bridge, attempts to check for collisions.
 *
 * The car assumes that the road starts at distance 0 and ends at distance {@link ROAD_END}
 * The bridge is located within the road and has default value of {@link BRIDGE_START} and {@link BRIDGE_END}
 *
 * This car can be instantiated either with default values or with input speed.
 * The bridge position can also be changed from default value but all cars need to have the same value.
 *
 * The car periodically updates its position, when it gets to the bridge start, it will attempt to enter the bridge.
 * While on the bridge the car will update its position and will exit the bridge when it reaches the bridge end.
 * While the car is running on the bridge, it checks to find out any collisions with any ohter car.
 *
 * The collision of a car will occur only with a car in the reverse direction. The car's update mechanism prevents the car from
 * colliding with a car in the same direction.
 *
 * The car can either go from Left to Right or from Right to left.
 *
 * @author Sanjeev
 *
 */
public class Car implements Runnable{

	private static final int ROAD_END = 900;
	private static final int ROAD_START = 0;
	public static final int BRIDGE_START = 300;
	public static final int BRIDGE_END = 600;
	/**
	 * Enumeration indicating the direction the car will be running in.
	 *
	 * @author Sanjeev
	 *
	 */
	public enum Direction {
		LEFT(1),
		RIGHT(-1);
		/** Sign of the motion */
		private int sign = 0;

		/** Private constructor */
		private Direction(int sign)
		{
			this.sign = sign;
		}

		/** The sign of this direction */
		public int sign()
		{
			return sign;
		}


		/**
		 * Reverse the current direction
		 * @return	The reversed direction as a new object
		 */
		public Direction reverse()
		{
			return this == LEFT ? RIGHT : LEFT;
		}
	};

	/** The car position at the moment. This is an atomic integer as the position is updated in this car's thrad and is accessed
	 *  the car behind this car.
	 */
	private  AtomicInteger carPosition;

	/** The speed of this car. This is number of units travelled by the car in 1 milli second */
	private int interval;

	/**
	 * State of the car. The car is running when this is true, false otherwise.
	 */
	private boolean runCar = true;

	/**
	 * The bridge start position. Defaults to 400 units of distance
	 */
	private int startBridgeX = BRIDGE_START;

	/**
	 * The bridge end position, defaults to 500 units of distance
	 */
	private int endBridgeX;
	/**
	 * Travel direction for this car
	 */
	private final Direction moveDirection;

	/**
	 * The car in front of this car. This can be null which means no car is in front of this one.
	 * Or a car which has already the route.
	 */
	private final Car frontCar;

	/**
	 * Bridge access strategy
	 */
	private final IBridgeAccessStrategy bridge;

	/**
	 * static members to generate unique ides for the cars.
	 */
	private static final int[] s_carIds = {0,0};

	/** Id of the car. The left and Right cars can have the same id, they are differentiated by the direction
	 *  Useful for debugging purposes.
	 */
	private final int id;

	/** String name for the car, used for debugging */
	private final String name;

	/**
	 * Default update interval for the car
	 */
	private long updateInterval = 1000;

	/**
	 * Basic construcotor for the car where the speed and bridge location are defaulted to following values:
	 *
	 * The speed or number of units travelled per millisecond is 0.005 units per millisecond.
	 *
	 * The bridge start and end are the interval [300,600]
	 *
	 * @param direction
	 * @param front
	 * @param bridge
	 */
	public Car(Direction direction, Car front, IBridgeAccessStrategy bridge)
	{
		this.moveDirection = direction;
		this.frontCar = front;
		this.bridge = bridge;


		this.interval = 5*direction.sign();
		this.startBridgeX = BRIDGE_START ;
		this.endBridgeX =   BRIDGE_END ;

		if (direction == Direction.LEFT )
		{
			this.carPosition = new AtomicInteger(ROAD_START);
			id = s_carIds[0]++;
			name = "L " + id;
		}
		else
		{
			this.carPosition = new AtomicInteger(ROAD_END);
			id =  s_carIds[1]++;
			name = "R " + id;
		}
	}
	/**
	 * Car constructor. The car constructor gets a reference to the car in front of it (can be null)
	 * and a reference to the bridge crossing strategy.
	 *
	 *
	 * @param direction
	 * @param front
	 * @param bridge
	 * @param speed		The speed of this car. The speed is specified in m/milli-seconds.
	 */
	public Car(Direction direction, Car front, IBridgeAccessStrategy bridge, int speed)
	{
		this(direction, front, bridge);
		this.interval = speed*direction.sign();
		updateInterval = 1;

	}

	/**
	 * Public constructor to create a car. Allows the bridge coordinates and the car speed to be controlled.
	 *
	 * @param direction		The direction of motion of this car.
	 * @param front			The car in front of this car, can be null.
	 * @param bridge		The bridge strategy
	 * @param speed			The speed of this car in units of distance per 1000 milliseconds
	 * @param bridgestart	The start of the bridge. Has to be > 0 and < ROAD_END {@link Car.ROAD_END}
	 * @param bridgeend		The end of the bridge. Has to be less than ROAD_END
	 */
	public Car(Direction direction, Car front, IBridgeAccessStrategy bridge, int speed, int bridgestart, int bridgeend)
	{
		this(direction, front, bridge, speed);
		if (bridgestart < 0 || bridgeend < 0)
			throw new IllegalArgumentException("Bridge start or end can not be less than 0 " + bridgestart + ", bridgeend");
		if (bridgeend > ROAD_END)
			throw new IllegalArgumentException("Bridge can not end at a roads end");
		this.startBridgeX =   bridgestart ;
		this.endBridgeX =  bridgeend ;
	}

	@Override
	public void run()
	{
		while (runCar)
		{
			try
			{
				/**
				 * Attempt to move the car and sleep for a 1000 milliseconds.
				 * This is an attempt to simulate the car's movement in units per milliseconds.
				 */
				move();
				Thread.sleep(updateInterval);
			}
			catch (InterruptedException e)
			{
				// continue
			}
			catch (CarCollisionException e) {
				// TODO Auto-generated catch block
				System.out.println("Collision encountered: " + e.getMessage() + ", car stopping running");
				//runCar = false;
			}
			// after move, update the location of the car. The car will stop running after it has gone out of the track.
			if (!canContinueRunning())
			{
				/** Update teh location of the car. This causes the car behind this car to know that this car has stopped running */
				carPosition.addAndGet(interval);
				runCar = false;
			}

		}

	}

	/**
	 *
	 * @return	Return the current position of this car.
	 */
	public int position()
	{
		return carPosition.get();
	}

	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * Get the direction of motion of this car.
	 * @return
	 */
	public Direction getDirection()
	{
		return this.moveDirection;
	}


	/**
	 * Attempt to move this car. The move method does the following:
	 *
	 * 1. Updates the location of this car. If there is a car in front of this car, then the location of the car is only updated
	 *    if there will be no collisions between the two cars.
	 * 2.
	 *
	 * @throws InterruptedException
	 * @throws CarCollisionException
	 */
	private void move() throws InterruptedException, CarCollisionException
	{
		int oldposition = carPosition.get();
		int newposition = updateCarPosition(oldposition);

		/** If the car did not change position, then return  */
		if (oldposition == newposition)
		{
			// do not move the car as this car will bump into the car in front of it.
			return;
		}
		if (moveDirection == Direction.LEFT)
		{
			processLocation(oldposition, newposition);
		}
		if (moveDirection == Direction.RIGHT)
		{
			/**
			 * For right moving cars, transform their location to the left side. This helps in keeping the code simple
			 * and the bridge dimensions same.
			 */
			oldposition = ROAD_END-oldposition;
			newposition=ROAD_END-newposition;
			processLocation(oldposition, newposition);

		}
		return;
	}

	/**
	 * Process the location of this car. The car has its previous location and the current location.
	 * The car will attempt to do the following:
	 *
	 * 1. Enter Bridge if the cars new position is after the bridge start point, and the previous location is less than the bridge start point.
	 * 2. Exit Bridge if the car's new position is after the bridge's end point, and the previous location is within the bridge.
	 * 3. Within the bridge, i.e. car is travelling in the bridge there is a check performed to make sure the car does not collidge with
	 *    any other cars.
	 * @param oldposition
	 * @param newposition
	 * @throws InterruptedException
	 * @throws CarCollisionException
	 */
	private void processLocation(int oldposition, int newposition) throws InterruptedException, CarCollisionException
	{
		if (newposition >= startBridgeX && oldposition < startBridgeX)
		{
			// car is attempting to enter the bridge.
			bridge.enterBridge(this);
		}
		else if (newposition >= endBridgeX && oldposition < endBridgeX)
		{
			// car is attempting to leave the bridge.
			bridge.exitBridge(this);
		}
		else if (newposition > startBridgeX && newposition < endBridgeX)
		{
			// this car is in the bridge, check for collisions here
			checkCollisions();
		}
	}

	/**
	 * Check if this car can continue running.
	 * If this is the left car and its position is > road's end, then this car has to stop
	 * If this is the right side car, then position <= 0 means the car has reached the end and it has to stop
	 *
	 * @return	TRUE  if the car can continue to run
	 * 			FALSE otherwise
	 */
	private boolean canContinueRunning()
	{
		boolean status = false;
		if (moveDirection == Direction.LEFT)
			status =  carPosition.get() >= ROAD_END ? false : true;
	    else
		    status = carPosition.get() <= 0 ? false : true;

		return status;
	}

	/**
	 * Update the position of the car based on the old position. The update is based on the car in front of this car.
	 * If this is the first car, then the car's position is updated by default.
	 * @param oldposition
	 * @return
	 */
	private int updateCarPosition(int oldposition)
	{
		int newposition = oldposition;
		if (frontCar == null)
		{
			// Update the car's position.
			 newposition = carPosition.addAndGet(interval);
		}
		else
		{
			if (moveDirection == Direction.RIGHT && frontCar.position() < (oldposition + interval))
			{
				/**
				 * For car travelling from the right, the car in front of this car will have its coordinates decreasing.
				 */
				newposition = carPosition.addAndGet(interval);
			}
			else if (frontCar.position() > (oldposition + interval))
			{
				/** The car in front has space, move this car. */
				newposition = carPosition.addAndGet(interval);
			}
		}
		return newposition;
	}

	/**
	 * Check for collisions. While this car is on the bridge, at every update check if this car and other cars on the bridge
	 * are moving in opposite directions.
	 *
	 * @throws CarCollisionException
	 */
	private void checkCollisions() throws CarCollisionException
	{
		ArrayList<Car> bridgeCars = bridge.getCurrentSnapShot();
		for (Car car : bridgeCars)
		{
			if (car.moveDirection != moveDirection)
			{
				System.out.println("Car : [" + name + "] collided with car : " + car.toString());
			}
		}
	}


}
