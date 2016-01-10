package backtracking;
/**
 *  http://cs.indstate.edu/~zeeshan/aman.pdf
 *  A brute force implementation of the travelling salesman problem.
 *  Calculate the path lengths of all hamiltonian circuits and find the smallest path.
 *  
 *  Use an adjacency matrix to get the paths. 
 *  Assuming that there is a path between all pairs of vertices in the graph.
 *  
 *  An array is used to store the paths 
 *  
 *  The complexity is O(N!), actually since this is an undirected graph, complexity is O(N!/N) 
 * @author kg
 *
 */
public class TravellingSalesmanBruteForce {

	int[][] g = null;
	// the path itself.
	int path[] = null;
	// current position for the vertex that will be added to the path
	int idx = 0;
	// shortest path
	int stpath = Integer.MAX_VALUE;
	public TravellingSalesmanBruteForce(int[][] graph)
	{
		g = graph;
		path = new int[g.length];
		for (int i = 0; i < path.length; ++i)
			path[i] = -1;
		// starting from vertex 0.
		path[0] = 0;
		idx = 1;
	}
	/**
	 *  The path length
	 * @return
	 */
	private int getPathLength()
	{
		int len = 0;
		for  (int i = 0; i < path.length-1; ++i)
		{
			int u = path[i];
			int v = path[i+1];
			len = len + g[u][v];
		}
		len = len + g[path[path.length-1]][path[0]];
		return len;
	}
	private void printPath()
	{
		System.out.print(path[0]);
		for (int i = 1; i < path.length; ++i)
			System.out.print(" -> " + path[i] );
		System.out.println(" -> " + path[0]);
	}
	
	/*
	 * This is a solution if there are no -1 in the path, i.e. all vertices have been visited.
	 */
	private boolean isSolution()
	{
		for (int i = 0; i < path.length; ++i)
			if (path[i] == -1)
				return false;
		return true;
	}
	
	/*
	 * A vertex is valid if it is not already in the path.
	 */
	private boolean isValid(int u)
	{
		for (int i = 0; i < path.length; ++i)
			if (path[i] == u)
				return false;
		return true;
	}
	public void shortestPath(int pos)
	{
		// if this is a solution, then calculate the path length and return
		if (isSolution()) 
		{
			printPath();
			int len = getPathLength();
			if (len < stpath)
			{
				stpath = len;
			}
			return;
		}
		// for each neighbor of position pos
		int u = pos;
		for (int v = 0; v < g.length; ++v) 
		{
			if (isValid(v) && u != v) {
				// depth first search, mark the vertex
				path[idx++] = v;
				shortestPath(v);
				// unmark the vertex
				path[--idx] = -1;
			}
		}
	}
	public static void main(String[] args)
	{
		int[][] graph = {
				{ 0, 3, 5, 2 },
				{3, 0, 9, 5 },
				{5, 9, 0, 1 },
				{2, 5, 1, 0 }
		};
		TravellingSalesmanBruteForce tsp = new TravellingSalesmanBruteForce(graph);
		tsp.shortestPath(0);
		int[] p = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		int[] dist = new int[graph.length];
		for (int i = 0; i < graph.length; ++i)
		{
			p[i] = -1; 
		    visited[i] = false;
		    dist[i] = -1;
		}
		int rem = graph.length;
		
		TSPTour(graph, 0, p, dist, rem,0);
		
	}
	
	/**
	 * @param g  the graph
	 * @param u  start vertex initally can be any
	 * @param p  the parent vector array
	 * @param rem # of remaining nodes in the graph to use to decide when to check tha the start vertex can be included.
	 */
	public static void TSPTour(int[][] g, int u, int[] p, int[] dist, int rem, int src)
	{
		if (rem == 1)
		{
			// tihs is a solution, print the path, calculate the distances and print it.
			for (int i = 0; i < p.length; ++i)
				System.out.print(p[i] + " -> " );
			System.out.println(0);
		}
		// start from u.
		// for all neighbors of u 
		for (int v = 0; v < g.length; ++v)
		{
			
			if (g[u][v] != 0)
			{
				// if v is not visited, i.e. it has no parent call TSPTour
				// or if v is parent of u then do nothing.
				if (p[v] != -1 || p[u] == v)
					continue;
				// if v is the source vertex and there are other vertices to process, ignore this vertex
				if (v == src && rem > 0 )
					continue;
				
				// mark parent of v as u
				p[v] = u;
				TSPTour(g, v, p, dist, rem-1, src);
				p[v] = -1;
			}
		}
		
	}
}
