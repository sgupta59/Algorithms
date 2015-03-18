/*
 * ShortestPathKEdges.java
 *
 * Created on: Mar 18, 2015
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
public class ShortestPathKEdges
{

    /**
     * Get the shortest path from u to t using k edges.
     *
     * Recursive method:
     *
     *   SP(u, t, k) = min { SP(v, t, k-1) }
     *      where there are edges (u,v)
     *
     *
     * @param g
     * @param u
     * @param t
     * @param k
     * @return
     */
    public static int ShortestPathKEdges_r(int[][] g, int u, int t, int k)
    {
        if (k == 0)
        {
            if (u == t)
            {
                // shortest path from a vertex to itself using 0 edges is 0
                return 0;
            }
            // infinity otherwise.
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        // otherwise, for each adjacent vertex of u
        for (int v = 0; v < g.length; ++v)
        {
            // if there is an edge from u to v
            if (g[u][v] != 0)
            {
                /**
                 * Memoize this... from v to t using k-1 edges. What changes here is v, t and k-1
                 */
                int min1 = ShortestPathKEdges_r(g, v, t, k-1);
                if (min1 != Integer.MAX_VALUE)
                    min1 = min1 + g[u][v];
                if (min1 < min)
                    min = min1;
            }
        }
        return min;
    }
    /**
     * Memoized version of the shortest path with k edges from u to t with k edges.
     * @param g
     * @param u
     * @param t
     * @param k
     * @return
     */
    public static int ShortestPathKEdges_i(int[][] g, int s, int t, int k)
    {
        // create a 3d matrix as follows
        // matrix is of the form [u][v][edges]
        // this is 3 dimensional and the cells along the z axis have the lengths.

        // This is K+1 as we want the shortest path from u to v using 2 edges, so k is inclusive.
        int[][][] m = new int[g.length][g.length][k+1];
        // initialize this to be infinite.
        for (int e = 0; e <= k; ++e)
        {
            // now there is a 2D matrix initialize this one
            for (int idx = 0; idx < g.length; ++idx)
            {
                for (int jdx = 0; jdx < g.length;  ++jdx)
                {
                    m[idx][jdx][e] = Integer.MAX_VALUE;
                }
            }
        }

        // initilize teh lengths for vertex v to v using 0 edges.
        for (int idx = 0; idx < g.length; ++idx)
        {
            for (int jdx = 0; jdx < g.length; ++jdx)
            {
                if (idx == jdx)
                {
                    m[idx][jdx][0] = 0;
                }
            }
        }

        // now start to populate the shortest path, start from e = 0 i.e. no edge, then e = 1, i.e. one edge etc.
        for (int e = 0; e <= k; ++e)
        {
            // shortest path using e edges from u to v is shortest path from u' to v using e-1 edges + weight (u,u')
            // for each vertex as a source
            for (int u = 0; u < g.length; ++u)
            {
                // for each vertex v as the target
                for (int v = 0; v < g.length; ++v)
                {
                    // for each adjacent vertex of u to v
                    for (int w = 0; w < g.length; ++w)
                    {
                        // if there is an edge from u to w get shortest path using one less edge.
                        // if e is 0, then do nothing as this is the base case.
                        // if m[w][v][e-1] is infinity, then ignore it as the path does not exist
                        if (g[u][w] != 0 && e != 0 && m[w][v][e-1] != Integer.MAX_VALUE)
                        {
                            // shortest path from u to v using e eges is shortest path from
                            // w to v using k-1 edges + weight (u,w)
                            m[u][v][e] = Math.min(m[u][v][e], m[w][v][e-1] + g[u][w]);
                        }
                    }
                }
            }
        }
        return m[s][t][k];
    }
    public static void main(String[] args)
    {
        int g[][] = { {0, 10, 3, 2},
                          {0, 0, 0, 7},
                          {0, 0, 0, 6},
                          {0, 0, 0, 0}
          };
        int u = 0, v = 3, k = 2;
        int length = ShortestPathKEdges_r(g, u, v, k);
        System.out.println("" + length);
        length = ShortestPathKEdges_i(g,u,v,k);
        System.out.println("" + length);
    }
}
