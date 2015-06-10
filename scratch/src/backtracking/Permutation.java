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
        permutestring(perm);
        permute(perm,0);
    }

    public static void permutestring(String input)
    {
        String permuted = new String();
        permutestring_doit(input,permuted);
    }

    public static void permutestring_doit(String input, String soFar)
    {
        if (input.length() == 0)
        {
            System.out.println(soFar);
            return;
        }
        // for each first character in the input string,
        for  (int i = 0; i < input.length(); ++i)
        {
            String newstring = input.substring(0,i) + input.substring(i+1);
            permutestring_doit(newstring, soFar + input.charAt(i));
        }
    }
}
