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
    public static boolean promising(int[][] W, int[] vindex, int i)
    {
        int j;
        boolean status;

        int prevvid = vindex[i-1]-1;
        int cvid = vindex[i]-1;
        if (i == W.length - 1 &&   W[vindex[W.length - 1]-1] [vindex [0]-1] ==0)
            status = false;                                           // First vertex must be adjacent
        else if (i > 0 &&  W[vindex[i - 1]-1] [vindex [i]-1] ==0)
            status = false;                                               // to last. ith vertex must
        else{                                                            // be adjacent to (i - 1) st.
            status = true;
           j = 1;
           while (j < i && status){                                      // Check if vertex is
               if (vindex[i] == vindex [j])                              // already selected.
                   status = false;
               j++;
              }
           }
        return status;
    }
    public static void hamiltonianAllPaths (int[][] g, int[] path, int i)
    {
        int j;

        if (promising (g, path, i))
        {
          if (i == g.length - 1)
          {
              for (int idx = 0; idx < g.length; ++idx)
                  System.out.print(" " + path[idx]);
              System.out.println("");
          }
          else
          {
             for (j = 2; j <= g.length; j++)
             {             // Try all vertices as

                 path [i + 1] = j;         // next one.
                 hamiltonianAllPaths (g, path, i + 1);
                 System.out.println("Processed Vertex: " + j);
             }
          }
        }
    }

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
        hamiltonianAllPaths(g, path, 0);
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
