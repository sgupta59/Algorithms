package graph;

import java.util.LinkedList;
import java.util.Queue;

public class DiameterRadius {

	/**
	 * Eccentricity of a vertex is the max distance from a vertex u to another vertex v in the graph.
	 * Diameter of a graph is = max (eccentricity of all vertices), i.e  d(G) = max { ec(u)} where u in G.V
	 * Radius of a graph is = min (eccentricity of all vertices), i.e. r(G) = min(ec(u)) where u in G.V.
	 * Periphery of a graph = all vertices with diameter of the graph
	 * center of a graph = vertex with eccentricity equal to the radius of a graph. 
	 * @param g
	 * @param u
	 * @return
	 */
	public static int eccentricity(Graph g, int vert)
	{
		// for a given vertex, do a bfs and find the 
		for (int i = 0; i < g.V; ++i)
		{
			g.color[i] = Graph.WHITE;
			g.depth[i] = 0;
		}
	    // start with vertex u.
		// set its depth = 0
		g.depth[vert] = 0;
		// set the color of this vertex
		g.color[vert] = Graph.GRAY;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(vert);
		// whilte queue is not empty
		while (!queue.isEmpty())
		{
			// get the vertex
			int u = queue.poll();
			// for all its adjacent vertices
			for (int v = 0; v < g.adj[u].length; ++v)
			{
				if (g.adj[u][v] == 1 && g.color[v] == Graph.WHITE)
				{
					queue.offer(v);
					g.color[v] = Graph.GRAY;
					g.depth[v] = g.depth[u]+1;
					g.parent[v] = u;
				}
			}
			g.color[u] = Graph.BLACK;
		}
		int maxdist = Integer.MIN_VALUE;
		for (int idx = 0; idx < g.V; ++idx)
		{
			if (g.depth[idx] > maxdist)
				maxdist = g.depth[idx];
		}
		return maxdist;
	}
	
	public static int graphDiameter(Graph g)
	{
		int diameter = Integer.MIN_VALUE;
		for (int i = 0; i < g.V; ++i)
		{
			int ec = eccentricity(g, i);
			if (ec > diameter)
				diameter = ec;
		}
		return diameter;
	}
	public static int graphRadius(Graph g)
	{
		int radius = Integer.MAX_VALUE;
		for (int i = 0; i < g.V; ++i)
		{
			int ec = eccentricity(g,i);
			if (ec < radius)
				radius = ec;
		}
		return radius;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(5, false);
		g.addEdge(0, 1);
		g.addEdge(1,2);
		g.addEdge(1,3);
		g.addEdge(2, 3);
		g.addEdge(2,4);
		graphRadius(g);
		int diameter = graphDiameter(g);
		int ec0 = eccentricity(g,1);
		System.out.println("abc");
	}

}
