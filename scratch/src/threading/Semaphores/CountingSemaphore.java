/*
 * CountingSemaphore.java
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
public class CountingSemaphore
{
    private int _counter;

    public CountingSemaphore()
    {
        _counter = 0;
    }

    /**
     * Up (dijkstra), i.e. something there so notify
     * Acquire (java)
     * Take
     */
    public synchronized void take()
    {
        ++_counter;
        this.notifyAll();
    }

    public synchronized void release() throws InterruptedException
    {
        while (_counter == 0)
        {
            wait();
        }
        --_counter;
        return;
    }
}
