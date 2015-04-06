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
public class SendingThread extends Thread
{
    SimpleSignalingSemaphore _semap;

    public SendingThread(SimpleSignalingSemaphore semap)
    {
        _semap = semap;
    }

    @Override
    public void run()
    {
        while (true)
        {
            _semap.take();

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
    }

    public static void main(String[] args)
    {
        SimpleSignalingSemaphore  sema = new SimpleSignalingSemaphore();
        ReceivingThread rth = new ReceivingThread(sema);
        SendingThread sth = new SendingThread(sema);
        rth.start();
        sth.start();
        try
        {
            rth.join();
            sth.join();
        }
        catch (InterruptedException e)
        {

        }

    }
}
