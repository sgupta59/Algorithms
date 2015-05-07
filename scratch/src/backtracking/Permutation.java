/*
 * Permutation.java
 *
 * Created on: May 7, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package backtracking;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class Permutation
{
    public static void permute(String perm, int i)
    {
        if (i >= perm.length())
        {
            System.out.println(perm);
            return;
        }
        for (int j = i; j < perm.length(); ++j)
        {
            // swap j and i
            perm = swap(perm,i,j);
            permute(perm,i+1);
            perm = swap(perm,i,j);
        }
    }

    public static String swap(String perm, int i, int j)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(perm);
        char ci = buf.charAt(i);
        char cj = buf.charAt(j);
        buf.setCharAt(i, cj);
        buf.setCharAt(j,ci);
        return buf.toString();
    }

    public static void main(String[] args)
    {
        String perm = "abcd";
        permute(perm,0);
    }
}
