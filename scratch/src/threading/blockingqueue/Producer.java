/*
 * Producer.java
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
public class Producer implements Runnable
{
    BlockingQueue<String> _queue;
    private boolean _shutdown = false;
    private final String _name;
    private int _id=0;
    public Producer(BlockingQueue<String> q)
    {
        _name = "Producer_" + _id++;
        _queue = q;
    }

    @Override
    public void run()
    {
        int counter = 0;
        Thread.currentThread().setName(_name);
        while (!_shutdown)
        {
            String val = Integer.toString(++counter);
            try
            {
                System.out.println(Thread.currentThread().getName() + ": "  + val);
                _queue.put(val);
                Thread.sleep(5);
            }
            catch (InterruptedException e)
            {

            }
        }
    }
    public synchronized void shutdown()
    {
        _shutdown = true;
    }
}
