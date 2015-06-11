package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	// counter used to assign a value to a node
	private final int nodes = 0;
	private final Map<Integer,String> nodenameMap = new HashMap<Integer, String>();
	private final Map<String,Integer> namenodeMap = new HashMap<String,Integer>();
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
		depth = new int[V];
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
		//if (isDirected == false)
		//	adj[v][u] = weight;
		Edge e = new Edge(u,v,weight);
		edgeList.add(e);
	}
	public void addEdge(String uname, String vname)
	{
	    // size if offset by 1, so if there are 2 nodes in the graph, the node index will be
	    // 0, 1 but the map size will be 2, and 2 is the start index of the new node being added
	    // addEdge("p","q") 2 nodes indexed as 0, 1, size = 2
	    // addEdge("p1","q1") size = 2, so p1 is id = 2, q1 = id = 3
	    int u = 0;
	    int v = 0;
	    if (namenodeMap.get(uname) != null)
	        u = namenodeMap.get(uname);
	    else
	    {
	        u = nodenameMap.size();
	        namenodeMap.put(uname, u);
	        nodenameMap.put(u, uname);
	    }
	    if (namenodeMap.get(vname) != null)
            v = namenodeMap.get(vname);
        else
        {
            v = nodenameMap.size();
            namenodeMap.put(vname, v);
            nodenameMap.put(v, vname);
        }
	    adj[u][v] = 1;
	    if (isDirected==false)
	        adj[v][u] = 1;
	}
	public int getId(String name)
	{
	    if (namenodeMap.get(name) != null)
	        return namenodeMap.get(name);
	    return -1;
	}
	public String getName(int id)
	{
	    if (nodenameMap.get(id) != null)
	        return nodenameMap.get(id);
	    return null;
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
	public void printEdge(int u, int v)
	{
	    System.out.println("("+ nodenameMap.get(u) + "," + nodenameMap.get(v) + ")");
	}
	
	public ArrayList<Edge> edges()
	{
		return edgeList;
	}
}
