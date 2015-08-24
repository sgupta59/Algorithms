/*
 * LocalMinimiaArray.java
 *
 * Created on: Aug 13, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package sedgewick.Fundamentals;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class LocalMinimiaArray
{
    private static int findMinima_doit(int[] a, int lo, int hi)
    {
        int mid = lo + (hi-lo)/2;

        if ((mid == 0 || a[mid] <= a[mid-1]) && (mid == a.length-1 ||a[mid] <= a[mid+1]))
            return a[mid];
        if (a[mid] <= a[mid-1])
            return findMinima_doit(a, mid+1,hi);
        return findMinima_doit(a, lo, mid-1);
    }
    public static int findMinima(int[] a)
    {
        return findMinima_doit(a, 0, a.length-1);
    }
    public static void main(String[] args)
    {
        int[] a = {64, 14, 52, 27, 71, 19, 63, 1, 16, 57};
        System.out.println(findMinima(a));
    }
}
