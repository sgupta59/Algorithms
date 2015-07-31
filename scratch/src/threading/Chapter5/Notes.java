package threading.Chapter5;

import java.util.Vector;

public class Notes {

	/**
	 * 1. Compound Actions using Client side locking.
	 */
	public static <E> E getLast(Vector<E> list) {
		synchronized(list) {
			for (int i = 0; i < list.size(); ++i) {
				// do something on the whole list.
			}
		}
		return null;
	}
	
	/**
	 * Iterators in most collections are fail fast. They throw ConcurrentModificationException.
	 * 1. Lock the entire collection.
	 * 2. Clone the collection and process.
	 * 
	 */
	
	/**
	 * BlockingQueue will block.
	 * 
	 * LinkedBlockingQueue  : FIFO
	 * ArrayBlockingQueue   : FIFO
	 * PriorityBlockingQueue : Queue with priority.
	 * SynchronousQueue: Maintains a list of queued threads waiting to enqueue or dequeue an element.
	 * 					A get thread blocks till there is a put thread putting data
	 * 					A put thread blocks till there is a get thread to retrieve data.
	 * 					Advantage: reduces latency
	 */
	
	/**
	 * Deque
	 * 1. Extends Queue (Deque)
	 * 2. BlockingDeque Extends BlockingQueue
	 * 3. Instances:
	 * 	ArrayDeque
	 *  LinkedBlockingDeque
	 */
	
	/**
	 * ConcurrentHashMap for concurrent access. 
	 * Uses LockStriping.
	 * Weakly Consistent Iterators: traverse eleemnts as they existed when iterator was constructed.
	 */
	
	/**
	 * CopyOnWriteArrayList: Concurrent list access.
	 * 1. Create and publish a new copy if it is modified.
	 */
	
	/**
	 * CopyOnWriteArraySet: Concurrent Set access.
	 * 1. Create and Publish a new copy if it is modified.
	 * 2. Do not throw ConcurrentModifiedException as they view the collection at the time they were created.
	 */
	
	/**
	 * 5.3.3 Dequeue/BlockingDeque/ArrayDeque/LinkedBlockingDeque
	 * 	 Threads have their own Deque, if it is empyt, they steam work from some other thread's deque's tail. 
	 *   This reduces thread contention.
	 */
	
	/**
	 * 5.4
	 * 
	 * 1. If a method is interrupted, if the interruption is caught, interrupt the current thread by Thread.interrupt. 
	 * 	  This restores the interrupt status of the thread.
	 */
	
	/**
	 * 5.5: Synchronizers: Objects that control the flow of threads based on their state.
	 * Examples:
	 * 	BlockingQueue
	 *  Semaphores
	 *  Latches			A synchronizer that can delay the execution of threads untill the latch reaches termination state.
	 *  			    Once latch reaches the termination state, it can not be reused.
	 *  CyclicBarriers
	 *  
	 */
	 
	 /**
	 * 5.5.1 CountDownLatch: Synchronizer that delays execution of threads till its termination condition.
							 A single thread can reduce the counter.
	 * 5.5.2 FutureTask: Synchronizer which blocks the get method till the task is finished.
	 * 5.5.3 Semaphore: Controls how many clients can access a resource.
	 * 5.5.4 CyclicBarrier: Synchronization aid that allows threads to wait till each thread reaches a specific condition.
	 *                      If one thread leaves the barrier prematurely, eg. an exception, all other threads get a barrier broken
						    exception (Does not happen in countdown latch)
							Can be reused after the barrier is broken.
							Thread specific.
	 */
}
