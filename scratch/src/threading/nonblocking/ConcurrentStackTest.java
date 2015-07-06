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
    static Long total = 0L;

    private static class Node<E> {
        E item;
        Node<E> next;
        private Node(E item) {
            this.item = item;
            this.next = null;
        }

        private Node<E> next() {
            return next;
        }
    }

    private final AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    public ConcurrentStackTest()
    {

    }

    public void push(E item)
    {
        Node<E> newItem = new Node<E>(item);
        Node<E> oldItem = null;
        do {
            oldItem = top.get();
            newItem.next = oldItem;
        } while (!top.compareAndSet(oldItem,newItem));
    }

    public E pop()
    {
        Node<E> item = null;
        Node<E> nextItem = null;
        do {
            item = top.get();
            if (item == null)
                return null;
            nextItem = item.next;
        } while (!top.compareAndSet(item,nextItem));
        return item.item;
    }
	public static void main(String[] args) throws InterruptedException
	{
	    // create 10 threads with latches.
	    int threadcount = 10;
	    CountDownLatch startLatch = new CountDownLatch(1);
	    CountDownLatch stopLatch = new CountDownLatch(threadcount);
	    ExecutorService executor = Executors.newCachedThreadPool();
	    ConcurrentStackTest<Integer> stack = new ConcurrentStackTest<Integer>();
	    for (int i = 0; i < threadcount/2; ++i)
	    {
	        executor.execute(new Runnable() {
	            @Override
                public void run() {
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
	                    stack.push(j);
	                }
	                synchronized(total) {
	                    total += j;
	                    stopLatch.countDown();
	                }
	            }
	        });
	    }

	    for ( int i = 0; i < threadcount/2; ++i)
	    {
	        executor.execute(new Runnable() {
	            @Override
                public void run() {
	                int j = 0;
	                try
	                {
	                    startLatch.await();
	                } catch (InterruptedException e)
	                {

	                }
	                while (!Thread.interrupted()) {
	                    ++j;
	                    stack.pop();
	                }
	                synchronized(total) {
	                    total += j;
	                    stopLatch.countDown();
	                }
	            }
	        });
	    }

	    startLatch.countDown();
	    Thread.sleep(100);
        executor.shutdownNow();
        stopLatch.await();
        System.out.println("Count: " + total);
	}


}
