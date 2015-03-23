/*
 * CountIslands.java
 *
 * Created on: Mar 23, 2015
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
public class CountIslands
{
    // Do a topological sort on the graph represented by the matrix.
    // now go through the vertices in topological order and count number of vertices that have an incoming edge.
    public static int countIslandsTopologicalSort(int[][] g)
    {

        return 0;
    }
    /**
     * count all 'x' that have a
     *      1. 'o' on the row above same column
     *      2. 'o' on the same row but left column
     * @param m
     * @return
     */
    public static int countIslands(int[][] m)
    {
        int count = 0;
        for (int idx = 0; idx < m.length; ++idx)
        {
            for (int jdx = 0; jdx < m[idx].length; ++jdx)
            {
                if (m[idx][jdx] == 'X')
                {
                    if ((idx == 0 || m[idx-1][jdx]  == 'O') &&
                        (jdx == 0 || m[idx][jdx-1]  == 'O'))
                    {
                        ++count;
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        int mat[ ][ ] =
           {{'O', 'O', 'O'},
            {'X', 'X', 'O'},
            {'X', 'X', 'O'},
            {'O', 'O', 'X'},
            {'O', 'O', 'X'},
            {'X', 'X', 'O'}
          };
        int islands = countIslands(mat);
        System.out.println("ISlands: " + islands);
    }
}
