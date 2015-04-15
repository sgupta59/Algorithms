/*
 * TestGraphs.java
 *
 * Created on: Apr 10, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package graph;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public   class TestGraphs
{
    /** Exercise:  22.4-2 */
    public static Graph clrs_graph_1 = null;
    public static Graph kruskalGraph_1 = null;
    public static Graph kruskalGraph_2 = null;
    static
    {
    	createClrsGraph1();
    	createKruskalGraph1();
    	createKruskalGraph2();
    }
    public static void createKruskalGraph2()
    {
    	kruskalGraph_2 = new Graph(9,false);
    	kruskalGraph_2.addEdge(0,1,4);
    	kruskalGraph_2.addEdge(0,7,8);
    	
    	kruskalGraph_2.addEdge(1,7,11);
    	kruskalGraph_2.addEdge(1,2,8);
    	
    	kruskalGraph_2.addEdge(2,8,2);
    	kruskalGraph_2.addEdge(2,3,7);
    	kruskalGraph_2.addEdge(2,5,4);
    	
    	kruskalGraph_2.addEdge(3,4,9);
    	kruskalGraph_2.addEdge(3,5,14);
    	
    	kruskalGraph_2.addEdge(4,5,10);
    	
    	kruskalGraph_2.addEdge(5,6,2);
    	
    	kruskalGraph_2.addEdge(6,8,6);
    	kruskalGraph_2.addEdge(6,7,1);
    	
    	kruskalGraph_2.addEdge(7,8,7);
    	
    }
    public static void createKruskalGraph1()
    {
    	/* Let us create following weighted graph
       10
   0--------1
   |  \     |
  6|   5\   |15
   |      \ |
   2--------3
       4       */
    	kruskalGraph_1 = new Graph(4, false);
    	kruskalGraph_1.addEdge(0,1,10);
    	kruskalGraph_1.addEdge(0,2,6);
    	kruskalGraph_1.addEdge(0,3,5);
    	kruskalGraph_1.addEdge(1,3,15);
    	kruskalGraph_1.addEdge(2,3,4);
    }
    public static void createClrsGraph1()
    {
    	 clrs_graph_1 = new Graph(14, true);
         clrs_graph_1.addEdge("m", "r");
         clrs_graph_1.addEdge("m", "q");
         clrs_graph_1.addEdge("m", "x");

         clrs_graph_1.addEdge("n","a");
         clrs_graph_1.addEdge("n","u");
         clrs_graph_1.addEdge("n","q");

         clrs_graph_1.addEdge("a", "r");
         clrs_graph_1.addEdge("a", "s");
         clrs_graph_1.addEdge("a", "v");

         clrs_graph_1.addEdge("p", "a");
         clrs_graph_1.addEdge("p", "s");
         clrs_graph_1.addEdge("p", "z");

         clrs_graph_1.addEdge("q", "t");

         clrs_graph_1.addEdge("r", "u");
         clrs_graph_1.addEdge("r", "y");

         clrs_graph_1.addEdge("s", "r");

         clrs_graph_1.addEdge("u", "t");

         clrs_graph_1.addEdge("v", "x");
         clrs_graph_1.addEdge("v", "w");

         clrs_graph_1.addEdge("w", "z");

         clrs_graph_1.addEdge("y", "v");
    }
}
