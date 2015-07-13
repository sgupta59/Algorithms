package threading.OneLaneBridge;

import java.util.ArrayList;

/**
 * Interface for the access strategy.
 *
 *
 * @author Sanjeev Gupta
 *
 */
interface IBridgeAccessStrategy {

    /**
     * Enter the bridge
     *
     * @param car
     * @throws InterruptedException
     */
	public void enterBridge(Car car) throws InterruptedException;

	/**
	 * Exit the bridge
	 *
	 * @param car
	 */
	public void exitBridge(Car car);

	/**
	 * Return the number of cars that are present on the bridge at the moment.
	 *
	 * @return
	 */
	public ArrayList<Car> getCurrentSnapShot();

}
