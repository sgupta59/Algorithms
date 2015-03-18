/*
 * PringSubsets.java
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
public class PrintSubsets
{
    public static void printSubsets(String in, String out)
    {
        if (in.isEmpty())
        {
            System.out.println(out);
            return;
        }
        printSubsets(in.substring(1), out+in.charAt(0));
        printSubsets(in.substring(1),out);
    }
    public static void main(String[] args)
    {
        String in = "abcd";
        String out="";

        printSubsets_iter(in,out);
        printSubsets(in,out);
    }

    public static void printSubsets_iter(String in, String out)
    {
        if (in.length() == 0)
            return;
        for (int idx = 0; idx < in.length(); ++idx)
        {
            String ins = in.substring(idx+1);
            String outs = out+in.charAt(idx);
            System.out.println(outs);
            printSubsets_iter(ins, outs);
        }
    }

}
