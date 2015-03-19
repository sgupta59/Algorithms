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
	public static void main(String[] args)
	{
		Graph g = new Graph(8, true);
		paths = new int[8];
	}
}
