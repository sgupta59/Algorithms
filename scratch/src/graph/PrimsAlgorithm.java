package graph;

/**
 * Similar to dijkstra but different in the sense that the weights for each vertex is the edge
 * weight and not the accumulated weight up to that vertex.
 * @author kg
 *
 */
public class PrimsAlgorithm {

	/**
	 * Array based prims algorithm.
	 * The complexity is O(V^2+VE) = O(V^2).
	 * @param g
	 */
	public static void prim_array(int[][] g)
	{
		// create an array to hold distances for each vertex.
		int[] d = new int[g.length];
		int[] p = new int[g.length];
		
		boolean[] visited = new boolean[g.length];
		
		// initialize everything
		// THis takes time O(V) here, for a heap, it will take O(N)
		for (int idx = 0; idx < g.length; ++idx)
		{
			d[idx] = Integer.MAX_VALUE;
			p[idx] = -1;
			visited[idx] = false;
		}
		// mark the first vertex as the start vertex.
		d[0] = 0;
		p[0] = -1;
		// for rest of the vertices, i.e. N-1
		for (int idx = 0; idx < g.length-1; ++idx)
		{
			// O(N) here , for heap it will be O(logn) --> V*V or V*LogV
			int minid = findMinIndex(d,visited);
			// mark this as visited.
			visited[minid] = true;
			// for all adjacent edges to minid
			for (int jdx = 0; jdx < g.length; ++jdx)
			{
				// if there is an edge between idx and jdx
				// and the jdx vertex has not been visited.
				// for each edge, that means V*E or  E*logV
				// This is decrease key operation, done once per edge, hence E*LogV
				if (g[minid][jdx] != 0 && !visited[jdx])
				{
					if (g[minid][jdx] < d[jdx])
					{
						d[jdx] = g[minid][jdx];
						p[jdx] = minid;
					}
				}
			}
		}
		// Total complexity is O(v+e)logv or O(v^2)
		printMST(g, d, p);
	}
	public static void printMST(int[][] g, int[] d, int[] p)
	{
		for (int idx = 1; idx < d.length; ++idx)
		{
			System.out.println("Edge: " + p[idx] + "," + idx + " - " + d[idx]);
		}
	}
	public static int findMinIndex(int[] d, boolean[] visited)
	{
		int min = -1;
		for (int idx = 0; idx < d.length; ++idx)
		{
			if (!visited[idx])
			{
				if (min == -1)
					min = idx;
				else if (d[idx] < d[min])
					min = idx;
			}
		}
		return min;
	}
	public static void main(String[] args)
	{
		/* Let us create the following graph
        2    3
    (0)--(1)--(2)
     |   / \   |
    6| 8/   \5 |7
     | /     \ |
    (3)-------(4)
          9          */
		int graph[ ][ ] = {{0, 2, 0, 6, 0},
                    {2, 0, 3, 8, 5},
                    {0, 3, 0, 0, 7},
                    {6, 8, 0, 0, 9},
                    {0, 5, 7, 9, 0},
                   };
		prim_array(graph);
	}
}
