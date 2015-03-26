/*
 * ThreadPoolTester.java
 *
 * Created on: Mar 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.threadpool;

/**
 *http://tutorials.jenkov.com/java-concurrency/thread-pools.html
 *
 * @author Sanjeev Gupta
 *
 */
public class ThreadPoolTester
{
    public static void main(String[] args)
    {
        // create a thread pool.
        ThreadPool tp = new ThreadPool(5,5);
        for (int i = 0; i < 2; ++i)
        {
        try
        {
            tp.execute((new Runnable() {

                @Override
                public void run()
                {
                    // TODO Auto-generated method stub
                    System.out.println("Thread: " + Thread.currentThread().getId());
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
            ));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }
}
