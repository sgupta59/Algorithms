/*
 * Consumer.java
 *
 * Created on: Mar 25, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.blockingqueue;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class Consumer implements Runnable
{
    private boolean shutdown = false;
    private static int _id = 1;
    BlockingQueue<String> _queue;
    private final String _name;
    public Consumer(BlockingQueue<String> queue)
    {
        _name = "Consumer_"+ _id++;
        _queue = queue;
    }

    @Override
    public void run()
    {
        Thread.currentThread().setName(_name);
        while (!shutdown)
        {
            // create a new item and add it to the queue.

            try
            {
                System.out.println(Thread.currentThread().getName() + ": "  + _queue.get());
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public synchronized void shutdown()
    {
        shutdown = true;
    }
}
