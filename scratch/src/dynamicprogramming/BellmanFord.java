package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

	public static void bellmanFord(Integer[][] adjMatrix, int s, int t)
	{
		Integer[][] costMatrix = new Integer[adjMatrix.length][];
		for (int idx = 0; idx < adjMatrix.length; ++idx)
		{
			costMatrix[idx] = new Integer[adjMatrix[0].length];
			for (int jdx = 0; jdx < adjMatrix[0].length; ++jdx)
				costMatrix[idx][jdx] = Integer.MAX_VALUE;
		}
		int nrows = adjMatrix.length;
		int ncols = adjMatrix[0].length;
		// Let the cost matrix be with 
		// rows = vertex ids
		// cols = # of edges.
		// mark the target vertex id to be 0 for all edges. 
		for (int idx = 0; idx < ncols; ++idx)
			costMatrix[t][idx] = 0;
		// all other cost matrix values are at infinity by default.
		// now, start computing hte costs.
		// for # of edges in increasing order.
		for (int idx = 1; idx < ncols; ++idx)
		{
			// Here idx is # of edges in the solution.
			// for each vertex in the adjmatrix.
			for (int vid = 0; vid < nrows; ++vid)
			{
				List<Integer> adjList = adjacency(adjMatrix,vid);
				// for for given vid, and its adjlists, calculate the matrix values.
				// Solution is: Opt(i,v) = min(opt(i-1,v), min(opt(i-1,v"")+cvv"
				int sol1 = costMatrix[vid][idx-1];
				for (int jdx = 0; jdx < adjList.size(); ++jdx)
				{
					int adjid = adjList.get(jdx);
					if (costMatrix[adjid][idx-1] == Integer.MAX_VALUE)
						continue;
					int sol2 = costMatrix[adjid][idx-1]+adjMatrix[vid][adjid];
					if (sol1 > sol2)
						sol1 = sol2;
				}
				costMatrix[vid][idx] = sol1;
			}
		}
	}
	private static List<Integer> adjacency(Integer[][] G, int v) {
		List<Integer> result = new ArrayList<Integer>();
		for (int x = 0; x < G.length; x++) {
			if (G[v][x] != null) {
				result.add(x);
			}
		}
	    return result;
	 }
	public static void main(String[] args)
	{
		Integer[][] adjMatrix = {
			     /**  t, a   , b, c  , d, e  */
				{null,null,null,null,null,null}, /** t */
				{-3,null,-4, null, null, null},  /** a */
				{null, null,null, null,-1,-2},  /** b */
				{3,null,8,null,null,null},      /** c */
				{4,6,null,null,null,null},      /** d */
				{2,null,null,-3,null,null}    /** e */
		};
		Integer[][] adjMatrix1 = {
				/** a , b, c, d, e, t */
				{ null,-4,null,null,null,-3},
				{ null,null,null,-1,-2,null},
				{null,8,null,null,null,3},
				{6,null,null,null,null,4},
				{null,null,-3,null,null,2},
				{null,null,null,null,null,null}
		};
		bellmanFord1D(adjMatrix1,0);
		bellmanFord(adjMatrix,5,0);
		printMatrix(adjMatrix);
	}
	
	public static void bellmanFord1D(Integer[][] matrix, int vid)
	{
		int[] d = new int[matrix.length];
		int[] p = new int [matrix.length];
		for (int idx = 0; idx < matrix.length; ++idx)
			p[idx] = -1;
		for (int idx = 0; idx < matrix.length; ++idx)
			d[idx] = Integer.MAX_VALUE;
		d[vid] = 0;
		p[vid] = 0;
		// Number of vertex = matrix.length
		int nVertices = matrix.length;
		// for n-1 vertices, i.e n-1 times because first vertex is 0
		for (int idx = 1; idx < nVertices; ++idx)
		{
			// for each vertex v
			for (int u = 0; u < nVertices; ++u)
			{
				List<Integer> adjlist = adjacency(matrix,u);
				// for every vertex in the adj list
				for (int kdx = 0; kdx < adjlist.size(); ++kdx)
				{
					int v = adjlist.get(kdx);
					int dv = d[v];
					int du = d[u];
					if (d[u] == Integer.MAX_VALUE)
						continue;
					if (d[v] > d[u] + matrix[u][v])
					{
						d[v] = d[u] + matrix[u][v];
						p[v] = u;
					}
				}
				printArray(d);
			}
		}
		printArray(d);
		System.out.println("Weights: 0 -4 -9 -5 -6 -6");
		printPath(p,0,5);
		System.out.println("");
		System.out.println("Path: 0 1 4 2 5");
	}
	public static void printPath(int[] p, int s, int e)
	{
		if (s == e)
			System.out.print(" " + s);
		else if (e == -1)
			System.out.print(" no path" );
		else
		{
			printPath(p, s, p[e]);
			System.out.print(" " + e);
		}
	}
	public static void printArray(int[] arr)
	{
		for (int idx = 0; idx < arr.length; ++idx)
			System.out.print(" " + arr[idx]);
		System.out.println("\n");
	}
	public static void printMatrix(Integer[][] matrix)
	{
		for (int idx = 0; idx < matrix.length; ++idx)
		{
			for (int jdx = 0; jdx < matrix[idx].length; ++jdx)
				System.out.print(" " + matrix[idx][jdx]);
			System.out.println("");
		}
		
	}
}
