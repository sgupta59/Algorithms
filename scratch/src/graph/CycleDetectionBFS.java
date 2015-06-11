package graph;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionBFS {

	public static boolean hasCycle(Graph g)
	{
		// start with vertex 0 and do a bfs.
		g.color[0] = Graph.GRAY;
		g.depth[0] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		while (queue.isEmpty() == false)
		{
			int u = queue.poll();
			for (int v = 0; v < g.V; ++v)
			{
				if (g.adj[u][v] == 1)
				{
					if (g.color[v] == Graph.WHITE)
					{
						g.depth[v] = g.depth[u]+1;
						g.color[v] = Graph.GRAY;
						g.parent[v] = u;
						queue.offer(v);
					}
					else if (g.depth[v] >= g.depth[u])
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 *  0--1   2-- 3  
		 *  |  | / | / |
		 *  4  5-- 6-- 7
		 */
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
		System.out.println(hasCycle(g));
	}

}
