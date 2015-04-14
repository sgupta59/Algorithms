/*
 * QuickUnionUF.java
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
public class QuickUnionUF
{
    private final int N;
    private int[] id=null;
    private int count = 0;
    public QuickUnionUF(int n)
    {
        N = n;
        id = new int[N];
        for  (int i = 0; i < N; ++i)
            id[i] = i;
        count = N;
    }

    /**
     * Theta(logn) ? operation
     * O(n) in worst case.
     * @param p
     * @return
     */
    public int find(int p)
    {
        while (p != id[p])
        {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    /**
     * O(n) or O(n) in worst case
     *
     * @param p
     * @param q
     */
    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        id[i]=j;
        --count;
    }
    public static void main(String[] args)
    {
        QuickUnionUF uf = new QuickUnionUF(10);
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
        // [1, 1, 1, 8, 3, 0, 5, 1, 8, 8]
    }
}
