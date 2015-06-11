package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A bipartite graph is an undirected graph G = (V, E) in which V can be partitioned into two sets V1 and V2 such that 
 * (u, v) E implies either u in V1 and v in V2 or u in V2 and v in V1. 
 * That is, all edges go between the two sets V1 and V2.
 * 
 * The partition V1 and V2 can be 2 colors or odd/even numbers (i.e. 3-V1 or 3-V2) will give odd or even number if
 * V1 = 1
 * @author kg
 *
 */
public class BipartiteBFS {
	
	
	public static boolean isBipartite(Graph g)
	{
		boolean status = true;
		int[] color = new int[g.V];
		for (int idx = 0; idx < g.adj[0].length; ++idx)
		{
			g.color[idx] = Graph.WHITE;
			g.depth[idx] = 0;
			color[idx] = -1;
		}
		// start with vertex 0.
		g.color[0] = Graph.GRAY;
		
		
		color[0] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);
		while (!q.isEmpty()) 
		{
			int u = q.poll();
			for (int v = 0; v < g.adj[u].length; ++v)
			{
				if (g.adj[u][v] == 1)
				{
					if (color[v] == -1)
						color[v] = color[v]-1;
					else if (color[v] == color[u])
						return false;
				}
			}
		}
		return status;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(4, false);
		int G[][ ] = {{0, 1, 0, 1},
		        {1, 0, 1, 0},
		        {0, 1, 0, 1},
		        {1, 0, 1, 0}};
		g.setAdj(G);
		boolean status = isBipartite(g);
		System.out.println("aa");
	}
}
