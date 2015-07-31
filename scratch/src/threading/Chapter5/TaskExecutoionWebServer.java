package threading.Chapter5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutoionWebServer {

	public static void executeUnboundedThreads() throws IOException 
	{
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				public void run() {
					handleRequest(connection);
				}
			};
			new Thread(task).start();
		}
	}
	
	public static void executeBoundedThreads() throws IOException 
	{
		Executor executor = Executors.newFixedThreadPool(100);
		ServerSocket server = new ServerSocket(80);
		while (true) {
			final Socket connection = server.accept();
			Runnable run = new Runnable() {
				public void run() {
					handleRequest(connection);
				}
			};
			executor.execute(run);
		}
		
	}
	
	public static void executeThreadPerConnection() throws IOException 
	{
		Executor executor = new Executor() {
			public void execute(Runnable r) {
				new Thread(r).start();
			}
		};
		ServerSocket server = new ServerSocket(80);
		while (true) {
			final Socket connection = server.accept();
			Runnable run = new Runnable() {
				public void run() {
					handleRequest(connection);
				}
			};
			executor.execute(run);
		}
	}
	public static void handleRequest(Socket connection) {
		return ;
	}
}
