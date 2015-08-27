/*
 * RadixSort.java
 *
 * Created on: Aug 27, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package sorting;

import java.util.LinkedList;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class RadixSort
{
    public static void radixSort(int[] arr, int ndigits)
    {
       int[] arr1 = new int[10];
       LinkedList<Integer>[] arra = (LinkedList<Integer>[])new LinkedList<?>[10];
       int index = 1;
       for (int i =1; i <= ndigits; ++i)
       {
           for (int j = 0; j < arr.length; ++j)
           {
               int rdx = getRadix(arr[j],i);
               LinkedList<Integer> bucket = arra[rdx];
               if (bucket == null)
               {
                   bucket = new LinkedList<Integer>();
                   arra[rdx] = bucket;
               }
               bucket.add(arr[j]);
           }
           int pos = 0;
           for (int idx = 0; idx < arra.length; ++idx)
           {
               LinkedList<Integer> bucket = arra[idx];
               if (bucket == null)
                   continue;
               for (Integer inti : bucket)
                   arr[pos++]=inti;
               arra[idx].clear();
           }
       }
    }

    public static int getRadix(int num, int idx)
    {
        return (int)(num/(Math.pow(10, idx-1))%10);
    }

    public static void countingSort1(int[] arr)
    {
        int[] out = new int[arr.length];
        // create an array for frequencies for 0-9 i.e. 10 items
        int[] count = new int[10];
        for (int i = 0; i < arr.length; ++i)
            ++count[arr[i]];
        // get the frequencies, each position is number of items as [count(i) - count(i-1)] items. this is one based.
        for (int i = 1; i < count.length; ++i)
            count[i] = count[i] + count[i-1];
        for (int i = 0; i < arr.length; ++i)
        {
            out[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
        }

        for (int i = 0; i < arr.length; ++i)
            arr[i] = out[i];
    }
    /**
     * http://www.programming-algorithms.net/article/40549/Counting-sort
     * @param arr
     */
    public static void countingSort(int[] arr)
    {
        // create an output array.
        int[] out = new int[arr.length];
        // find the min and max items
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i)
        {
            if (arr[i] < min)
            {
                min = arr[i];
                continue;
            }
            if (arr[i] > max)
                max = arr[i];
        }
        int[] count = new int[max-min+1];
        // now create the frequencies for eacy integer type.
        for (int i = 0; i < arr.length; ++i)
            ++count[arr[i]-min];
        // offset by 1
        --count[0];
        for (int i = 1; i < count.length; ++i)
            count[i] = count[i] + count[i-1];

        for (int i = 0; i < arr.length; ++i)
        {
            out[count[arr[i]-min]]=arr[i];
            --count[arr[i]-min];
        }
        for (int i = 0; i < arr.length; ++i)
            arr[i] = out[i];
    }
    public static void main(String[] args)
    {
        int[] arr = {239, 234, 879, 123, 258, 416, 317, 137, 225};
        int[] count = {9, 6, 6, 3, 2, 1, 4, 2, 9, 3};
        int[] count1 = {1, 1, 1, 1,1, 9,1, 1, 1, 1};
        int[] count2 = {9, 8, 7, 6,5, 4,3, 2, 1, 0};
        countingSort(count2);
        radixSort(arr,3);
    }
}
