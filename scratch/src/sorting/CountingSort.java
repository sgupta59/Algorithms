/*
 * CountingSort.java
 *
 * Created on: Oct 14, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package sorting;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class CountingSort
{
    private static void testCountingSort()
    {
        int[] a = {5, 4, 5, 4, 3, 2, 2, 1};
        countingSort(a);
    }

    private static void testRadixSort()
    {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(arr);
    }
    private static int findMax(int[] a )
    {
        int max = a[0];
        for (int i = 1; i < a.length; ++i)
            if (a[i] > max)
                max = a[i];
        return max;
    }
    public static void radixSort(int[] a)
    {
        int maxNum = findMax(a);
        for (int exp = 1; maxNum/exp > 0; exp *= 10)
        {
            countingSort(a, exp);
        }
    }
    public static void countingSort(int[] a, int exp)
    {
        // create an array for counts
        int c[] = new int[10];
        // create an array for the new values
        int[] b = new int[a.length];

        // first update the count array
        //
        for (int i = 0; i < a.length; ++i)
        {
            ++c[(a[i]/exp)%10];
        }
        for (int i = 1; i < c.length; ++i)
        {
            c[i] += c[i-1];
        }

        for (int i = a.length-1; i >=0; --i)
        {
            int idx = a[i];
            int v = c[(idx/exp)%10];
            b[v-1] = idx;
            --c[(idx/exp)%10];
        }
        System.arraycopy(b, 0, a, 0, a.length);
    }
    public static void countingSort(int[] a)
    {
        // create an array for counts
        int c[] = new int[10];
        // create an array for the new values
        int[] b = new int[a.length];

        // first update the count array
        //
        for (int i = 0; i < a.length; ++i)
        {
            ++c[a[i]];
        }
        for (int i = 1; i < c.length; ++i)
        {
            c[i] += c[i-1];
        }

        for (int i = a.length-1; i >=0; --i)
        {
            int idx = a[i];
            int v = c[idx];
            b[v-1] = idx;
            --c[idx];
        }
        System.arraycopy(b, 0, a, 0, a.length);
    }
    public static void main(String[] args)
    {
        testRadixSort();
        testCountingSort();
    }
}
