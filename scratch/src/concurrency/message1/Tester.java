package concurrency.message1;

public class Tester {

	public static void main(String[] args)
	{
		Tester t = new Tester();
		Channel<Integer> ch = new Channel<Integer>();
		Thread sendThread = new Thread( t.new Sender(ch));
		Thread receiveThread = new Thread(t.new Receiver(ch));
		sendThread.start();
		receiveThread.start();
		try {
			sendThread.join();
			receiveThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	class Receiver implements Runnable {
		Channel<Integer> channel;
		Receiver(Channel<Integer> ch) {
			channel = ch;
		}
		public void run()
		{
			 
			while (true) {
				try {
					Thread.sleep(1000);
					int id = channel.receive();
					System.out.println("Received: " + id);
				} catch (InterruptedException e)
				{
					
				}
			}
		}
	}
	class Sender implements Runnable {
		Channel<Integer> channel;
		public Sender(Channel<Integer> ch) 
		{
			channel = ch;
		}
		public void run()
		{
			int i = 0;
			while (true)
			{
				
				try {
					System.out.println("Sent: " + i);
					channel.send(i++);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
