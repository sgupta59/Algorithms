package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
/**
 * Graphs in http://www.geeksforgeeks.org/find-paths-given-source-destination/
 * 
 * 1. Complexity here is O(v^2) as the graphs are not DAGs.
 * 2. Topological sort example?
 * @author kg
 *
 */
public class PrintAllPaths {
	
	public static void printAllPaths(Graph g, int s, int t, boolean[] visited, Vector<Integer> path)
	{
		if (s==t)
		{
			for (int idx = 0; idx < path.size(); ++idx)
				System.out.print(" " + path.get(idx));
			System.out.print(" " + s);
			System.out.println("");
			visited[s] = false;
			return;
		}
		// mark the current vertex as visited.
		visited[s] = true;
		// add the current vertex to the path.
		path.add(s);
		// for each adjacent vertex of s, if vertex is not visited, call difs.
		 for (int jdx = 0; jdx < g.V; ++jdx)
			{
				if (g.adj[s][jdx] != 0)
				{
					if (!visited[jdx])
					{
						printAllPaths(g, jdx, t, visited, path);
					}
				}
			}
		 
		path.remove(new Integer(s));
		visited[s] = false;
	}
	public static void printAllPaths(Graph g, int s, int t, boolean[] visited, int[] path, int pathidx)
	{
		visited[s] = true;
		path[pathidx] = s;
		++pathidx;
		if (s == t)
		{
			// print pah
			for (int idx = 0; idx < pathidx; ++idx)
				System.out.print(path[idx] + " ");
			System.out.println("");
		}
		else
		{
			for (int v = 0; v < g.V; ++v)
			{
				if (g.adj[s][v] == 1)
				{
					if (!visited[v])
						printAllPaths(g, v, t, visited, path, pathidx);
				}
			}
		}
		visited[s] = false;
		--pathidx;
	}
	public static void printAllPaths(Graph g, int s, int t)
	{
		boolean[] visited = new boolean[g.V];
		int[] path = new int[g.V];
		int pathidx = 0;
		for (int idx = 0; idx < g.V; ++idx)
			visited[idx] = false;
		printAllPaths(g, s, t, visited, path, pathidx);
		System.out.println("\n");
		Vector<Integer> v = new Vector<Integer>();
		for (int idx = 0; idx < g.V; ++idx)
			visited[idx] = false;
		printAllPaths(g, s, t, visited, v);
		
	}
	public static int printAllPathsBFS(Graph g, int s, int t)
	{
		// create a visited array
		boolean visited[] = new boolean[g.V];
		// create a parent pointer list.
		ArrayList<ArrayList<Integer>> parent = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < g.V; ++i)
			parent.add(new ArrayList<Integer>());
		// mark parent for the source vertex as -1
		parent.get(s).add(-1);
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		while (!q.isEmpty()) {
			int u = q.poll();
			visited[u] = true;
			// get all siblings of u
			for (int v = 0; v < g.V; ++v) {
				if (g.adj[u][v] == 1 && visited[v] == false) {
					parent.get(v).add(u);
					if (q.contains(v)==false)
						q.offer(v);
				}
			}
		}
		// now print all paths from s to t.
		printPath(parent, s, t,t);
		return 0;
	}
	static boolean printPath(ArrayList<ArrayList<Integer>> parent, int src, int target, int origt)
	{
		if (src == target) {
			System.out.print("  " + src);
			return true;
		}
		if (parent.get(target).get(0) == -1)
		{
			System.out.println("no path");
			return false;
		}
		// get all parents of target
		ArrayList<Integer> parents = parent.get(target);
		for (int p : parents) {
			printPath(parent, src,p, origt);
			System.out.print(" --> " + target);
			if (origt == target)
				System.out.println(" ");
		}
		return false;
	}
	public static void main(String[] args)
	{
		Graph g = new Graph(4,true);
		g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(0, 3);
	    g.addEdge(2, 0);
	    g.addEdge(2, 1);
	    g.addEdge(1, 3);
	    printAllPathsBFS(g, 2, 3);
	    printAllPaths(g, 2, 3);
	    System.out.println("abc");
	    Graph g1 = new Graph(6,true);
	    g1.addEdge(0,1);
	    g1.addEdge(0,3); 
	    g1.addEdge(1,3);
	    g1.addEdge(1,4);
	    g1.addEdge(2,5);
	    g1.addEdge(3,1);
	    g1.addEdge(4,3);
	    g1.addEdge(4,5);
	    g1.addEdge(4,2);
	    printAllPaths(g1, 0, 5);
	    System.out.println("abc");
	}
}
