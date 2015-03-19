package  segmentedtrees;

/*
 * SegmentTreeSum.java
 *
 * Created on: Mar 3, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */


/**
 * https://kartikkukreja.wordpress.com/2014/11/09/a-simple-approach-to-segment-trees/
 * https://kartikkukreja.wordpress.com/2015/01/10/a-simple-approach-to-segment-trees-part-2/
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 *
 * @author Sanjeev Gupta
 *
 */
public class SegmentTreeSum
{
    public static int getMid(int s, int e)
    {
        return s + (e -s)/2;
    }
    public static double getLogBase2(int x)
    {
        return Math.log(x)/Math.log(2);
    }
    /* A recursive function to update the nodes which have the given index in
    their range. The following are parameters
     st, index, ss and se are same as getSumUtil()
     i    --> index of the element to be updated. This index is in input array.
    diff --> Value to be added to all nodes which have i in range
     */
    public static void updateValueUtil(int[]st, int ss, int se, int i, int diff, int index)
    {
        // Base Case: If the input index lies outside the range of this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then update the value
        // of the node and its children
        st[index] = st[index] + diff;
        if (se != ss)
        {
            int mid = getMid(ss, se);
            updateValueUtil(st, ss, mid, i, diff, 2*index + 1);
            updateValueUtil(st, mid+1, se, i, diff, 2*index + 2);
        }
    }
    /*  A recursive function to get the sum of values in given range of the array.
    The following are parameters for this function.

    st    --> Pointer to segment tree
    index --> Index of current node in the segment tree. Initially 0 is
             passed as root is always at index 0
    ss & se  --> Starting and ending indexes of the segment represented by
                 current node, i.e., st[index]
    qs & qe  --> Starting and ending indexes of query range
    */
    public static int getSumUtil(int[]st, int ss, int se, int qs, int qe, int index)
    {
        // If segment of this node is a part of given range, then return the
        // sum of the segment
        if (qs <= ss && qe >= se)
            return st[index];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return getSumUtil(st, ss, mid, qs, qe, 2*index+1) +
            getSumUtil(st, mid+1, se, qs, qe, 2*index+2);
    }
    public static int getSum(int[] st, int n, int qs, int qe)
    {
        if (qs < 0 || qe > st.length-1 || qs > qe)
        {
            System.out.println("Invalid range");
        }
        return getSumUtil(st, 0,n-1, qs, qe, 0);
    }
    public static int constructSTUtil(int[] arr, int ss, int se, int[] st, int si)
    {
        if (ss == se)
        {
            st[si] = arr[ss];
            return arr[ss];
        }
        int mid = getMid(ss, se);
        int left = constructSTUtil(arr, ss, mid, st, si*2+1);
        int right = constructSTUtil(arr, mid+1, se, st, si*2+2);
        st[si] =  left + right;

        return st[si];
    }
    public static int getSegmentTreeSize(int N) {
        int size = 1;
        for (; size < N; size <<= 1)
        {
            System.out.println("test");
        }
        return size << 1;

    }
    public static int[] constructST(int[] arr)
    {
        int leaves = arr.length;
        int totalnodes1 = getSegmentTreeSize(leaves);
        // heingt is ceil(logn) where log is base 2 log
        int height = (int)Math.ceil(getLogBase2(leaves));
        int totalnodes = 2*(int)Math.pow(2.0, height)-1;
        int[] st = new int[totalnodes];
        constructSTUtil(arr, 0, arr.length-1, st, 0);
        return st;
    }
    public static void updateValue(int[] arr, int[] st, int i, int new_val)
    {
        if (i < 0 || i >= arr.length)
            return ;
        int diff = new_val-arr[i];
        arr[i] = new_val;
        updateValueUtil(st,0,arr.length-1,i,diff,0);
    }
    public static void main(String[] args)
    {
        int[] arr = {1,3,5,7, 9, 11};
        int[] st = constructST(arr);
        int sum = getSum(st,arr.length, 1,3);
        System.out.println("Sum: " + sum);
        updateValue(arr,st,1,10);
        sum = getSum(st,arr.length, 1,3);
        System.out.println("Sum: " + sum);
    }
}
