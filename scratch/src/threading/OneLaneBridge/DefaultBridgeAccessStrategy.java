package threading.OneLaneBridge;

import java.util.ArrayList;

/**
 * Default access strategy. Allows all cars to pass through
 * 
 * @author Sanjeev
 *
 */
public class DefaultBridgeAccessStrategy implements IBridgeAccessStrategy {

	
	@Override
	public void enterBridge(Car car) throws InterruptedException 
	{
		System.out.println(car.toString() + " entered");
	}

	@Override
	public void exitBridge(Car car) 
	{
		System.out.println(car.toString() + " exited");
	}

	@Override
	public ArrayList<Car> getCurrentSnapShot() {
		ArrayList<Car> list = new ArrayList<Car>();
		
		return list;
	}

}
