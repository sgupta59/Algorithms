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
    static
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
