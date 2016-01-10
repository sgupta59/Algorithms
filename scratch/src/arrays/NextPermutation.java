/*
 * NextPermutation.java
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
public class NextPermutation
{
    public static String nextPermutation(int[] n)
    {
        if (n.length == 1)
            return "";
        int length = n.length;
        // starting from end, find the first non increasing item
        int crossover = length-1;
        for (; crossover > 0; --crossover)
        {
            if (n[crossover] > n[crossover-1])
                break;
        }
        if (crossover -1 == 0)
            return "";
        // find the first item larger than n[crossover] from the end.
        int gt = length-1;
        for (; gt > crossover; --gt)
        {
            if (n[gt] > n[crossover])
                break;
        }
        // swap crossover and gt and reverse the
        int tmp = n[crossover-1];
        n[crossover-1] = n[gt];
        n[gt] = tmp;
        // reverse starting at crossover
        int end = n.length-1;
        while (crossover < end)
        {
            tmp = n[crossover];
            n[crossover] = n[end];
            n[end] = tmp;
            ++crossover;
        }
        return "";

    }
    public static void main(String[] args)
    {
        int[] n = {0, 3, 4, 2, 1};
        nextPermutation(n);
    }
}
