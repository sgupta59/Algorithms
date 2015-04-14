/*
 * WeightedQuickUnionUF.java
 *
 * Created on: Apr 14, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package graph;

/**
 * A weighted quick union algorithm creates a union of two trees with the tree with a greater height to be the parent of the subtree with smaller height.
 *
 * Here,
 *
 * Tree Depth  = number of edges from a node to its root, e.g. root has depth 0, node 1 has depth 1 etc.
 * Tree Height = Number of edges from the longest path from a node to its root.
 *
 * A tree of height h has at least 2^h nodes.
 *
 * 2^(max height) = number of nodes in the tree
 *
 * Hence max height = log (number of nodes)
 *
 * Reference: http://courses.cs.washington.edu/courses/cse326/00wi/handouts/lecture18/sld033.htm
 *
 * @author Sanjeev Gupta
 *
 */
public class WeightedQuickUnionUF
{
    // Number of components.
    private final int N;
    // Count of components
    private int count;
    // ids of each component.
    private final int[] id;
    // size of each component.
    private final int[] size;

    public WeightedQuickUnionUF(int n)
    {
        N = n;
        id = new int[n];
        size = new int[n];
        count = n;
        /** Initially, all compoents are single and the size is 1 */
        for  (int idx = 0; idx < N; ++idx)
        {
            id[idx] = idx;
            size[idx] = 1;
        }

    }

    // return the root of p
    // O(logn)
    // This is because there are n nodes in the tree, then the height of the tree is logn
    // number of nodes in a tree is at least 2^(max height) which means
    // max height = logn
    public int find(int p)
    {
        while (p != id[p])
           p = id[p];
        return p;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    public boolean union(int p, int q)
    {
        int rootp = find(p);
        int rootq = find(q);
        // if in the same component, then return.
        if (rootp == rootq)
            return false;
        if (size[rootp] < size[rootq])
        {
            // if size of subtree rooted at p is less than size of subtree rooted at q
            id[rootp]=rootq;
            size[rootq] += size[rootp];
        } else {
            id[rootq] = rootp;
            size[rootp] += size[rootq];
        }
        --count;
        return true;
    }


    public static void main(String[] args)
    {

        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(5);
        /*boolean added = uf.union(0,1 );
        if (added)
            added = uf.union(1,2);

        added = uf.union(0,2);*/
        uf.union(4, 0);
        uf.union(0,1);
        uf = new WeightedQuickUnionUF(10);
        uf.union(4 ,3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);
        // id array: [6, 2, 6, 4, 4, 6, 6, 2, 4, 4]
        // size array: [1, 1, 3, 1, 4, 1, 6, 1, 1, 1]
        System.out.println("");
    }
}
