/*
 * AntInMaze.java
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
public class AntInMaze
{
    public static int RIGHT = 1;
    public static int DOWN = 2;
    static int[][] maze = {
             /*  0  1  2  3  4 */
        /* 0 */ {1, 0, 0, 0, 1},
        /* 1 */ {1, 0, 1, 0, 1},
        /* 2 */ {1, 1, 0, 1, 0},
        /* 3 */ {0, 1, 1, 1, 1},
        /* 4 */ {0, 0, 0, 1, 1}
    };
    static int[][] solution = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };
    public static void printPath()
    {
        for (int idx = 0; idx < solution.length; ++idx)
        {
            for (int jdx = 0; jdx < solution[idx].length; ++jdx)
            {
                System.out.print(" " + solution[idx][jdx]);
            }
            System.out.println("");
        }
    }
    public static boolean canmove(int row, int col, int dir)
    {
        if (dir == DOWN)
        {
            row = row+1;
            if (row > 4)
                return false;
            if (maze[row][col] == 0)
                return false;
            return true;
        }
        col = col+1;
        if (col > 4)
            return false;
        if (maze[row][col] == 0)
            return false;
        return true;
    }

    private static boolean isASolution(int row, int col)
    {
        if (row == solution.length-1 && col == solution.length-1)
        {
            return true;
        }
        return false;
    }
    public static boolean solve(int row, int col)
    {
        /**
         * If this is a solution, mark the solution and return true
         */
        if (isASolution(row,col))
        {
            solution[row][col] = 1;
            printPath();
            return true;
        }

        // for given row/column position.  mark this as visited.
        solution[row][col] = 1;
        // two directions are possible , down and right.

        // try to move down
        if (canmove(row, col, DOWN) && solve(row+1, col))
        {
            return true;
        }
        // try to move forward
        if (canmove(row, col, RIGHT) && solve(row, col+1))
        {
            return true;
        }

        // if all of them fail, unmark the location and return false
        solution[row][col] = 0;
        return false;
    }
    public static void main(String[] args)
    {
        boolean status = solve(0,0);
        System.out.println("Solution: " + status);
        printSolution();
    }
    private static void printSolution()
    {
        for (int i = 0; i < solution.length; ++i)
        {
            for (int j = 0; j < solution[i].length; ++j)
            {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
