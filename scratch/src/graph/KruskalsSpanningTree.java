package graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Spanning Tree: A graph where there is a path between every pair of vertices.
 * 				  There can be multiple spanning trees.
 * 
 * Minimum spanning Tree: Tree where the spanning tree has minimum weight of edges.
 * 
 * Number of Edges in minimum spanning Tree: V-1 where V is number of vertices in the graph.
 * 
 * Algorithm:
 * 
 * 1. Sort all edges in the graph in non decreasing order O(nlogn)
 * 2. Pick the smallest edge, check that it does not form a cycle in the spanning tree.
 * 3. If it does not, add the edge to the spanning tree.
 * 4. Continue 2 and 3 till there are V-1 edges in the tree
 * 
 * Complexity Analysis:
 * 
 * 1. Sorting takes ElogE time.
 * 2. UF find operations take logV time.
 *    Since there are E edges, this mean ElogV
 * 3. Total is ElogE + ElogV
 *    E can be <= V^2
 *    logE = log(2V) = Log(V)
 *    
 *    Therefore, total time is O(ElogV) or O(ElogE)
 * 
 * @author kg
 *
 */
public class KruskalsSpanningTree {

	public static void KruskalMST(Graph g)
	{
		int V = g.V;
		Edge[] mstEdges = new Edge[V-1];
		ArrayList<Edge> edges = g.edges();
		// Sorting takes O(ElogE) time.
		Collections.sort(edges);
		QuickFindUF uf = new QuickFindUF(V);
		int i = 0;
		int e = 0;
		// do this for V-1 vertices, i.e. from 0 to V-2 i.e ofr E edges.
		while (e < V-1)
		{
			Edge edge = edges.get(i++);
			// find can take O(logV) time for a Union by rank with path compression with path compression
			//int x = uf.find(edge.u);
			//int y = uf.find(edge.v);
			if (uf.union(edge.u,edge.v))
			{
				mstEdges[e++] = edge;
			}
		}
		System.out.println("MST Edges: " );
		/// output (validate) is:kruskalGraph_1
		//Edge(2,3,4)
		//Edge(0,3,5)
		//Edge(0,1,10)
		// kruskalGraph_2
		//Edge(6,7,1)
		//Edge(2,8,2)
		//Edge(5,6,2)
		//Edge(0,1,4)
		//Edge(2,5,4)
		//Edge(2,3,7)
		//Edge(0,7,8)
		//Edge(3,4,9)
		for (int idx = 0; idx < mstEdges.length; ++idx)
		{
			System.out.println("Edge("+mstEdges[idx].u+","+mstEdges[idx].v+","+mstEdges[idx].w+")");
		}
	}
	public static void main(String[] args)
	{
		Graph g = TestGraphs.kruskalGraph_2;
		KruskalMST(g);
	}
}
