/*
 * Test.java
 *
 * Created on: Aug 19, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

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
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

            @Override
            public int compare(Integer lhs, Integer rhs)
            {
                // TODO Auto-generated method stub
                if (lhs < rhs) return -1;
                if (lhs == rhs) return 0;
                return 1;
            }});
        pq.offer(1);
        pq.offer(2);
        pq.offer(3);
        while (pq.isEmpty()==false)
        {
            System.out.println("Output: " + pq.poll());
        }

    }
}
