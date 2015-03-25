/*
 * FillArrayWithNumbers.java
 *
 * Created on: Mar 25, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package backtracking;

/**
 *http://www.geeksforgeeks.org/fill-two-instances-numbers-1-n-specific-way/
 *
 * @author Sanjeev Gupta
 *
 */
public class FillArrayWithNumbers
{
    public static boolean fillArray(int[] array, int pos, int num)
    {
        // check if the array has been filled.
        if (array[pos] != 0 || array[pos+num+1] != 0)
            return false;
        // fill the array at pos and pos+num+1
        array[pos] = num; array[pos+num+1] = num;
        for (int i = num-1; i > 0; --i)
        {
            if (fillArray(array,pos+1,i))
                return true;
            array[i] = 0; array[i+num+1]=0;
        }
        array[pos] = 0; array[pos+num+1]=0;
        return false;
    }
    public static void main(String[] args)
    {
        int n = 3;
        int[] arr = new int[n*2];
        fillArray(arr,0,n);

    }
}
