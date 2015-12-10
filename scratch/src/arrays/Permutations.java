/*
 * Permutations.java
 *
 * Created on: Nov 11, 2015
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
public class Permutations
{
    /**
     * Return character representation of a remainder based on the base.
     *
     * base 10 --> 0-9 is the remainder so return (char)(rem+'0'), '0' is
     * base 8  --> 0-7 so return rem + '0'
     * base 16 --> 0 - 15. 10 is A
     *             0 - 9 --> rem + '0'
     *             10 - 15 --> (rem - 10) + 'A' as
     * @param rem
     * @param base
     * @return
     */
    public static char getChar(int rem, int base)
    {
        if (rem > 9)
            return (char)(rem - 10 + 'A');
        return (char)(rem + '0');
    }
    public static String intToString(int n, int base)
    {
        if (n == 0)
        {
            return new StringBuffer().append((char)(0+'0')).toString();
        }
        boolean sign = false;
        if (n < 0)
        {
            n = -1*n;
            sign = true;
        }
        StringBuilder sbr = new StringBuilder();

        while (n != 0)
        {
            int rem = n % base;

            char c1 = getChar(rem, base);
            sbr.insert(0, c1);
            n = n/base;
        }
        if (sign)
            sbr.insert(0, '-');

        return sbr.toString();
    }
    public static int findNextPermutation(int perm)
    {

        return 0;
    }
     /* -
     * @param a
     * @param i
     * @param j
     */
    public static void swap(int[] a, int i, int j)
    {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void permute(int[] a, int i)
    {
        if (i == a.length)
        {
            for (int k = 0; k < a.length; ++k)
                System.out.print(a[k] + " ");
            System.out.println("");
        }
        for (int j = i; j < a.length; ++j)
        {
            swap(a, i, j);
            permute(a, i+1);
            swap(a, i,j);
        }
    }
    public static void permute(boolean[] state, StringBuilder ans, int idx)
    {
        if (ans.length() == state.length)
        {
            System.out.println(ans);
        }
        for (int i = idx; i < state.length; ++i)
        {
            ans.insert(ans.length(), (char)(i+1+'0'));
            permute(state, ans, i+1);
            ans.delete(ans.length()-1, ans.length());
        }
    }
    public static void printAllPermutations(int n)
    {
        int[] ids = new int[n];
        for (int i = 0; i < ids.length; ++i)
            ids[i] = i+1;
        // to do a permutioat
        String value = intToString(n, 10);
        String start = "1234";
        boolean[] state = new boolean[start.length()];
        StringBuilder ans = new StringBuilder();
        permute(state, ans, 0);
        permute(ids, 0);
    }
    static int partition(int[] a, int lo, int hi)
    {
        int i = lo; int j = hi+1;
        int pivot = lo;
        int p = a[lo];
        while (i <= j)
        {
            while (i <= hi && a[i++] < p);
            while (j >= lo && a[--j] > p);
            if (i >= j)
                break;
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;

        }
    }
    public static void main(String[] args)
    {
        String s = intToString(1567,16);
        System.out.println("Int: " + 1567 + ", base 10: " + intToString(1567,10));
        System.out.println("Int: " + 1567 + ", base 8: " + intToString(1567,8));
        System.out.println("Int: " + 1567 + ", base 16: " + intToString(1567,16));
        printAllPermutations(5);


    }
}
