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
	static Map<Integer,String> treeEdgesBaseLine = new HashMap<Integer,String>();
	static Map<Integer,String> backEdgesBaseLine = new HashMap<Integer,String>();
	static int treecounter = 0;
	static int backcounter = 0;
	static int WHITE = 0;
	static int GRAY = 1;
	static int RED = 2;
	static int BLACK =3 ;
	static int start = 2;
	/**
	 * DFS traversal recursive, as specified in CLRS.
	 * Node is marked visited when it is removed from the stack (i.e. the recursive function is called)
	 * Its parent is set when it is first discovered
	 * @param g
	 * @param visited
	 * @param parent
	 * @param u
	 */
    public static void dfs_recursive_simple(int[][] g,   boolean[] visited, int[] parent, int u)
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
    				dfs_recursive_simple(g, visited, parent, v);

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
     *
     *  Node is marked as visited/gray when it is removed from the stack.
     *  Then it is added back to the stack. The gray node is added back in order to mark its finish time when all its children are processed.
     *  This will happen as the children are pushed on top of the stack.
     *
     *
     */
    public static void dfs_stack_simple(int[][] g, boolean[] visited, int[] parents,int i)
    {
    	Stack<Integer> stack = new Stack<Integer>();
    	// mark the vertex as visited and put it in the queue
    	stack.push(i);

    	while (stack.isEmpty() == false)
    	{
    		int u = stack.pop();
    		// If node is not visited, visit it.
    		if (!visited[u])
    		{
    		    visited[u]=true;
    		    // First time visiting this node so print as a tree node.
    		    if (parents[u] != -1  )
    		    {
    		        printEdge(parents[u], u, "Tree");
    		    }
                // Push this node back on the stack as a gray node. Next time it is encountered, mark it as a black node.
                // Also gives a location where the node can be processed.
    		    stack.push(u);
    		    //[EDGE TRAVERSAL]
    		    // if v is traversed from 0 to g.length-1, then the dfs tree is different and the same node
    		    // generates two back edges
    		    for (int v = g.length-1; v >= 0 ; --v)
    		    {
    		        if (g[u][v] != 0 )
    		        {
    		            // if v is not visited, mark its parent. and add v to the stack, v node is not marked visited here
    		            // so the parent gets updated if there is some other path to this node. different than bfs where the node
    		            // can be marked visited at this point
    		            if (visited[v] == false)
    		            {
    		                stack.push(v);
    		                parents[v] = u;
    		            }
    		            else if (parents[u] != v)
    		            {
    		                printEdge(u,v, "back");
    		            }
    			    }
    		    }
    		}
    	}
    }
    public static void dfs_stack_based1(int[][] g, boolean[] visited, int[] parents,int[] color, int[] start, int[] finish, int time, int i)
    {
        Stack<Integer> stack = new Stack<Integer>();
         // mark the vertex as visited and put it in the queue
        stack.push(i);


        while (stack.isEmpty() == false)
        {
            int u = stack.pop();
            if (color[u] == WHITE)
            {
                visited[u]=true;
                color[u] = GRAY;
                start[u] = ++time;
                if (parents[u] != -1  )
                {
                    printEdge(parents[u], u, "Tree");
                }
                // Push this node back on the stack as a gray node. Next time it is encountered, mark it as a black node.
                // Also gives a location where the node can be processed.
                stack.push(u);

                //[EDGE TRAVERSAL]
                // if v is traversed from 0 to g.length-1, then the dfs tree is different and the same node
                // generates two back edges
                for (int v = g.length-1; v >= 0 ; --v)
                {
                    if (g[u][v] != 0 )
                    {
                        // if v is not visited, mark its parent. and add v to the stack, v node is not marked visited here
                        // so the parent gets updated if there is some other path to this node. different than bfs where the node
                        // can be marked visited at this point
                        if (color[v] == WHITE)
                        {
                            stack.push(v);
                            parents[v] = u;
                        }
                        else if (color[v] == Graph.BLACK)
                        {
                            // if v is black, this can be a cross edge or a forward edge.
                            if (start[u] < start[v])
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

            }
            else if (color[u] == GRAY)
            {
                    color[u] = BLACK;
                    finish[u] = ++time;
            }
            else
            {
                // black node found just pop it? maybe this is the previous?
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
     * This causes u'-> v as a tree edge when u->v is alread a tree edge, i.e v has been discovered by u and is in the queue to be processed
     * where u' has been discovered after u, so
     *
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
    		if (visited[u] == false)
    		{
        		visited[u] = true;
        		for (int v = 0; v < g.length; ++v)
        		{
        			if (g[u][v] != 0 )
        			{
        				if (visited[v] == false)
        				{
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
    		else
    		{

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
    				else if (v != parents[u])
    				{
    					printEdge(u, v, "Back");
    				}
    			}
    		}
    	}
    }
	public static void dfsTraversal(int[][] g )
	{
		boolean visited[] = new boolean[g.length];
		int[] parent = new int[g.length];
		for (int idx = 0; idx < visited.length; ++idx)
		{
			visited[idx] = false;
			parent[idx] = -1;
		}
        int[] color = new int[g.length];

        for (int idx = 0; idx < color.length; ++idx)
        {
            color[idx] = WHITE;

        }
        //int[] start = new int[g.length];
        //int[] finish = new int[g.length];
        //int time = 0;
		//dfs_stack_based1(g,visited,parent,color, start, finish, time, 2);
		//dfs_stack_simple(g, visited,parent,start);
        bfsTraversal_normal1(g,visited, parent,start);
		for (int idx = 0; idx < visited.length; ++idx)
		{
			if (!visited[idx])
			{
				//dfsTraversal_Recursive(g, visited, parent, idx);
				//bfsTraversal_normal(g,visited, parent,idx);
				//dfs_bfs_based(g,visited,parent,idx);
				//bfsTraversal_normal1(g,visited,parent,idx);
				dfs_stack_simple(g,visited,parent,idx);
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
			treeEdges.put(++treecounter, value);
		}
		else
		{
			backEdges.put(++backcounter, value);
		}
	}
	public static void main(String[] args)
	{

	    //int[][] g = graph1();
	    int[][] g =graph1();

		dfsTraversal(g);
		System.out.println("Test status:" + isValid());
	}
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
	    start = 2;
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
            {0, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 0, 0, 1}
        };
        start = 2;
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
	        treeEdgesBaseLine.put(1, "Tree Edge (s, a)");
	        treeEdgesBaseLine.put(2, "Tree Edge (a, b)");
	        treeEdgesBaseLine.put(3, "Tree Edge (b, e)");
	        treeEdgesBaseLine.put(4, "Tree Edge (e, d)");
	        treeEdgesBaseLine.put(5, "Tree Edge (d, c)");
	        treeEdgesBaseLine.put(6, "Tree Edge (c, f)");
	        treeEdgesBaseLine.put(7, "Tree Edge (f, g)");
	        treeEdgesBaseLine.put(8, "Tree Edge (g, h)");
	        backEdgesBaseLine.put(1, "back Edge (d, a)");
	        backEdgesBaseLine.put(2, "back Edge (c, s)");
	        backEdgesBaseLine.put(3, "back Edge (g, d)");
	        backEdgesBaseLine.put(4, "back Edge (h, e)");
	        start = 0;
	        return g;
	}
	private static  boolean isValid()
	{
	    for (Map.Entry<Integer, String> entry : treeEdgesBaseLine.entrySet())
	    {
	        if (entry.getValue().compareTo(treeEdges.get(entry.getKey()))!= 0)
	        {
	            return false;
	        }
	    }
	    for (Map.Entry<Integer, String> entry : backEdgesBaseLine.entrySet())
        {
            if (entry.getValue().compareTo(backEdges.get(entry.getKey()))!= 0)
            {
                return false;
            }
        }
	    return true;
	}
}
