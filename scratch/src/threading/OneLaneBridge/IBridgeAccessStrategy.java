package threading.OneLaneBridge;

import java.util.ArrayList;

interface IBridgeAccessStrategy {

	public void enterBridge(Car car) throws InterruptedException;
	
	public void exitBridge(Car car);
	
	public ArrayList<Car> getCurrentSnapShot();
	
}
