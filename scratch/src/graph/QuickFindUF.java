/*
 * QuickUF.java
 *
 * Created on: Apr 14, 2015
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
public class QuickFindUF
{
    // Size of the UF structure.
    private final int N;
    // ids of different components.
    private final int[] id;

    private int _count;

    public QuickFindUF(int n)
    {
        N = n;
        id = new int[N];
        // initially all componets are disconnected
        for (int i = 0; i < N; ++i)
            id[i] = i;
        _count = N;
    }

    public int count()
    {
    	return _count;
    }
    /**
     * Return the component id for this component.
     * O(1) operation.
     * @param id
     * @return
     */
    int find(int p)
    {
        return id[p];
    }

    /**
     * REturns true if p and q are in the same component.
     *
     * O(1) operation.
     *
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    /**
     * add p into q's component.
     * Do nothing if p and q are in the same component.
     *
     * O(n) operation
     * TODO: Do I need a check for count? i.e. count does not go below 1
     *
     * @param q
     * @param p
     * @return
     */
    public boolean union(int p, int q)
    {
        // find the component id of p and q
        int i = find(p);
        int j = find(q);
        if (i == j)
            return false;
        for (int idx = 0; idx < N; ++idx)
        {
            if (id[idx] == i)
                id[idx] = j;
        }
        --_count;
        return true;
    }

    public static void main(String[] args)
    {
        QuickFindUF uf = new QuickFindUF(10);
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
    }
}
