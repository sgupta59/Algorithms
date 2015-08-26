/*
 * MedianTest.java
 *
 * Created on: Aug 25, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package sedgewick.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class MedianTest
{
    public static int getMedian(int number, int median, PriorityQueue<Integer> minPQ, PriorityQueue<Integer> maxPQ)
    {
        // if this the first item then return it.
        if (minPQ.isEmpty())
        {
            minPQ.offer(number);
            return minPQ.peek();
        }
        if (maxPQ.isEmpty())
        {
            if (number < median)
            {
                maxPQ.offer(number);
            }
            else
            {
                maxPQ.offer(minPQ.poll());
                minPQ.offer(number);
            }
            return (maxPQ.peek()+minPQ.peek())/2;
        }
        if (number > minPQ.peek())
            minPQ.offer(number);
        else
            maxPQ.offer(number);
    }
    public static void main(String[] args)
    {
        int[] stream = {3, 7, 4, 1, 2, 6, 5};
       // create a min priority queue
       PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(5);
       // create a max priority queue
       PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(5, new Comparator<Integer>()
           {
               @Override
            public int compare(Integer left, Integer right)
               {
                   int diff = left.compareTo(right);
                   if (diff > 0) return -1;
                   if (diff < 0) return 1;
                   return 0;
               }
       });
       int median = 0;
       for  (int i = 0; i < stream.length; ++i)
       {
            median = getMedian( i, median, minPQ, maxPQ);
       }
       for (int i = 0; i < stream.length; ++i)
       {
           minPQ.offer(stream[i]);
           maxPQ.offer(stream[i]);
       }
       for (int i = 0; i < 5; ++i)
       {
           System.out.println("Min: " + minPQ.poll());
           System.out.println("Max: " + maxPQ.poll());
       }
    }
}
