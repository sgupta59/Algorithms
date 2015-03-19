package dynamicprogramming;

/**
 * http://www.geeksforgeeks.org/shortest-path-exactly-k-edges-directed-weighted-graph/
 * @author kg
 *
 */
public class ShortestPathKEdges {

	/**
	 * Recursive function to get shortest path from u to t using k edges.
	 * This has complexity of O(V^k)
	 * 
	 * This recursive function is same as path from all incoming edges to t + (u,t) i.e.
	 * this is the prefix function instead of the suffix function as given in dasgupta
	 * @param g
	 * @param u
	 * @param t
	 * @param k
	 * @return
	 */
	public static int shortestPath_r(int[][] g, int u, int t, int k)
	{
		// shortest path from u to v using k edges is minimum of shortest path from
		// each adjacent edge of u to v + weight of the adjacent edge.
		
		// if u is same as t and k is 0, this means path from u to u using 0 edges, this path length
		// is 0 even though there is no edge
		if (u == t && k == 0)
		{
			return 0;
		}
		else if (k == 0)
		{
			// otherwise if two vertices are different and k is 0, this means path from 
			// u to t using 0 edges which is maximum
			return Integer.MAX_VALUE;
		}
		
		
		int res = Integer.MAX_VALUE;
		// for each adjacent edge of u
		for (int v = 0; v < g.length; ++v)
		{
			// For all adjacent edges of u, i.e. (u,v)
			if (g[u][v] != 0  )
			{
				// get the shortest path from v to t using k-1 edges.
				// NOTE: To memoize this, we need something to hold the path from v to t using k-1 edges
				//       this is why a 3d matrix is needed.
				int res1 = shortestPath_r(g, v, t, k-1);
				if (res1 != Integer.MAX_VALUE)
				{
					res1 = res1 + g[u][v];
				}
				// store the minimum
				if (res1 < res)
					res = res1;
			}
		}
		return res;
	}
	
	public static int shortestPath_i(int[][] g, int u, int t, int k)
	{
		// have to use k edges sa if the path is using 2 edges, then we need place holders for
		// 0 edges, 1 edge and 2 edges, therefore k is inclusive in the cell.
		// this represents a 3d matrix where there are cells that hold the path lengths
		int[][][] path = new int[g.length][g.length][k+1];
		// for each of the k edges
		for (int e = 0; e <= k; ++e)
		{
			// find path from u to t using e edges.
			// first fill all the cells at layer e = 0, then at layer e = 1 based on 
			// cells in e = 0
			for (int idx = 0; idx <g.length; ++idx)
			{
				for (int jdx = 0; jdx < g.length; ++jdx)
				{
					// initialize the path from vertex idx to jdx using e edges first
					path[idx][jdx][e] = Integer.MAX_VALUE;
					if (idx == jdx && e == 0)
					{
						// path from  a vertex to itself using 0 edge is 0
						path[idx][jdx][e] = 0;
					}
					for (int kdx = 0; kdx < g.length; ++kdx)
					{
						// path from vertex idx to jdx via kdx using e edges
						if (g[idx][kdx] != 0 && e > 0 && path[kdx][jdx][e-1] != Integer.MAX_VALUE)
						{
							// there is an edge from idx to kdx.
							path[idx][jdx][e] = Math.min(
									path[idx][jdx][e] , 
									g[idx][kdx]+path[kdx][jdx][e-1]);
						}
					}
				}
			}
		}
		return path[u][t][k];
	}
	public static void main(String[] args)
	{
		int graph[][] = { {0, 10, 3, 2},
                		  {0, 0, 0, 7},
                		  {0, 0, 0, 6},
                		  {0, 0, 0, 0}
              };
		int u = 0, v = 3, k = 2;
		int length = shortestPath_r(graph, u, v, k);
        System.out.println("Weight of the shortest path is: " + length);
        length = shortestPath_i(graph, u, v, k);
        System.out.println("Weight of the shortest path is: " + length);
	}
}
