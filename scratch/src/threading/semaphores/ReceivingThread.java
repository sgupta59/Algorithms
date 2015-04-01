/*
 * ReceivingThread.java
 *
 * Created on: Apr 1, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.semaphores;

import threading.semaphores.simple.SimpleSignalingSemaphore;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class ReceivingThread extends Thread
{
    private final SimpleSignalingSemaphore _sem;
    public ReceivingThread(SimpleSignalingSemaphore sem)
    {
        _sem = sem;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                _sem.release();
                Thread.sleep(2000);
                System.out.println("Receiving thread released a semaphore");
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
