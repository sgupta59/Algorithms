/*
 * SendingThread.java
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
public class SendingThread
{
    public static void main(String[] args)
    {
        // create a simple semaphore
        SimpleSignalingSemaphore sema = new SimpleSignalingSemaphore();
        Thread t1 = new Thread(){@Override
        public void run() {
            while (true)
            {
                sema.take();
                System.out.println("Semaphore taken, i.e. signaled");
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }};
        t1.start();
        try
        {
            t1.join();
        }
        catch (InterruptedException e)
        {

        }
    }
}
