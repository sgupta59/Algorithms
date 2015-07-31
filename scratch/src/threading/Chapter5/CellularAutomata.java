package threading.Chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CellularAutomata {

	// store a reference to the board
	private final Board mainBoard;
	private final CyclicBarrier barrier;
	private final Worker[] workers;
	public CellularAutomata(Board board) {
		
		this.mainBoard = board;
		int count = Runtime.getRuntime().availableProcessors();
		barrier = new CyclicBarrier(count, new Runnable() {
			public void run() {
				mainBoard.commitNewValues();
			}
		});
		workers = new Worker[count];
		for (int i = 0; i < count; ++i)
			workers[i]= new Worker(mainBoard.getSubBoard(count, i));
	}
	
	public void start() 
	{
		for (int i = 0; i < workers.length; ++i)
			new Thread(workers[i]).start();
		mainBoard.waitForConvergence();
	}
	public  class Worker implements Runnable {
		private final Board board;
		public Worker(Board board) {
			this.board = board;
		}
		
		public void run() {
			while (!board.hasConverged()) {
				// do a solution
				for (int i = 0; i < board.getMaxX(); ++i)
					for (int j = 0; j < board.getMaxY(); ++j)
						board.setNewValue(i, j, 0);
				// now wait for the barrier.
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
interface Board {
    int getMaxX();
    int getMaxY();
    int getValue(int x, int y);
    int setNewValue(int x, int y, int value);
    void commitNewValues();
    boolean hasConverged();
    void waitForConvergence();
    Board getSubBoard(int numPartitions, int index);
}