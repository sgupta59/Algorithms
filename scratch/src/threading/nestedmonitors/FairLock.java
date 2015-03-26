/*
 * FairLock.java
 *
 * Created on: Mar 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.nestedmonitors;

import java.util.LinkedList;

/**
 *  http://tutorials.jenkov.com/java-concurrency/nested-monitor-lockout.html
 *
 *  A fair locking class with a nested lock that can cause threads to lock. This will happen as
 *  if one thread tries to lock this Lock and goes into the wait mode in the obj.wait() point
 *  and another thread tries to unlock this lock, the other thread will block as it can not aquire the "this" lock as it is locked
 *  by the first thread.
 *
 *  Thread1.lock().
 *  Thread2.lock() --> waiting for the lock in the obj.wait() point
 *  Thread1.unlock() --> blocks as it can not get the "this" monitor lock
 *
 * @author Sanjeev Gupta
 *
 */
public class FairLock
{
    private class QueueObject {}
    private  boolean _isLocked = false;
    private  Thread _lockingThread = null;
    private final LinkedList<QueueObject> _waitingThreads = new LinkedList<QueueObject>();
    public FairLock()
    {

    }


    public void lock() throws InterruptedException
    {
        // create a queue object
        QueueObject obj = new QueueObject();
        // Lock down the whole class first.
        synchronized(this)
        {
            // add the object to the queue list
            _waitingThreads.add(obj);
            // if the lock is already aquired or if the waiting thread returns a lock object different than obj
            // this means some other object is in teh process of acquiring this lock or has acquired it.
            while (_isLocked || _waitingThreads.peek() != obj)
            {
                // synchronoized on the obj as we are calling wait/notify/notifyAll on it.
                synchronized(obj)
                {
                    try
                    {
                        obj.wait();
                    }
                    catch (InterruptedException e)
                    {
                        // on an exception, remove the object from the object queue
                        _waitingThreads.remove(obj);
                       throw e;
                    }
                }
            }
            // thread will aquire the lock, remve the object from the queue
            _waitingThreads.remove(obj);
            // set the flag
            _isLocked = true;
            // store the thread id.
            _lockingThread = Thread.currentThread();
        }
    }

    public synchronized void unlock()
    {
        /**
         * If this thread is not same as the locking thread, throw an IllegalMonitorState exception/
         */
        if (this._lockingThread != Thread.currentThread())
        {
            throw new IllegalMonitorStateException("Not locking thread");
        }
        // unset the flags
        _isLocked = false;
        _lockingThread = null;
        // if there are other waiting threads
        if (_waitingThreads.size() > 0)
        {
            // get the first object
            QueueObject obj = _waitingThreads.poll();
            synchronized(obj)
            {
                // done in synchronized context as the notify call is made
                obj.notify();
            }
        }
    }
}
