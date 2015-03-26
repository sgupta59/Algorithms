/*
 * BlockingQueue.java
 *
 * Created on: Mar 25, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.blockingqueue;

import java.util.LinkedList;

/**
 * Source: http://tutorials.jenkov.com/java-concurrency/blocking-queues.html
 *
 * @author Sanjeev Gupta
 *
 */
public class BlockingQueue<T> {

    private final LinkedList<T> linkedList = new LinkedList<T>();
    private final int limit;
    public BlockingQueue(int limit)
    {
        this.limit = limit;
    }

    public synchronized void put(T t) throws InterruptedException
    {
        while (isFull())
        {
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting in put");
            wait();
        }

        linkedList.add(t);
        // send out a notifiy signal as soon as an item is added.
        // if a put thread is waiting, then it will do a isFull test in teh while loop and go back to waiting.
        // a get thread that is waiting will get the item as soon as possible.
        notifyAll();
    }

    public synchronized T get() throws InterruptedException
    {
        while (isEmpty())
        {
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting in get");
            wait();
        }

        T t = linkedList.poll();
        // send out a notify signal.
        // if a put thread is waiting, it will get the control.
        // if a get thread is waiting, if the queue becomes empty, the isEmpty() test will keep the waiting thread
        // in the while loop
        notifyAll();
        return t;
    }
    private boolean isEmpty()
    {
        return linkedList.size() == 0;
    }
    private boolean isFull()
    {
        return linkedList.size() == limit;
    }
}
