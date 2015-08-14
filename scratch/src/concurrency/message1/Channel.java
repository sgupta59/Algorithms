package concurrency.message1;

public class Channel<T> extends Selectable {

	T _value;
	public Channel()
	{
		
	}
	
	public synchronized void send(T t) throws InterruptedException
	{
		_value = t;
		signal();
		while (_value != null) wait();
	}
	
	public synchronized T receive() throws InterruptedException
	{
		block();
		T old = _value;
		_value = null;
		notifyAll();
		return old;
	}
}
