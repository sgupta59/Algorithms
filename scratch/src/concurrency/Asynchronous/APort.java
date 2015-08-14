package concurrency.Asynchronous;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A port will collect all messages and allow receivers to read off of it.
 * @author kg
 *
 * @param <T>
 */
public class APort<T> extends Selectable {

	Queue<T> queue = new LinkedList<T>();
	
	public synchronized void put(T item) {
		queue.add(item);
		signal();
	}
	
	public synchronized T get() throws InterruptedException {
		block();
		return queue.remove();
	}
}
