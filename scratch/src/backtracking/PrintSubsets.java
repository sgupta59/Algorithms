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
     * @param in
     * @param out
     */
    public static void printSubsets_iter(String in, String out)
    {
        if (in.length() == 0)
        {
        	return;
        }
        for (int idx = 0; idx < in.length(); ++idx)
        {
            String ins = in.substring(idx+1);
            String outs = out+in.charAt(idx);
            System.out.println(outs);
            printSubsets_iter(ins, outs);
        }
    }

    
}
