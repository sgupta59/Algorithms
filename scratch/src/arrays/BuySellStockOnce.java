/*
 * BuySellStock.java
 *
 * Created on: Nov 3, 2015
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
public class BuySellStockOnce
{
    /**
     * Compare each item in the array with every other subsequent item. Calculate the max of all those.
     * This means comparing each pair of items. There are n pairs for i = 0 (including i)
     * n-1 pairs for i = 1, n-2 pairs for i = 2... , 1 pair for n - (n-1) for a total of
     * n(n+1)/2
     * @param a
     * @return
     */
    public static int buy_sell_once_1(int[] a)
    {
        int maxprofit = 0;
        for (int i = 0; i < a.length; ++i)
        {
            for (int j = i; j < a.length; ++j)
            {
                int profit = a[j] - a[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }

    /**
     * Do a divide and conquer on the array.
     * 1. Find max profit on the left sub array.
     * 2. Find the max profit on the right sub array.
     * 3. find min on left and max on right sub array and get the profit.
     * 4. the profit for the combined array is the max of 1 or 2 or 3.
     *
     * This takes T(n) = 2T(n/2) + cn  = nlogn time.
     * Space complexity is logn as at each time, a stack is created for n/2
     *
     * @param a
     * @return
     */
    public static int buy_sell_once_2(int[] a, int lo, int hi)
    {
        int maxprofit = 0;
        // base case takes T(1) time.
        if (hi-lo < 1)
            return maxprofit;

        int mid = lo + (hi-lo)/2;
        // Divide step: 2T(n/2)
        int profitLeft = buy_sell_once_2(a, lo, mid);
        int profitRight = buy_sell_once_2(a, mid+1, hi);

        // conquer step T(n) step.
        // find min on the left sub array
        int leftmin = Integer.MAX_VALUE;
        for (int i = lo; i <= mid; ++i)
            leftmin = Math.min(leftmin, a[i]);

        int rightmax = Integer.MIN_VALUE;
        for (int i = mid+1; i <= hi; ++i)
            rightmax = Math.max(rightmax,a[i]);

        // find max on the right sub array.
        int profitCombined = rightmax - leftmin;

        if (profitCombined > profitLeft && profitCombined > profitRight)
            return profitCombined;
        return profitLeft > profitRight ? profitLeft : profitRight;
    }

    /**
     * Optimized version, keep track of the min value of the array at each step.
     * Compare A[i] - min and see if it is greater than the max profit.
     * O(n) time
     * @param a
     * @return
     */
    public static int buy_sell_once_3(int[] a)
    {
        int min = a[0];
        int maxprofit = 0;
        for (int i = 1; i < a.length; ++i)
        {
            maxprofit = Math.max(maxprofit, a[i]-min);
            min = Math.min(min,a[i]);
        }
        return maxprofit;
    }
    public static void main(String[] args)
    {
        int[] a = {2, 7, 1, 8, 2, 8, 4, 5, 9, 0, 4, 5};
        int profit_2 = buy_sell_once_2(a, 0, a.length-1);
        int profit_1 = buy_sell_once_1(a);

    }
}
