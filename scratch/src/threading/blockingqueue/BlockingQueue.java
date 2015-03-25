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
 *
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
        boolean isEmpty = isEmpty();
        linkedList.add(t);
        if (isEmpty)
        {
            notifyAll();
        }
    }

    public synchronized T get() throws InterruptedException
    {
        while (isEmpty())
        {
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting in get");
            wait();
        }
        boolean isFull = isFull();
        T t = linkedList.poll();
        if (isFull)
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
