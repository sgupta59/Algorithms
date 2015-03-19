package graph;

import java.util.ArrayList;

public class Graph {

	public static int WHITE = 0;
	public static int GRAY = 1;
	public static int BLACK = 2;
	
	public  int adj[][] = null;
	public int color[] = null;
	public int parent[] = null;
	public int depth[] = null;
	public boolean isDirected = false;
	public int d[] = null;
	public int f[] = null;
	public int time = 0;
	public int V = 0;
	public int E = 0;
	public ArrayList<Edge> edgeList = new ArrayList<Edge>();
	public Graph()
	{
		
	}
	public Graph(int V, boolean directed)
	{
		this.V = V;
		isDirected = directed;
		adj = new int[V][V];
		color = new int[V];
		parent = new int[V];
		d = new int[V];
		f = new int[V];
		for (int idx = 0; idx < V; ++idx)
			parent[idx] = -1;
		depth = new int[V];
		for (int idx = 0; idx < V; ++idx)
			depth[idx] = Integer.MAX_VALUE;
	}
	public Graph(int V, int E)
	{
		this.V = V;
		this.E = E;
		adj = new int[V][V];
		color = new int[V];
		parent = new int[V];
		d = new int[V];
		f = new int[V];
		for (int idx = 0; idx < V; ++idx)
			parent[idx] = -1;
		depth = new int[V];
		for (int idx = 0; idx < V; ++idx)
			depth[idx] = Integer.MAX_VALUE;
	}
	public int[][] getMatrix()
	{
		return adj;
	}
	
	public void setAdj(int[][] adj)
	{
		this.adj = adj;
		this.V = adj.length;
		this.E = adj.length;
		color = new int[V];
		parent = new int[V];
		d = new int[V];
		f = new int[V];
	}
	// add an adge between u and v
	public void addEdge(int u, int v)
	{
		adj[u][v] = 1;
		if (isDirected == false)
			adj[v][u] = 1;
	}
	public void addEdge(int u, int v, int weight)
	{
		adj[u][v] = weight;
		Edge e = new Edge(u,v,weight);
		edgeList.add(e);
	}
	// print path from u to v
	public void printPath(int u, int v)
	{
		if (u == v )
		{
			System.out.print(u);
			return;
		}
		if (parent[v] == -1)
		{
			System.out.println("No path from " + u + " to " +  v + " exists");
			return;
		}
		printPath(u,parent[v]);
		System.out.print(", " + v);
	}
}
