/*
 * SumOfSubsets.java
 *
 * Created on: Mar 6, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package scratch.backtracking;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class SumOfSubsets
{
    public static int  W = 13;
    public static int total =0;
    public static int[] include =null;
    public static boolean isPromising(int[] w,int idx, int cweight, int remaining)
    {
        if ((cweight+remaining >= W) && (cweight==W || (cweight + w[idx+1] <= W) ))
        {
            return true;
        }
        return false;
    }
    public static void sumofsubsets(int[]w, int idx, int cweight, int remaining)
    {
        if (isPromising(w, idx, cweight, remaining))
        {
            if (cweight == W)
            {
                System.out.println("Solution: " + 0 + " to " + idx);
                return;
            }
            include[idx+1]=1;
            sumofsubsets(w,idx+1,cweight+w[idx+1],remaining-w[idx+1]);
            include[idx+1]=0;
            sumofsubsets(w,idx+1,cweight, remaining-w[idx+1]);
        }
    }
    public static void main(String[] args)
    {
        int[] w = {0,3,4,5,6};
        include = new int[w.length];
        for (int idx  = 0; idx < w.length; ++idx)
            total += w[idx];
        sumofsubsets(w, 0, 0,total);

    }
}
