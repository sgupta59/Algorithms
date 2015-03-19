package graph;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public static void BFS(Graph g, int n)
	{
		// mark the color as gray
		g.color[n] = Graph.GRAY;
		// parent of u is already -1
		g.depth[n] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(n);
		while (q.isEmpty()==false)
		{
			int u = q.poll();
			// scan the adjacency list of this vertex
			for (int v = 0; v < g.V; ++v)
			{
				if (g.adj[u][v] != 0)
				{
					// reached a white v
					if (g.color[v] == Graph.WHITE)
					{
						g.color[v] = Graph.GRAY;
					    g.depth[v] = g.depth[u]+1;
					    g.parent[v] = u;
					    q.offer(v);
					    //System.out.println("Tree Edge " + u + " to " + v);
					}
					else if (g.color[v] == Graph.GRAY)
					{
						// reached a gray v
						if (g.depth[u] == g.depth[v])
						{
							System.out.println("Sibling Edge " + u + " to " + v);
						}
						else
						{
							System.out.println("Ancestor Edge: " + u + " to " + v);
						}
					}
					else
					{
						if (g.parent[u] == v)
						{
							System.out.println("Parent Edge " + u + " to " + v);
						}
						else if (g.parent[u] == g.parent[v])
						{
							System.out.println("Sibling Edge: " + u + " to " + v);
						}
						else
						{
							System.out.println("Ancestor edge " + u + " to " + v);
						}
					}
				}
			}
			g.color[u] = Graph.BLACK;
		}
	}
	public static void main(String[] args)
	{
		Graph g = new Graph(8, false);
		g.addEdge(0,1);
		g.addEdge(0,4);
		
		g.addEdge(1,0);
		g.addEdge(1,5);
		
		g.addEdge(2,3);
		g.addEdge(2,5);
		g.addEdge(2,6);
		
		g.addEdge(3,2);
		g.addEdge(3,6);
		g.addEdge(3,7);
		
		g.addEdge(4,0);
		
		g.addEdge(5,1);
		g.addEdge(5,6);
		g.addEdge(5,2);
		
		g.addEdge(6,5);
		g.addEdge(6,3);
		g.addEdge(6,2);
		g.addEdge(6,7);
		
		g.addEdge(7, 6);
		g.addEdge(7,3);
		BFS(g,1);
		g.printPath(1, 7);
		System.out.println("");
	}
}
