package graph;

import java.util.LinkedList;

/**
 * Topological sort.
 *
 * Mark all vertices with white color.
 *
 * do this for all white vertices.
 *
 * create a counter for visit times.
 * pick a vertex and mark   set ds = ++time, set the parent of this vertex as -1
 * for the given vertex, get its children.
 *   if the children are white
 *      mark child as gray
 *      call dfs
 * mark the vertex as black
 * set its finish time as ++t
 * add this vertex to the front of a linked list or to the top of the stack.
 *
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class TopologicalSort {

	public static void DFSUtil(Graph g,LinkedList<Integer> sorted, int u)
	{
		g.color[u] = Graph.GRAY;
		g.d[u] = ++g.time;
		for (int v = 0; v < g.V; ++v)
		{
			if (g.adj[u][v] == 1)
			{
				if (g.color[v] == Graph.WHITE)
				{
					g.parent[v] = u;
					g.color[v] = Graph.GRAY;
					DFSUtil(g, sorted,v);
				}
			}
		}
		g.color[u] = Graph.BLACK;
		g.f[u] = ++g.time;
		sorted.addFirst(u);
	}
	public static void DFS(Graph g)
	{
		LinkedList<Integer> sorted = new LinkedList<Integer>();
		// for each vertex, mark it as white.
		// call DFS on all vertices
		for (int u = 0; u < g.V; ++u)
			if (g.color[u] == Graph.WHITE)
			DFSUtil(g,sorted, u);
		System.out.println("");
	}
	public static void main(String[] args)
	{
		Graph g = new Graph(9,true);
		g.addEdge(0, 1);
		g.addEdge(0,3);

		g.addEdge(1,2);
		g.addEdge(3,2);
		g.addEdge(4,5);
		g.addEdge(5,3);

		g.addEdge(4,7);
		g.addEdge(5,7);

		g.addEdge(6,7);
		DFS(g);
	}
}
