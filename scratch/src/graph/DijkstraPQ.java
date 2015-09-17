package graph;

import java.util.PriorityQueue;

public class DijkstraPQ {

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
						System.out.println("offering vertex: " + v);
						//pq.remove(n1);
						d[v] = d[u]+g.adj[u][v];
						pq.offer(n1);
					}
				}
			}
		}
		System.out.println("");
	}
	
	public static void dijkstra_test(Graph g, int start)
	{
		// create an array for weights of the vertices, initialized to Max value.
		// create an array of parent pointers.
		// create an array of visited nodes
		int d[] = new int[g.V];
		int p[] = new int[g.V];
		boolean[] visited = new boolean[g.V];
		for (int i = 0; i < g.V; ++i) {
			d[i] = Integer.MAX_VALUE;
			p[i] = -1;
			visited[i] = false;
		}
		// create a new node here
		d[start] = 0;
		p[start] = -1;
		 
		Vertex u = new Vertex(start, d[start]);
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(u);
		while (pq.isEmpty() == false) {
			 u = pq.poll();
			 visited[u.id] = true;
			 // now get the adjacent vertices
			 for (int j = 0; j < g.V; ++j) {
				 if (visited[j] == false && g.adj[u.id][j] > 0 ) {
					 
				 }
			 }
		}
	}
	private static class Vertex implements Comparable<Vertex>{
		public int id;
		public int w;
		public Vertex(int id, int w) {
			this.id = id;
			this.w = w;
		}
		
		public int compareTo(Vertex other) {
			if ( this.w < other.w)
				return -1;
			return w == other.w ? 0 : 1;
		}
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
		  DijkstraPriorityQueue(graph, 0);

	}
}

class Node implements Comparable<Node> {

	public int id;
	public int cost;
	public Node()
	{

	}

	@Override
    public boolean equals(Object o) {
		if (o instanceof Node)
		{
			Node on = (Node)o;
			return on.id==id;
		}
		return false;
	}

	@Override
	public int compareTo(Node arg0) {
		// TODO Auto-generated method stub
		if (cost < arg0.cost)
			return -1;
		else if (cost > arg0.cost)
			return 1;
		return 0;
	}

    @Override
    public int hashCode()
    {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

}