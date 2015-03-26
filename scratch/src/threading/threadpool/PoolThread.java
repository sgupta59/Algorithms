/*
 * PoolThread.java
 *
 * Created on: Mar 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.threadpool;

import threading.blockingqueue.BlockingQueue;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class PoolThread extends Thread
{
    private final BlockingQueue<Runnable> _runnableQueue;
    private boolean _stop = false;
    public PoolThread(BlockingQueue<Runnable> runnableQueue)
    {
        _runnableQueue = runnableQueue;
    }

    @Override
    public void run()
    {
        while (!isStopped())
        {
            try
            {
                Runnable r = _runnableQueue.get();
                r.run();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doStop(boolean stop)
    {
        _stop = stop;
        this.interrupt();
    }

    public synchronized boolean isStopped()
    {
        return _stop;
    }
}
