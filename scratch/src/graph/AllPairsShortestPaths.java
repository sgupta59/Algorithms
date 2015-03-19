package graph;

public class AllPairsShortestPaths {

	/**
	 * http://www.cse.ust.hk/faculty/golin/COMP271Sp03/Notes/MyL15.pdf
	 * http://www.columbia.edu/~cs2035/courses/ieor6614.S11/apsp.pdf
	 * 
	 * All pairs shortest path using dynamic programming. Calculates path from each source vertex 
	 * to the target vertex.
	 * 
	 * The path calculated is as follows
	 * 
	 * SP(i, j, k) = min { SP(i, j, k-1) , SP(i, k, k-1) + SP(k, j, k-1) }
	 * 
	 * where
	 * 
	 * SP(i, j, k) = shortest path from i to j using k vertices, 
	 * 
	 * SP(i, j, 0) = shortest path from i to j using 0 intermediate vertices.
	 * 
	 * This example has O(V^3) space complexity and O(v^3) time complexity.
	 * @param g
	 */
	public static void FloydWarshell_1(int[][] g)
	{
		// create a 3D matrix to hold shortest parts for each individual vertex.
		// the matrix is of the form of (i, j, k) where
		// i, j are vertices in the graph, and k is the intermediate vertex.
		int[][][] D = new int[g.length][g.length][g.length+1];
		// for k = 0, this is the original graph.
		// D[][][0] gives shortest paths from vertex i to vertex j with no intermediate vertex
		// D[][][1] gives shortest path from vertex i to j with 1 intermediate vertex, vertex = 0
		//D[][][1] gives shortest path from vertex i to j with 2 intermediate vertex, vertex = 0, 1
		for  (int idx = 0; idx < g.length; ++idx)
		{
			for (int jdx = 0; jdx <  g.length; ++jdx)
			{
				D[idx][jdx][0] = g[idx][jdx];	
				if (g[idx][jdx] == 0 && idx != jdx)
				{
					// If there is no edge between idx and jdx set the weight to infinity.
					D[idx][jdx][0] = Integer.MAX_VALUE;
				}
			}
		}
		printMatrix(D,0);
		// now do this for each of the vertices
		for  (int kdx = 1; kdx <= g.length; ++kdx)
		{
			for (int idx = 0; idx <  g.length; ++idx)
			{
				for (int jdx = 0; jdx <  g.length; ++jdx)
				{
					// shortest path from idx to jdx using vertex kdx can be:
					// SP(idx,jdx,kdx) = min { SP(idx,jdx, kdx-1, 
					//                         SP(idx,kdx, kdx-1)+SP(kdx,jdx,kdx-1)
					
					if ( D[idx][kdx-1][kdx-1] != Integer.MAX_VALUE && 
					    D[kdx-1][jdx][kdx-1] != Integer.MAX_VALUE)
					{
						D[idx][jdx][kdx] = Math.min(D[idx][jdx][kdx-1], 
							                    D[idx][kdx-1][kdx-1]+D[kdx-1][jdx][kdx-1]);
					}
					else
					{
						// If either of idx to kdx or kdx to jdx is infinity, then there is no path
						// from idx -> kdx -> jdx, so just use idx->jdx using kdx-1 vertex.
						D[idx][jdx][kdx] = D[idx][jdx][kdx-1];
					}
				}
			}
			printMatrix(D,kdx);
		}
		printMatrix(D,g.length);
	}
	
	public static void main(String[] args)
	{
		/** Graph from http://www.columbia.edu/~cs2035/courses/ieor6614.S11/apsp.pdf */
		int[][] g = { { 0, 3, 0, 0},
					  { 0, 0, 12, 5}, 
				      {4, 0, 0, -1},
				      {2, -4, 0, 0}};
		FloydWarshell_1(g);
	}
	
	/** Print the 3d matrix for a single vertex. THis gives shortest paths from i to j passing through
	 * vertex v
	 * @param D
	 * @param vertex
	 */
	public static void printMatrix(int[][][] D, int v)
	{
		for (int idx = 0; idx < D.length; ++idx)
		{
			for (int jdx = 0; jdx < D.length; ++jdx)
			{
				if (D[idx][jdx][v] == Integer.MAX_VALUE)
					System.out.print(" " + 0);
				else
				 System.out.print(" " + D[idx][jdx][v]);
			}
			System.out.println("");
		}
		System.out.println("------------------------------------------");
	}
}
