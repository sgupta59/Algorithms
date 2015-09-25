/*
 * Hamiltonian.java
 *
 * Created on: Mar 11, 2015
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
public class HamiltonianPath
{
   public static boolean isPromising(int[][] W, int[] path,int pos,  int v)
    {
        /** check if this vertex is adjacent vertex of the previously added vertex */
        int prev = pos-1;
        if (W[path[prev]][v] == 0)
        {
            return false;
        }
        /** Check if this vertex has already been included before */
        for (int idx = 0; idx < pos; ++idx)
        {
            if (path[idx] == v)
                return false;
        }
        return true;
    }

    public static boolean hamiltonianCycleUtil(int[][] g, int[] path, int pos)
    {
        if (pos == g.length)
        {
            // if there is an edge from the current position to the first
            if (g[path[pos-1]][path[0]]==1)
            {
                return true;
            }
            return false;
        }
        for (int v = 1; v < g.length; ++v)
        {
            if (isPromising(g, path, pos, v))
            {
                path[pos] = v;
                if (hamiltonianCycleUtil(g, path, pos+1))
                    return true;
                path[pos] = -1;
            }
        }
        return false;
    }
    public static boolean hamiltonianCycle(int g[][])
    {
        int[] path = new int[g.length];
        for (int idx = 0; idx < g.length; ++idx)
            path[idx] = -1;
        path[0] = 1;
         
        path[0] = 1;
        if (hamiltonianCycleUtil(g, path, 1) == false)
        {
            System.out.println("No Solution");
            return false;
        }
        for (int idx = 0; idx < g.length; ++idx)
            System.out.print(" " + path[idx]);
        System.out.println("");
        return true;
    }
    public static void main(String[] args)
    {

        int[][] graph = {
            { 0, 1, 1, 0, 1, 1, 0, 0},
            { 1, 0, 1, 0, 1, 1, 0, 0},
            { 1, 1, 0, 1, 0, 0, 1, 0},
            { 0, 0, 1, 0, 0, 0, 0, 1},
            { 1, 1, 0, 0, 0, 1, 0, 0},
            { 1, 1, 0, 0, 1, 1, 1, 0},
            { 0, 0, 1, 0, 0, 1, 0, 0},
            { 0, 0, 0, 1,0, 0, 1, 0}
        };
        int[] visited = new int[graph.length];
        visited[0] = 1;
        hamiltonianCycle(graph);
    }
}
