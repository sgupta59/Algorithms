/*
 * FindMissingPositive.java
 *
 * Created on: Mar 23, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package arrays;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class FindMissingPositive
{
    /**
     * Put all negative numbers at the front of the array.
     * @param arr
     * @return
     */
    public static int segegrate(int[] arr)
    {
        int j = 0;
        for (int i = 0; i < arr.length; ++i)
        {
            if (arr[i] < 0)
            {
                /** Binary based swap without using a temporary */
                arr[i] = arr[i] ^ arr[j];
                arr[j] = arr[i] ^ arr[j];
                arr[i] = arr[i] ^ arr[j];
                ++j;
            }
        }
        return j;
    }
    public static int missingNumberConstantSpace(int[] arr)
    {
        // move all numbers to the begining and get the start index where the positive numbers start
        int start = segegrate(arr);

        // mark all indices corresponding to arr[idx] as negative
        for (int i = start; i < arr.length; ++i)
        {
            // get the value at index i.
            int idx = Math.abs(arr[i])+start-1;
            // get the current value at that index, make sure idx is within range.
            int val = idx < arr.length ? arr[idx] : 0;
            if (idx < arr.length && val > 0)
            {
                // set the value as negative if it is positive.
                arr[idx] = -1*arr[idx];
            }
        }
        // return the first non negative index
        for (int idx = start; idx < arr.length; ++idx)
        {
            if (arr[idx] > 0)
                return idx-start+1;
        }
        return 0;
    }
    // Using counting sort type method. but requires O(N) space.
    // for every arr[i] value, update a counter in the counter array to count number of arr[i]
    // values
    public static int missingNumberNSpace(int[] arr)
    {
        int[] holes = new int[arr.length];
        for (int idx = 0; idx < arr.length; ++idx)
        {
            if (arr[idx] >= 0 && arr[idx] < arr.length)
            {
                if (arr[idx] == 0)
                    ++holes[arr[idx]];
                else
                    ++holes[arr[idx]-1];
            }
        }
        for (int idx = 0; idx < holes.length; ++idx)
        {
            if (holes[idx] == 0)
                return idx+1;
        }
        return 0;
    }


    public static void main(String[] args)
    {
        int[] arr = {2, 3, -7, 6, 8, 1, -10, 15};
        int missing1 = missingNumberConstantSpace(arr);
        int missing = missingNumberNSpace(arr);
        System.out.println("Missing number: " + missing);
    }
}
