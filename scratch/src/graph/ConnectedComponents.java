/*
 * ConnectedComponents.java
 *
 * Created on: Mar 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package graph;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class ConnectedComponents
{
    public static void dfs_doit(int[][]g, boolean[] visited, int u)
    {
        // mark u as visited.
        visited[u] = true;
        for (int v = 0; v < g.length; ++v)
        {
            if (g[u][v] == 1 && visited[v] == false)
            {
                dfs_doit(g, visited, v);
            }
        }
    }
    public static int dfs_recursive(int[][] g)
    {
        boolean[] visited = new boolean[g.length];
        for (int idx = 0; idx < visited.length; ++idx)
            visited[idx] = false;
        int count = 0;
        for (int idx = 0; idx < visited.length; ++idx)
        {
            if (visited[idx] == false)
            {
                dfs_doit(g, visited,idx);
                ++count;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        int g[][]= {    {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
        };
        int count = dfs_recursive(g);
        System.out.println("Count: " + count);
    }
}
