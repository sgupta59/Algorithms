/*
 * ConcurrentStackTest.java
 *
 * Created on: Jul 1, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.nonblocking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class ConcurrentStackTest<E>
{
	private static class Node<E> {
		E item;
		Node<E> next;
		private Node(E item)
		{
			this.item = item;
		}
		
		private Node<E> next()
		{
			return next;
		}
	}
	
	
	public ConcurrentStackTest()
	{
		
	}
	Node<E> top = null;
	
	public synchronized void push(E item)
	{
		Node<E> newTop = new Node<E>(item);
		if (top == null)
		{
			top = newTop;
			return;
		}
		newTop.next = top;
		top = newTop;
	}
	
	public synchronized E pop()
	{
		if (top == null)
			return null;
		Node<E> newTop = top.next();
		Node<E> orig = top;
		top = newTop;
		return orig.item;
	}
	public static Long total = 0L;
    public static void main(String[] args) throws InterruptedException
    {
    	// 1. create 10 threads, 5 will be producers, 5 will be consumers.
    	// 2. have the producer threads wait on a countdown latch till everything starts.
    	// 3. have the consumer threds wait on a countdown latch till everything starts.
    	int threadcount = 10;
    	final CountDownLatch startLatch = new CountDownLatch(1);
    	final CountDownLatch stopLatch = new CountDownLatch(threadcount);
    	ExecutorService executor = Executors.newCachedThreadPool();
    	final ConcurrentStackTest<Integer> stackTest = new ConcurrentStackTest<Integer>();
    	for (int i = 0; i < threadcount/2; ++i)
    	{
    		executor.execute(new Runnable() {public void run() {
    				int j = 0;
    				try
    				{
    					startLatch.await();
    				}
    				catch (InterruptedException e)
    				{
    					
    				}
    				while (!Thread.interrupted())
    				{
    					++j;
    					stackTest.push(j);
    				}
    				synchronized(total) {
    					total += j;
    					stopLatch.countDown();
    					System.out.println("Latch Count: " + stopLatch.getCount());
    				}
    				
    			};
    		});
    	}
    	
    	for (int i = 0; i < threadcount/2; ++i)
    	{
    		executor.execute(new Runnable() {
    			public void run() 
    			{
    				int j = 0;
    				try
    				{
    					startLatch.await();
    				}
    				catch (InterruptedException e)
    				{
    					
    				}
    				while (!Thread.interrupted()) {
    					++j;
    					stackTest.pop();
    				}
    				synchronized(total) {
    					total += j;
    					stopLatch.countDown();
    					System.out.println("Latch Count: " + stopLatch.getCount());
    				}
    			}
    		});
    	}
    	
    	startLatch.countDown();
    	Thread.sleep(100);
    	executor.shutdownNow();
    	stopLatch.await();
    	System.out.println("Counter: " + total);
    }
}
