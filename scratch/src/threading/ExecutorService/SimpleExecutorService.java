/*
 * ExecutorService.java
 *
 * Created on: Apr 2, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading.ExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple executor service.
 *
 * NOTES: Shutdown method returns as soon as it is invoked, but it waits for the run method to finish before it shuts down
 *
 * @author Sanjeev Gupta
 *
 */
public class SimpleExecutorService
{
    public static AtomicInteger _id = new AtomicInteger(0);
    public static void simpleExecuteTest()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int idx = 0; idx < 20; ++idx)
        {
            try
            {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int localid = _id.getAndIncrement();

                    System.out.println("Asynchronous Task Start " + localid++);
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("Asynchronous Task End "+ localid++);
                }
            });
            }
            catch (RejectedExecutionException e)
            {
                System.out.println(e.getLocalizedMessage());
            }
        }
        executorService.shutdown();
        try
        {
            executorService.awaitTermination(2000, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("executorService shutdown");
    }

    public static void simpleSubmitTest()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous Task Start");


                Thread.currentThread().interrupt();
                try
                {

                    Thread.sleep(10000);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Asynchronous Task End");
            }
        });

        // Shutdown the service, returns immediately.

        executorService.shutdown();

        // Do a get on the future task, waits for the task to finish
        // This is a blocking call.
        // Question: When is obj not null?
        try
        {
            Object obj = future.get();
            System.out.println("Received: " + obj);
        }
        catch (InterruptedException | ExecutionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("executorService shutdown");
    }
    public static void simpleCallableTest()
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Asynchronous Task Start");


                //Thread.currentThread().interrupt();
                try
                {

                    Thread.sleep(10000);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Asynchronous Task End");
                return "Task Complete";
            }
        });

        // Shutdown the service, returns immediately.

        executorService.shutdown();

        // Do a get on the future task, waits for the task to finish
        // This is a blocking call.
        // Question: When is obj not null?
        try
        {
            Object obj = future.get();
            System.out.println("Received: " + obj);
        }
        catch (InterruptedException | ExecutionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("executorService shutdown");
    }
    public static void main(String[] args)
    {
        simpleExecuteTest();
        simpleCallableTest();
    }
}
