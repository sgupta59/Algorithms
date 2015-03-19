package graph; 

/**
 * http://www.slideshare.net/TraianRebedea/algorithm-design-and-complexity-course-8 
 * www.geeksforgeeks.org/bridge-in-a-graph/
 * @author kg
 *
 */
public class ArticulationVertex {

	static int time = 0;
	static int time1 = 0;
	public static void DFS_vertex(Graph g, boolean[] visited, int[] parent, int[] d, int[] low, int u)
	{
		// update the discovery and low time first
		d[u] = low[u] = ++time;
		// mark the vertex as visited.
		visited[u] = true;
		int children = 0;
		// for each adjacent vertex v of u
		for (int v = 0; v < g.V; ++v)
		{
			if (g.adj[u][v] != 0)
			{
				// There is an edge between (u,v)
				// if this v is not visited
				if (visited[v] == false)
				{
					++children;
					// mark the parent
					parent[v] = u;
					// call dfs on the v
					DFS_vertex(g, visited, parent, d, low, v);
					// update low of u to its low or the low of a subtree rooted at v.
					low[u] = Math.min(low[u], low[v]);
					// Articulation point detection starts.
					if (parent[u] == -1 && children >= 2)
					{
						// if u has no parent and more than 1 unvisited children, this is an articulation 
						// vertex.
						System.out.print(" " + u);
					}
					if (parent[u] != -1 && low[v] >= d[u])
					{
						// if u has a parent, but the subtree rooted at v has a low which is higher
						// than the discovery of u, as there are no backpointers.
						System.out.print(" " + u);
					}
					
				}
				else if (parent[u] != v)
				{
					// v is not a parent of u
					low[u] = Math.min(low[u], d[v]);
				}
			}
		}
	}
	public static void FindBridge(Graph g)
	{
		// need parent pointers
		int[] parent = new int[g.V];
		int[] d = new int[g.V];
		int[] low = new int[g.V];
		boolean[] visited = new boolean[g.V];
		for (int idx = 0; idx < g.V; ++idx)
		{
			parent[idx] = -1;
			visited[idx] = false;
		}
		// do this for all vertices
		for (int idx = 0; idx < g.V; ++idx)
			if (visited[idx] == false)
				DFS_bridge(g, parent, visited, d, low,idx);
	}
	public static void DFS_bridge(Graph g, int[] parent, boolean[] visited, int[] d, int[] low, int u)
	{
		// update the times.
		d[u] = low[u] = ++time;
		// mark u as visited
		visited[u] = true;
		// for each v connected to u
		for (int v = 0; v < g.adj.length; ++v)
		{
			// if (u,v) is an edge
			if (g.adj[u][v] != 0)
			{
				// if v is not visited
				if (visited[v] == false)
				{
					// set parent
					parent[v] = u;
					DFS_bridge(g, parent, visited, d, low, v);
					low[u] = Math.min(low[u], low[v]);
					if (low[v] > d[u])
					{
						System.out.println("( " + u + " " + v + " )");
					}
				}
				else if (parent[u] != v)
				{
					low[u] = Math.min(low[u], d[v]);
				}
			}
		}
	}
	public static void FindArticulationVertex(Graph g)
	{
		// need.
		// parent array, discovery time, low time, visited array.
		int[] parent = new int[g.V];
		int[] d = new int[g.V];
		int[] l = new int[g.V];
		boolean visited[]  = new boolean[g.V];
		for (int idx = 0; idx < g.V; ++idx)
		{
			visited[idx] = false;
			parent[idx] = -1;
			d[idx] = 0;
			l[idx] = 0;
		}
		for (int idx = 0; idx < g.V; ++idx)
		{
			if (visited[idx] == false)
				DFS_vertex(g,visited,parent,d,l,0);	
		}
	}
	public static void main(String[] args)
	{
		testBridge();
		testArticulation();
	}
	public static void testArticulation()
	{
		Graph g3 = new Graph(7,false);
	    g3.addEdge(0, 1);
	    
	    
	    g3.addEdge(1, 2);
	    g3.addEdge(2, 0);
	    g3.addEdge(1, 3);
	    g3.addEdge(1, 4);
	    g3.addEdge(1, 6);
	    g3.addEdge(3, 5);
	    g3.addEdge(4, 5);
	    FindArticulationVertex(g3);
	}
	public static void testBridge()
	{
		Graph g3 = new Graph(7,false);
		g3.addEdge(0, 1);
	    g3.addEdge(1, 2);
	    g3.addEdge(2, 0);
	    g3.addEdge(1, 3);
	    g3.addEdge(1, 4);
	    g3.addEdge(1, 6);
	    g3.addEdge(3, 5);
	    g3.addEdge(4, 5);
	    FindBridge(g3);
	}
}
