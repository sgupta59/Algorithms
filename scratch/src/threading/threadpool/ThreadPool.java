/*
 * ThreadPool.java
 *
 * Created on: Mar 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.threadpool;

import java.util.ArrayList;
import java.util.List;

import threading.blockingqueue.BlockingQueue;

/**
 *http://tutorials.jenkov.com/java-concurrency/thread-pools.html
 *
 * @author Sanjeev Gupta
 *
 */
public class ThreadPool
{

    private final BlockingQueue<Runnable> _taskqueue ;
    private final List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean _isStopped = false;
    public ThreadPool(int maxthreads, int maxtasks)
    {
        _taskqueue = new BlockingQueue<Runnable>(maxtasks);
        for (int idx = 0; idx < maxthreads; ++idx)
        {
            threads.add(new PoolThread(_taskqueue));
        }
        for (PoolThread thread : threads)
        {
            thread.start();
        }
    }

    public synchronized void execute(Runnable task) throws Exception
    {
        if (_isStopped)
            throw new IllegalStateException("Threadpool is stopped");
        _taskqueue.put(task);
    }
    public synchronized void stop()
    {
        _isStopped = true;
    }
}
