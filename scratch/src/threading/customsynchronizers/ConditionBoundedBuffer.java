/*
 * ConditionBoundedBuffer.java
 *
 * Created on: Jul 6, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.customsynchronizers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * ConditionBoundedBuffer
 * <p/>
 * Bounded buffer using explicit condition variables
 *
 * http://jcip.net.s3-website-us-east-1.amazonaws.com/listings.html
 * 14.11
 *
 * @author Brian Goetz and Tim Peierls
 *
 */
public class ConditionBoundedBuffer<E>
{
    private final int size;
    private final E[] items;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private int head;
    private int tail;
    private int count;
    public ConditionBoundedBuffer(int size)
    {
        this.size = size;
        items = (E[])new Object[size];
    }

    public void put(E item) throws InterruptedException
    {
        // lock this object first.
        lock.lock();
        try
        {
            // if the buffer is full, then wait till it becomes empty.
            while (count == size)
                notFull.await();

            // add an item to the buffer and update pointers
            items[tail++]=item;
            if (tail == size)
                tail = 0;
            notEmpty.signal();
            // now signal that this buffer has stuff in it.
        }
        finally
        {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException
    {
        // lock this object.
        lock.lock();
        // if there is anything to take,i.e. block till there is something to take.
        try
        {
            while (count == 0)
                notEmpty.await();
            E item = items[head];
            items[head] = null;
            ++head;
            if (head == size)
                head = 0;
            --count;
            notFull.signal();
            return item;
        }
        finally
        {
            // unlock this object.
            lock.unlock();
        }
    }


}
