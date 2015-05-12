/*
 * BoundedSemaphore.java
 *
 * Created on: May 12, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.Semaphores;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class BoundedSemaphore
{
    private final int _bound;
    private int _count;
    public BoundedSemaphore(int bound)
    {
        _bound = bound;
        _count = 0;
    }

    public synchronized void take() throws InterruptedException
    {
        while (_count == _bound)
        {
            wait();
        }
        ++_count;
        notifyAll();
    }

    public synchronized void release() throws InterruptedException
    {
        while (_count == 0)
        {
            wait();
        }
        --_count;
        notifyAll();
    }
}
