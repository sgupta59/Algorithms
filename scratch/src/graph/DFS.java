package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Graph source: http://11011110.livejournal.com/279880.html
 * @author kg
 *
 */
public class DFS {
	static Map<Integer,String> mapping = new HashMap<Integer,String>();
	static Map<Integer,String> treeEdges = new HashMap<Integer,String>();
	static Map<Integer,String> backEdges = new HashMap<Integer,String>();

	static int counter = 0;

	/**
	 * DFS traversal recursive, as specified in CLRS.
	 * Node is marked visited when it is removed from the stack (i.e. the recursive function is called)
	 * Its parent is set when it is first discovered
	 * @param g
	 * @param visited
	 * @param parent
	 * @param u
	 */
    public static void dfsTraversal_Recursive(int[][] g,   boolean[] visited, int[] parent, int u)
    {
        // mark the node as visited.
    	visited[u] = true;
    	for (int v = 0; v < g.length; ++v)
    	{
    		if (g[u][v] != 0)
    		{
    		    // If (u,v) is an edge, and v is not visited, then visit v
    			if (!visited[v])
    			{
    			    // set v's parent as u. This is a tree edge
    				parent[v] = u;
    				printEdge(u, v, "Tree");
    				dfsTraversal_Recursive(g, visited, parent, v);

    			}
    			else if (v != parent[u])
    			{
    			    // if v is visited, that means v was visited before u. If v is not u's parent, then v is a
    			    // back edge of some type.
    				printEdge(u, v, "Back");
    			}
    		}
    	}
    }
    /*
     * The right way to do a stack based DFS traversal.
     * When the node is removed from the stack, it is marked as visited. Its parent is set at that point.
     * Then the children are processed.
     *
     * NOTE: The edges are processed in reverse order to get the same dfs tree as a recursive dfs tree.
     * look at the line marked [EDGE TRAVERSAL]
     */
    public static void dfs_stack_based(int[][] g, boolean[] visited, int[] parents,int i)
    {
    	Stack<Integer> stack = new Stack<Integer>();
    	// mark the vertex as visited and put it in the queue
    	stack.push(i);
    	int prev = -1;
    	while (stack.isEmpty() == false)
    	{
    		int u = stack.pop();

    		if (!visited[u])
    		{

    		    visited[u]=true;
    		    if (prev != -1  )
    		    {
    		        if (parents[u] == -1)
    		            parents[u] = prev;
    		         printEdge(parents[u], u, "Tree");
    		    }
    		    stack.push(u);
    		    //[EDGE TRAVERSAL]
    		    // if v is traversed from 0 to g.length-1, then the dfs tree is different and the same node
    		    // generates two back edges
    		    for (int v = g.length-1; v >= 0 ; --v)
    		    {
    		        if (g[u][v] != 0 )
    		        {

    		            if (visited[v] == false)
    		            {
    		                stack.push(v);
    		                parents[v] = u;
    		            }
    		            /*else if (parents[u] != v)
    		            {
    		                printEdge(u,v, "back");
    		            }*/

    			    }
    		    }
    		    prev = u;
    		}
    		else
    		{
    		    //if (prev != -1)
                {
                    if (g[prev][u] == 1)
                        printEdge(prev, u, "Back Edge");
                    prev = u;
                }
    		}

    	}
    }
    public static void dfs_bfs_based(int[][] g, boolean[] visited, int[] parents,int i)
    {

    	Stack<Integer> stack = new Stack<Integer>();
    	// mark the vertex as visited and put it in the queue
    	stack.push(i);
    	visited[i]=true;
    	while (stack.isEmpty() == false)
    	{
    		int u = stack.pop();
    		for (int v = 0; v < g.length; ++v)
    		{
    			if (g[u][v] != 0 )
    			{
    				if (visited[v] == false)
    				{
    					visited[v]=true;
    					parents[v] = u;
    					printEdge(u, v, "Tree");
    					stack.push(v);
    				}
    				else if (v != parents[u])
    				{
    					printEdge(u, v, "Back");
    				}
    			}
    		}
    	}
    }
    /** This does not work as the children have to be marked as visited as soon as they are added to the
     * queue, in this method, children are marked visited when they are popped and not added.
     * @param g
     * @param visited
     * @param parents
     * @param i
     */
    public static void bfsTraversal_normal1(int[][] g, boolean[] visited, int[] parents,int i)
    {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	// mark the vertex as visited and put it in the queue
    	queue.offer(i);

    	while (queue.isEmpty() == false)
    	{
    		int u = queue.poll();
    		if (visited[u] == true)
    			continue;
    		visited[u] = true;
    		for (int v = 0; v < g.length; ++v)
    		{
    			if (g[u][v] != 0 )
    			{
    				if (visited[v] == false)
    				{
    					String source = mapping.get(u);
    					String target = mapping.get(v);
    					parents[v] = u;
    					printEdge(u, v, "Tree");
    					queue.offer(v);
    				}
    				else if (v != parents[u])
    				{
    					printEdge(u, v, "Back");
    				}
    			}
    		}
    	}
    }
    public static void bfsTraversal_normal(int[][] g, boolean[] visited, int[] parents,int i)
    {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	// mark the vertex as visited and put it in the queue
    	queue.offer(i);
    	visited[i]=true;
    	while (queue.isEmpty() == false)
    	{
    		int u = queue.poll();
    		for (int v = 0; v < g.length; ++v)
    		{
    			if (g[u][v] != 0 )
    			{
    				if (visited[v] == false)
    				{
    					visited[v]=true;
    					parents[v] = u;
    					printEdge(u, v, "Tree");
    					queue.offer(v);
    				}
    				else if (parents[v] != u)
    				{
    					printEdge(u, v, "Back");
    				}
    			}
    		}
    	}
    }
	public static void dfsTraversal(int[][] g)
	{
		boolean visited[] = new boolean[g.length];
		int[] parent = new int[g.length];
		for (int idx = 0; idx < visited.length; ++idx)
		{
			visited[idx] = false;
			parent[idx] = -1;
		}
		dfs_stack_based(g,visited,parent,2);
		for (int idx = 0; idx < visited.length; ++idx)
		{
			if (!visited[idx])
			{
				//dfsTraversal_Recursive(g, visited, parent, idx);
				//bfsTraversal_normal(g,visited, parent,idx);
				//dfs_bfs_based(g,visited,parent,idx);
				//bfsTraversal_normal1(g,visited,parent,idx);
				dfs_stack_based(g,visited,parent,idx);
			}
		}
		for (Map.Entry<Integer,String> entry : treeEdges.entrySet())
		{
			System.out.println(entry.getValue());
		}
		for (Map.Entry<Integer,String> entry : backEdges.entrySet())
		{
			System.out.println(entry.getValue());
		}
		return;
	}
	private static void printEdge(int u, int v, String type)
	{
		System.out.println(type + " Edge (" + mapping.get(u) + ", " + mapping.get(v) + ")");
		String value = type + " Edge (" + mapping.get(u) + ", " + mapping.get(v) + ")";
		if (type.compareTo("Tree")==0)
		{
			treeEdges.put(++counter, value);
		}
		else
		{
			backEdges.put(++counter, value);
		}
	}
	public static void main(String[] args)
	{

	    //int[][] g = graph1();
	    int[][] g =graph3();
		dfsTraversal(g);
	};
	public static int[][] graph2()
	{
        mapping.put(0, "0");
        mapping.put(1, "1");
        mapping.put(2, "2");
        mapping.put(3, "3");
	    int[][] g =
	    {
	        {0, 1, 1, 0},
	        {0, 0, 1, 0},
	        {1, 0, 0, 1},
	        {0, 0, 0, 1}
	    };
	    return g;
	}
	public static int[][] graph3()
    {
        mapping.put(0, "0");
        mapping.put(1, "1");
        mapping.put(2, "2");
        mapping.put(3, "3");
        int[][] g =
        {
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 1}
        };
        return g;
    }
	public static int[][] graph1()
	{
	        mapping.put(0, "s");
	        mapping.put(1, "a");
	        mapping.put(2, "b");
	        mapping.put(3, "c");
	        mapping.put(4, "d");
	        mapping.put(5, "e");
	        mapping.put(6, "f");
	        mapping.put(7, "g");
	        mapping.put(8, "h");
	        // Graph is from http://11011110.livejournal.com/279880.html
	        int[][] g  =

	            {{0, 1, 0, 1, 0, 0, 0, 0, 0} ,
	             {1, 0, 1, 0, 1, 0, 0, 0, 0} ,
	             {0, 1, 0, 0, 0, 1, 0, 0, 0} ,
	             {1, 0, 0, 0,  1, 0, 1, 0, 0} ,
	             {0, 1, 0, 1,  0, 1, 0, 1, 0} , /* 4 */
	             {0, 0, 1, 0,  1, 0, 0, 0, 1} ,
	             {0, 0, 0, 1, 0, 0, 0, 1, 0} ,
	             {0, 0, 0, 0, 1, 0, 1, 0, 1} ,
	             {0, 0, 0, 0, 0, 1, 0, 1, 0} };
	        return g;
	}
}
