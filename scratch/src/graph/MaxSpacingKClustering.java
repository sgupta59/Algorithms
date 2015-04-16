package graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  http://codingrecipies.blogspot.com/2013/09/max-spacing-for-k-order-cluster.html
 *  http://www.cs.wlu.edu/~sprenkle/cs211/winter12/lectures/15-clustering.pdf
 *  
 * Divide objects into k non empty groups.
 * What is the minimum distance between any pairs of objects in a group.
 * k-clustering with maximum spacing: What is the maximum spacing in a group of objects divided in k clusters?
 * 
 * Similar to kruskal's algorithm. Stop when there are K clusters.
 * Instead of repeating V-1 times, repeat V-K times until there are exactly k clusters (and no cycles).
 * 
 * Equivalent to finding a MST and deleting k-1 most expensive edges.
 *
 * @author Sanjeev
 */
public class MaxSpacingKClustering {
    
	public static int createMaxSpacingKCluster(int k)
	{
		Graph g = TestGraphs.kClusterGraph1;
		ArrayList<Edge> mstEdges = new ArrayList<Edge>();
		int V = g.V;
		ArrayList<Edge> edges = g.edges();
		// sort the edges.
		Collections.sort(edges);
		// create a UF structure.
		QuickFindUF clusters = new QuickFindUF(V);
		int i = 0;
		int e = 0;
		// can also do V-K times but have to be aware that there are no cycles and 
		// there are k clusters.
		for (i = 0; i < V-k; ++i)
		{
			Edge minEdge = edges.get(i);
			int p = minEdge.u;
			int q = minEdge.v;
			// if p and q are in the same cluster, then do nothing.
			if (clusters.find(p)==clusters.find(q))
			{
				continue;
			}
			 
			if (clusters.union(p, q))
				mstEdges.add(minEdge);
		}
		// Now find an edge that does ont cause cycles, this is the edge if added will reduce the
		// number of clusters to k-1, so the weight of this edge is the maximum spacing
		Edge minEdge = null;
		while (i < V)
		{
			minEdge = edges.get(i++);
			if (!clusters.connected(minEdge.u, minEdge.v))
				break;
		}
		return -1;
	}
    public static void main(String[] args)
    {
    	createMaxSpacingKCluster(4);
    }
}