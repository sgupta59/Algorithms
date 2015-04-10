package graph;
/** https://www.penflip.com/dwgill/clrs-problem-set-10142014 */
//
// http://stackoverflow.com/questions/11104744/simple-paths-between-2-nodes
// http://test.scripts.psu.edu/users/d/j/djh300/cmpsc465/notes-4985903869437/solutions-to-some-homework-exercises-as-shared-with-students/4-solutions-clrs-22.pdf
public class CountPaths {

	public static int[] paths = null;
	public static int countPaths(Graph g, int s, int t)
	{
		g.color[s] = Graph.GRAY;
		// for each adjacent vertex of s
		for (int idx = 0; idx < g.V; ++idx)
		{
			if (g.adj[s][idx] == 1)
			{
				// if this is the target gertex, increment the path, do not mark the target
				// as gray as it has to be visited again.
				if (idx == t)
					paths[s] = paths[s]+1;
				else if (g.color[idx] == Graph.WHITE)
				{
					// if the adj vertex is white, get the paths from this vertex to the target
					// and add to s's path sum
					paths[s] += countPaths(g, idx,t);
				}
				else
				{
					// the idx vertex has already been visited, just increment the count.
					// The vertex can not be gray as the graph is acyclic.
					paths[s] += paths[idx];
				}
			}
		}
		return paths[s];
	}
	// start a dfs from p . if it finds v, thre return true
	// otherwise return false.
	// if a dfs from a node returns true, increment the count of the parent's node.
	public static int dfs_path(Graph g, int[] path, boolean[] visited, int u, int end)
	{
	    // mark the start vertex as visited.


	    //System.out.println("Begin Processing Vertex: " + g.getName(u));
	    if (u == end)
	    {
	        return path[u];
	    }

	    int count = 0;
	    for (int v = 0; v < g.adj.length; ++v)
	    {
	        if (g.adj[u][v] == 1)
	        {

	            g.printEdge(u, v);

	            {
	                dfs_path(g, path, visited, v, end);
	                count += path[v];
	            }
	        }
	    }
	    path[u] = count;
	    return path[u];
	}
	public static void main(String[] args)
	{
	    Graph g = TestGraphs.clrs_graph_1;
	    paths = new int[g.adj.length];
	    boolean[] visited = new boolean[paths.length];

        int id = g.getId("p");
        paths[id] = 0;
        id = g.getId("v");
        paths[id] = 1;
        int count = dfs_path(g,paths, visited,g.getId("p"), g.getId("v"));
        System.out.println("count: " + paths[g.getId("p")]);
	}
}
