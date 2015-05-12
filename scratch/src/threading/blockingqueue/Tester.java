/*
 * Tester.java
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
public class Tester
{
    public static void main(String[] args)
    {
        // Create a queue
        //BlockingQueue<String> queue = new BlockingQueue<String>(10);
    	BlockingQueueConditioned<String> queue = new BlockingQueueConditioned<String>(10);
        Thread t1 = new Thread(new Consumer(queue));
        Thread t2 = new Thread(new Producer(queue));
        new Thread(new Consumer(queue)).start();
        t1.start();
        t2.start();
        try
        {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
