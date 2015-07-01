/*
 * ConcurrentStackTest.java
 *
 * Created on: Jul 1, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.nonblocking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class ConcurrentStackTest
{
    private static Long total = 0L;
    public static void main(String[] args) throws InterruptedException
    {
        int numthreads = 10;
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch startlatch = new CountDownLatch(1);
        CountDownLatch stopLatch = new CountDownLatch(10);
        for (int i = 0; i < numthreads/2; ++i)
        {
            executor.execute(new Runnable() {

                @Override
                public void run()
                {
                    int j = 0;
                    try
                    {
                        startlatch.await();
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    while (!Thread.interrupted())
                    {
                        ++j;
                    }
                    synchronized(total) {
                        total += j;
                        stopLatch.countDown();
                    }
                }});
        }

        for (int i = 0; i < numthreads/2; ++i)
        {
            executor.execute(new Runnable() { @Override
            public void run() {
                int j = 0;
                try
                {
                    startlatch.await();
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                while (!Thread.interrupted()) {
                    ++j;
                }
                synchronized(total) {
                    total += j;
                    stopLatch.countDown();
                }
            } });
        }

        startlatch.countDown();
        Thread.sleep(100);
        executor.shutdownNow();
        stopLatch.await();
        System.out.println(total);
    }
}
