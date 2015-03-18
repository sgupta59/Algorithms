/*
 * EightQueens.java
 *
 * Created on: Mar 5, 2015
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
public class EightQueens
{
    public static int COLS = 8;
    public static int ROWS = 8;
    public static void printSolution(int[] qa)
    {
        for (int idx = 1; idx < qa.length;++idx)
            System.out.print(" , " + qa[idx]);
        System.out.println("");
    }

    public static boolean isSafe(int[] qa, int col, int row)
    {
       for  (int tmpcol = 1; tmpcol <  col; ++tmpcol)
       {
           if (qa[tmpcol] == row  || col-tmpcol == Math.abs((double)(qa[tmpcol]-row)))
               return false;
       }
       return true;
    }
    public static int queens(int[] qa, int col,int count)
    {
        if (col > COLS)
        {
            printSolution(qa);
            return count+1;
        }
        int fail=0;
        int c1 = count;
        for (int row = 1; row <= ROWS; ++row)
        {

            if (isSafe(qa, col, row))
            {
                qa[col] = row;
                count =  queens(qa, col+1,count);
                if (count == c1)
                {
                    System.out.println("Failed queen at col: '" + col + ", row: " + qa[col] + ", failed" + ", reset to " + 0);
                    qa[col]=0;
                }
                else
                {
                    System.out.println("partial queen at col: " + col + "row: " + qa[col]);
                }
            }
            else
            {
                System.out.println("Not Safe Queen: " + col + " , row: " + row + ", column: " + col);
                ++fail;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        int[] qa = new int[COLS+1];
        int size = 0;
          size = queens(qa, 1,size);
        System.out.println("Size: " + size);
    }
}
