package graph;

import java.util.Stack;

public class DFSSearch {


    public static void DFS_iterative(Graph g, int s)
    {
        // create a stack
        Stack<Integer> stack = new Stack<Integer>();
        // put u on the stack
        stack.push(s);
        while (!stack.empty())
        {
            // get a node from the stack
            int u = stack.pop();
            if (g.color[u] == Graph.WHITE)
            {
                g.color[u] = Graph.GRAY;
                g.d[u] = ++g.time;
                stack.push(u);
                for (int v = 0; v < g.V; ++v)
                {
                    if (g.adj[u][v] == 1)
                    {
                        if (g.color[v] == Graph.WHITE)
                        {
                            g.parent[v] = u;
                            stack.push(v);
                        }
                    }
                }
            }
            else if (g.color[u] == Graph.GRAY)
            {
                g.f[u] = ++g.time;
                g.color[u] = Graph.BLACK;
            }
        }
    }
	public static void DFS_recursive(Graph g, int u)
	{
	    // mark the vertex as visiting.
		g.color[u] = Graph.GRAY;
		// increment the start time
		g.d[u] = ++g.time;
		for (int v = 0; v < g.V; ++v)
		{
			// if there is an edge
			if (g.adj[u][v] == 1)
			{
				// if vertex v is not visited
				if (g.color[v] == Graph.WHITE)
				{
				    // set the parent of v as u
					g.parent[v] = u;
					// visit the vertex v.
					DFS_recursive(g, v);
				}
				else if (g.color[v] == Graph.BLACK)
				{
					// if v is black, this can be a cross edge or a forward edge.
					if (g.d[u] < g.d[v])
					{
						System.out.println("Forward Edge: " + u + ", " + v);
					}
					else
					{
						System.out.println("Cross Edge: " + u + ", " + v);
					}
				}
				else
				{
					System.out.println("Back Edge: " + u + ", " + v);
				}
			}
		}
		g.f[u] = ++g.time;
		g.color[u] = Graph.BLACK;
	}
	public static void DFS(Graph g)
	{
		for (int u = 0; u < g.V; ++u)
		{
			g.color[u] = Graph.WHITE;
			g.parent[u] = -1;
		}
		g.time = 0;

		for (int u = 0; u < g.V; ++u)
		{
		    if (g.color[u] == Graph.WHITE)
		        DFS_recursive(g, u);
		}

	}
	public static void main(String[] args)
	{
	    Graph g = createGraph1();
		DFS(g);
		System.out.println("");
	}
	private static Graph createGraph1()
	{
	       Graph g = new Graph(8,true);

	        g.addEdge(0,4);

	        g.addEdge(1, 0);
	        g.addEdge(1, 5);

	        g.addEdge(2,1);
	        g.addEdge(2,5);

	        g.addEdge(3,6);
	        g.addEdge(3,7);

	        g.addEdge(4,1);
	        g.addEdge(5,4);

	        g.addEdge(6,5);
	        g.addEdge(6,2);

	        g.addEdge(7,6);
	        g.addEdge(7,3);
	        return g;
	}
}
