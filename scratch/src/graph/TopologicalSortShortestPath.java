package graph;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSortShortestPath {

	public static int time = 0;
	public static void doTopologicalSort_doit(Graph g, boolean[] visited, int[] parent, int[] start,
			int[] finish, 
			Stack<Integer> sortedPath, int u)
	{
		// set the start time
		start[u] = ++time;
		// mark this as visited.
		visited[u] = true;
		// for each outgoing edge from u
		for (int v = 0; v < g.V; ++v)
		{
			// if there is an edge (u,v)
			if (g.adj[u][v] != 0)
			{
				if (!visited[v])
				{
					// set parent of v
					parent[v] = u;
					doTopologicalSort_doit(g, visited, parent, start, finish, sortedPath, v);
				}
			}
		}
		// once all children hav ebeen processed
		finish[u] = ++time;
		sortedPath.push(u);
	}
	public static Stack<Integer> doTopologicalSort(Graph g)
	{
		// need visited array
		boolean[] visited = new boolean[g.V];
		// need parent pointer
		int[] parent = new int[g.V];
		// need start and finish times
		int[] start = new int[g.V];
		int[] finish = new int[g.V];
		for (int idx = 0; idx < g.V; ++idx)
		{
			visited[idx] = false;
			parent[idx] = -1;
			start[idx] = 0;
			finish[idx] = 0;
		}
		Stack<Integer> sortedpath = new Stack<Integer>();
		// do topological sort on each vertex in the graph
		for (int idx = 0; idx < g.V; ++idx)
		{
			if (!visited[idx])
				doTopologicalSort_doit(g, visited, parent, start, finish, sortedpath, idx);
		}
		// print the vertices in topological order
		/*while (!sortedpath.isEmpty())
		{
			System.out.print(" " + sortedpath.pop());
		}
		System.out.println("");*/
		return sortedpath;
	}
	public static void relax(int u, int v, int w, int[] d, int[] parent)
	{
		// if d[u] + w < d[v] then set d[v] = d[u] + w
		if (d[u] + w < d[v])
		{
			d[v] = d[u] + w;
			parent[v] = u;
		}
	}
	public static void findShortestPath(Graph g)
	{
		Stack<Integer> path = doTopologicalSort(g);
		// create a distance array
		int[] dist = new int[path.size()];
		int[] parent = new int[path.size()];
		// initilaize this to maximum distance
		for (int idx = 0; idx < dist.length; ++idx)
		{
			dist[idx] = Integer.MAX_VALUE;
			parent[idx] = -1;
		}
		// call relax on all vertices  now.
		dist[0] = 0;
		while (!path.isEmpty())
		{
			int u = path.pop();
			for (int v = 0; v < g.V; ++v)
			{
				// if there is an edge from u to v
				if (g.adj[u][v] != 0)
				{
					relax(u, v, 1, dist,parent);
				}
			}
		}
		printpath(0, 5, parent);
		for (int idx = 0; idx < dist.length; ++idx)
		{
			System.out.print(" " + dist[idx]);
		}
		System.out.println("");
	}
	public static void printpath(int u, int v, int[] parent)
	{
		if (u == v)
		{
			System.out.print(" " + v);
			return;
		}
		else if (parent[v] == -1)
		{
			System.out.println("No parent ");
			return;
		}
		printpath(u, parent[v], parent);
		System.out.print(" " + v);
	}
	public static void main(String[] args)
	{
		Graph g = new Graph(6,true);
		g.addEdge(0, 1);
		g.addEdge(0, 4);
		
		g.addEdge(1, 2);
		
		g.addEdge(2, 3);
		g.addEdge(2,5);
		
		g.addEdge(4,1);
		g.addEdge(4,5);
		
		g.addEdge(5,3);
		findShortestPath(g);
	}
}
