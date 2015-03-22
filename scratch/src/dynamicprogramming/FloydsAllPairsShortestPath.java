package dynamicprogramming;

/**
 * Source: Foundations of Algorithms Using Psuedocode.
 * 
 * Floyds shortest path algorithm for all pairs.
 * 
 * Brute Force: Brute force is worse than exponential as a brute force path from 
 * one vertex to every other vertex will include paths which have all other vertices as
 * intermediate vertices. This will be a subset of all paths from one vertex to another.
 * A path with all other vertices (V-(i, j)) will be factorial as first vertex is 
 * v1, (n-1), n-2, n-3....1 i.e. first vertex can be any of hte remaining vertices
 * 2nd vertex can be any of the n-2 remaining vertices etc.
 * 
 * Using floyds algorithm:
 * 
 * D[i][j][k] = Shortest path from i to j using vertices in the set {v1, v2...vk} i.e. any 
 *              of the vertices in the set 
 *              
 * D[i][j][k] = minimum of
 *             D[i][j][k-1] i.e. no vertex k in the path
 *             D[i][k][k-1] + D[k][j][k-1] i.e. shortest path from i to k using k-1
 *                                and shortest path from k to j using k-1
 *                                
 * NOTE: Also see AllPairsShortestPaths under graphs, same thing.                               
 * @author kg
 *
 */
public class FloydsAllPairsShortestPath {

	
	public static void FloydsShortestPath(int[][] g)
	{
		// create a distance matrix that has the shortest path lengths
		// D[i][j] = shortest path from i to j 
		// NOTE: We can also have a 3D matrix
		// int[][][] D = new int[g.length][g.length][g.length] where
		// all the intermediate distances are also stored 
		int[][] D = new int[g.length][g.length];
		// The path matrix. has the intermediate vertex for shortest path from i to j
		// if 0, that means there is no intermediate vertex.
		int[][] P = new int[g.length][g.length];
		
		// initialise the distance matrix with the graph, this is the k = 0 case
		// i.e. shortest path from u to v using no intermediate vertices.
		for (int idx = 0; idx < g.length; ++idx)
		{
			for (int kdx = 0; kdx < g.length; ++kdx)
			{
				D[idx][kdx] = g[idx][kdx];
				// if weight is 0 from i to k and i != k, there is no edge, 
				// set this as infinie weight.
				if (g[idx][kdx] == 0 && idx != kdx)
					D[idx][kdx] = Integer.MAX_VALUE;
			}
		}
		// Bottoms up construction for shortest path.
		//1.	Calculate shortest paths from u to v using 0 intermediate vertices.
		//2.	Calculate shortest paths from u to v using (0,1) intermediate vertices
		//3.	Calculate shortest paths from u to v using (0, 1, 2) intermediate vertices
		//4.	Calculate shortest paths from u to v using (0,1,2…n) intermediate vertices.
		// this shows there is an outer loop from 0 to n, one for each vertex.
		// the inner loop will calculate all paths from each vertex u to vertex v using the outer vertices
		for (int k = 1; k <= g.length; ++k)
		{
			// from vertex u
			for (int u = 0; u < g.length; ++u)
			{
				// to vertex v
				for (int v = 0; v < g.length; ++v)
				{
					// shortest path from u to v using k intermediate vertices
					// note k is 1 based so 1 has to be subtracted.
					if (D[u][k-1] != Integer.MAX_VALUE && D[k-1][v] != Integer.MAX_VALUE)
					{
						if (D[u][v] > D[u][k-1] + D[k-1][v] )
						{
							P[u][v] = k-1;
							D[u][v] = D[u][k-1] + D[k-1][v];
						}
					}
				}
			}
		}
		System.out.println("abc");
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[][] g = 
			/*  0 1 2 3 4 5 */
			{{0, 1, 0, 1, 5},
			{9, 0, 3, 2, 0},
			{0, 0, 0, 4, 0},
			{0, 0, 2, 0, 3},
			{3, 0, 0, 0, 0}};
		FloydsShortestPath(g);
	}
}
