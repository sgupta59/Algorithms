/*
 * Test.java
 *
 * Created on: May 18, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package lambdas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class Test
{
    public static void main(String[] args)
    {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync( ( ) -> {
            try
            {

                Thread.sleep(10000);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "100";
                } );
        try
        {
            System.out.println( "get  " + cf.get() );
        }
        catch (InterruptedException | ExecutionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
