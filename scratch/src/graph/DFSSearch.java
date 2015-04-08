package graph;

public class DFSSearch {


	public static void DFSUtil(Graph g, int u)
	{
		g.color[u] = Graph.GRAY;
		g.d[u] = ++g.time;
		for (int v = 0; v < g.V; ++v)
		{
			// if thee is an edge
			if (g.adj[u][v] == 1)
			{
				// if vertex v is white
				if (g.color[v] == Graph.WHITE)
				{
					g.parent[v] = u;
					//g.color[v] = Graph.GRAY;
					DFSUtil(g, v);
				}
				else if (g.color[v] == Graph.BLACK)
				{
					// if v is black, this can be a cross edge or a forward edge.
					if (g.d[u] < g.d[v])
					{
						System.out.println("Forward Edge: " + u + ", " + v);
					}
					else
					{
						System.out.println("Cross Edge: " + u + ", " + v);
					}
				}
				else
				{
					System.out.println("Back Edge: " + u + ", " + v);
				}
			}
		}
		g.f[u] = ++g.time;
		g.color[u] = Graph.BLACK;
	}
	public static void DFS(Graph g)
	{
		for (int u = 0; u < g.V; ++u)
		{
			g.color[u] = Graph.WHITE;
			g.parent[u] = -1;
		}
		g.time = 0;
		DFSUtil(g, 2);
		for (int u = 0; u < g.V; ++u)
			if (g.color[u] == Graph.WHITE)
				DFSUtil(g, u);
	}
	public static void main(String[] args)
	{
		Graph g = new Graph(8,true);

		g.addEdge(0,4);

		g.addEdge(1, 0);
		g.addEdge(1, 5);

		g.addEdge(2,1);
		g.addEdge(2,5);

		g.addEdge(3,6);
		g.addEdge(3,7);

		g.addEdge(4,1);
		g.addEdge(5,4);

		g.addEdge(6,5);
		g.addEdge(6,2);

		g.addEdge(7,6);
		g.addEdge(7,3);
		DFS(g);
		System.out.println("");
	}
}
