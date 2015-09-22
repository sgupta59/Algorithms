package backtracking;

/**
 * Backtracking implementation for k coloring a graph. The backtracking is done as follows (consider m = 3)
 * 
 *                                (start) 
 *    (v1)            c1                  c2            c3                         cost = m
 *    (v2)    c1      c2    c3         c1  c2  c3    c1  c2  c3                    cost = m + m + m +...= m^2
 *    (v3)  c1 c2 c3  ...                                                          cost = m^3 
 *    
 *    Total cost = 1 + m^2 + m^3 + ... m^n             n = number of vertices
 *                       
 * @author kg
 *
 */
public class GraphKColoring {

	/**
	 * Check if any adjacent vertex has the same color as the u vertex. return false in that case
	 */
	public static boolean isValid(int[][] g, int u, int color, int[] vcolor)
	{
		for (int v = 0; v < g.length; ++v) {
			if (g[u][v] != 0 && vcolor[v] == color)
				return false;
		}
		return true;
	}
	public static boolean graphColoring_dfs(int[][] g, int u, int m, int[] vcolor)
	{
		// if this is the last vertex, then return true.
		if (u == g.length)
			return true;
		// try all colors.
		for (int i = 0; i < m; ++i) {
			// if this color i is valid for the vertex u.
			if (isValid(g, u, i, vcolor)) {
				// mark this vertex with this color.
				vcolor[u] = i;
				if (graphColoring_dfs(g, u+1, m, vcolor)) 
					return true;
				// unmark the color try the next color
				vcolor[u] = -1;
			}
		}
		// return false as no match was found.
		return false;
	}
	public static void graphColoring(int[][] g, int m)
	{
		// create an array to hold all colors of the graph.
		int[] vcolor = new int[g.length];
		for  (int i = 0; i < vcolor.length; ++i)
			vcolor[i] = -1;
		// start graph coloring from vertex id = 0;
		if (graphColoring_dfs(g, 0, m, vcolor) == false)
		{
			System.out.println("Graph Coloring failed");
			return;
		}
		// if a solution, print the solution.
		for (int i = 0; i < vcolor.length; ++i)
			System.out.println(" Vertx Id: " + i + ", color: " + vcolor[i]);
	}
	public static void main(String[] args)
	{
		/* Create following graph and test whether it is 3 colorable
	      (3)---(2)
	       |   / |
	       |  /  |
	       | /   |
	      (0)---(1)
	    */
	    int graph[ ][ ] = {{0, 1, 1, 1},
	        {1, 0, 1, 0},
	        {1, 1, 0, 1},
	        {1, 0, 1, 0},
	    };
	    int m = 3; // Number of colors
	    graphColoring (graph, m);

	}
}
