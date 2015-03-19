package graph;

import java.util.PriorityQueue;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 * http://en.literateprograms.org/index.php?title=Special:DownloadCode/Dijkstra%27s_algorithm_(Java)&oldid=15444
 * http://www.geeksforgeeks.org/greedy-algorithms-set-7-dijkstras-algorithm-for-adjacency-list-representation/
 * @author kg
 *
 */
public class Dijkstra {

	public static int d[] = null;
	public static int p[] = null;
	public static boolean s[] = null;
	public static void printSolution(int[] d)
	{
		System.out.println("Vertex:           Distance from source");
		for (int idx = 0; idx < d.length; ++idx)
		{
			System.out.println(idx + "         " + d[idx]);
		}
	}
	public static int findMinDist(  boolean[] s, int[] d)
	{
		int minval = Integer.MAX_VALUE;
		int minidx = 0;
		for (int idx = 0; idx < d.length; ++idx)
		{
			if (s[idx] == false)
			{
				if (d[idx] < minval)
				{
					minidx = idx;
					minval = d[idx];
				}
			}
		}
		return minidx;
	}
	public static void dijkstraLinear(Graph g, int src)
	{
		d = new int[g.V];
		p = new int[g.V];
		s = new boolean[g.V];
		
		// initialize all vertices etc.
		for (int idx = 0; idx < g.V; ++idx)
		{
			d[idx] = Integer.MAX_VALUE;
			p[idx] = -1;
			s[idx] = false;
		}
		// set the source vertex distance to be 0.
		d[src] = 0;
		// for all vertices, relax their adjacent edges.
		for (int idx = 0; idx < g.V-1; ++idx)
		{
			// get the minimum weight vertex.
			int u = findMinDist(s,d);
			// mark this vertex as visited
			s[u] = true;
			// relax all adjacent vertices to this vertex.
			for (int jdx = 0; jdx < g.V; ++jdx)
			{
				if (g.adj[u][jdx] != 0 && d[u] != Integer.MAX_VALUE && !s[jdx])
				{
					int w = g.adj[u][jdx];
					if (d[u]+w < d[jdx])
					{
						d[jdx] = d[u]+w;
					}
				}
			}
		}
		printSolution(d);
	}
	public static void DijkstraPriorityQueue(Graph g, int src)
	{
		// create priority queue
		int d[] = new int[g.V];
		for (int idx = 0; idx < d.length; ++idx)
			d[idx] = Integer.MAX_VALUE;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// create a node
		Node n = new Node();
		n.cost=0;
		n.id=src;
		d[src]= 0;
		pq.offer(n);
		while (pq.isEmpty()==false)
		{
			// Get the node.
			Node evalNode = pq.remove();
			int u = evalNode.id;
			for (int v = 0; v < g.V; ++v)
			{
				if (g.adj[u][v] != 0)
				{
					// there is an edge.
					if (d[u] + g.adj[u][v] < d[v])
					{
						Node n1 = new Node();
						n1.cost=d[v];
						n1.id=v;
						pq.remove(n1);
						d[v] = d[u]+g.adj[u][v];
						pq.offer(n1);
					}
				}
			}
		}
		printSolution(d);
		System.out.println("");
	}
	public static void main(String[] args)
	{
		/* Let us create the example graph discussed above */
		/**
		 * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
		 */
		   int adj[ ][ ] = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
		                      {4, 0, 8, 0, 0, 0, 0, 11, 0},
		                      {0, 8, 0, 7, 0, 4, 0, 0, 2},
		                      {0, 0, 7, 0, 9, 14, 0, 0, 0},
		                      {0, 0, 0, 9, 0, 10, 0, 0, 0},
		                      {0, 0, 4, 0, 10, 0, 2, 0, 0},
		                      {0, 0, 0, 14, 0, 2, 0, 1, 6},
		                      {8, 11, 0, 0, 0, 0, 1, 0, 7},
		                      {0, 0, 2, 0, 0, 0, 6, 7, 0}
		                     };
		  Graph graph = new Graph();
		  graph.setAdj(adj);
		  dijkstraLinear(graph, 0);
		  DijkstraPriorityQueue(graph,0);
	}
}
