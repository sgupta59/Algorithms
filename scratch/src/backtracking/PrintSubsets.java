/*
 * PringSubsets.java
 *
 * Created on: Mar 6, 2015
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
public class PrintSubsets
{
	/**
	 * Print subsets as a binary tree. Either the subset has a character or it does not, e.g.
				 *    					subset (abcd)
				 *      a+subset(bcd)                           subset(bcd)
				 *           /\                                      /\
				 *          /  \                                    /  \
			    ab+subset(cd)  a+subset(cd)              b+subset(cd)   b+subset(d)
			       /\                  /\                         /\            /\  
			      /  \                /  \                       /  \          /  \ 
			 abc,(d)  ab,(d)     ac,(d)   a,(d)             bc,(d)  b,(d)   bd,()  b,()
			   /\         /\           /\        /\               /\     /\      /\  
   *          /  \       /  \         /  \      /  \             /  \   /  \    /  \ 
   *    abcd,() abc,() abd,() ab,() acd,() ac,() .....
   *    
   *    Every level is 2 recursive calls, one with a character, one without
   *    Empty substring means print the left side and return.
 
	 * @param soFar
	 * @param remaining
	 */
	public static void printSubset(String soFar, String remaining)
    {
    	if (remaining.length() == 0)
    	{
    		System.out.println(soFar);
    	}
    	else
    	{
    		printSubset(soFar+remaining.charAt(0), remaining.substring(1,remaining.length()));
    		printSubset(soFar, remaining.substring(1,remaining.length()));
    	}
    }
    public static void main(String[] args)
    {
        String in = "abcd";
        String out="";
        printSubSet_1(out,in);
        printSubset("", in);
        printSubsets_iter(in,out);
       
    }

    /**
     * Print the subset as a quadtree, each tree has four children, this reduces one recursion.
     *                                 abcd
     *                                
     *         a+(bcd)             b+(cd)               c+(d)             d+()
     *         
     *  ab+(cd) ac+(d)  ad+()     bc+(d) bd+()        cd+() c+()         
     *       
     *   1. Involves printing at each level the substring part.
     *   2. when instring is empty, then return
     * @param In
     * @param soFar
     */
    public static void printSubsets_iter(String In, String soFar)
    {
        if (In.length() == 0)
        {
        	return;
        }
        for (int idx = 0; idx < In.length(); ++idx)
        {
            String ins = In.substring(idx+1);
            String outs = soFar+In.charAt(idx);
            System.out.println(outs);
            printSubsets_iter(ins, outs);
        }
    }

    public static void printSubSet_1(String soFar, String rem)
    {
    	if (rem.length() == 0)
    	{
    		System.out.println(soFar);
    		return;
    	}
    	for (int i = 0; i < rem.length() ; ++i)
    	{
    		printSubSet_1(soFar+rem.charAt(i), rem.substring(i+1));
    	}
    }
}
