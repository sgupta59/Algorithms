/*
 * SimpleSemaphore.java
 *
 * Created on: Apr 1, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.signaling;

/**
 *  http://tutorials.jenkov.com/java-concurrency/semaphores.html
 *
 *  This one will be a signaling mechanism. One thread will set a boolean flag to true and signal that it has taken a lock.
 *  Another thread will wait for the signal to be set to tru and then return, so
 *
 *  Thread1.take() --> Takes
 *  Thread2.release() --> waits till a signal is set and then does a relases?
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class SimpleSignalingSemaphore
{
    private boolean _signal = false;
    private int _counter = 0;
    public SimpleSignalingSemaphore()
    {
        _signal = false;
    }

    public synchronized void take()
    {
        _signal = true;
        ++_counter;
        System.out.println("Semaphore taken, count: " + _counter + ", THREAD: " + Thread.currentThread().getName());
        this.notify();
    }

    public synchronized void release() throws InterruptedException
    {
        while (!_signal) this.wait();
        --_counter;
        System.out.println("Semaphore released, count: " + _counter + ", THREAD: " + Thread.currentThread().getName());
        _signal = false;
    }
}
