package graph;

public class BellmanFord {

	public static int[] d = null;
	public static int[] p = null;
	
	public static void printArr(int[] d)
	{
		System.out.println("Vertex		Distrance from source\n");
		for (int idx = 0; idx < d.length; ++idx)
			System.out.println(idx + "		" + d[idx]);
		System.out.println("");
	}
	public static void initialize_single_source(Graph g, int src)
	{
		d = new int[g.V];
		p = new int[g.V];
		for (int idx = 0; idx < g.V; ++idx)
		{
			d[idx] = Integer.MAX_VALUE;
			p[idx] = -1;
			
		}
		d[src] = 0;
	}
	public static void relax(int u, int v, int w)
	{
		if (d[u] != Integer.MAX_VALUE && d[u] + w < d[v])
		{
			d[v] = d[u] + w;
			p[v] = u;
		}
	}
	public static void bellmanFord(Graph g, int src)
	{
		initialize_single_source(g, src);
	    
	    for (int i = 0; i < g.V-1 ; ++i)
	    {
	    	// process edges for each vertex
	    	for (int j = 0; j < g.edgeList.size(); ++j)
	    	{
	    		int u = g.edgeList.get(j).u;
	    		int v = g.edgeList.get(j).v;
	    		int w = g.edgeList.get(j).w;
	    		relax(u,v,w);
	    	}
	    }
	    
	    // check for negative weight cycles.
	    for (int i = 0; i < g.edgeList.size(); ++i)
	    {
	    	int u = g.edgeList.get(i).u;
	    	int v = g.edgeList.get(i).v;
	    	int w = g.edgeList.get(i).w;
	    	if (d[u] != Integer.MAX_VALUE && d[u]+w < d[v])
	    		System.out.println("Graph contains negative weight cycle");
	    }
	    printArr(d);
	    
	}
	public static void main(String[] args)
	{
		Graph g = new Graph(5,8);
		// Graph in http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
		g.addEdge(0,1,-1);
		g.addEdge(0,2,4);
		g.addEdge(1,2,3);
		g.addEdge(1,3,2);
		g.addEdge(1,4,2);
		g.addEdge(3,2,5);
		g.addEdge(3,1,1);
		g.addEdge(4,3,-3);
		bellmanFord(g,0);
		return;
	}
}
