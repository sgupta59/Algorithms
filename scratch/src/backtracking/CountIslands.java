/*
 * CountIslands.java
 *
 * Created on: Mar 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package backtracking;

import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/**
 * count number of islands using a flood fill algorithm.
 * go and visit  every cell which is not marked and has a '1' or an 'x' on it.
 * do a dfs for every unvisited cell that has an 'x' or '1' on it and count number of times dfs is called.
 * how do i use a queue based verion instead of a recursive version?
 * can it be bfs based?
 *
 * @author Sanjeev Gupta
 *
 */
public class CountIslands
{
    public static boolean can_move(int u, int v, int[][] g)
    {
        if (u >= 0 && u < g.length && v >= 0 && v < g[0].length && g[u][v] == 1)
            return true;
        return false;
    }
    public static void countIslands_bfs(int[][] g, boolean [][] visited, int x, int y)
    {
        // assume u/v are not marked.
        Pair<Integer, Integer> cell = new Pair<Integer,Integer>(x,y);
        Queue<Pair<Integer,Integer>> _queue = new LinkedList<Pair<Integer,Integer>>();
        _queue.offer(cell);
        visited[x][y] = true;
        while (_queue.isEmpty() == false)
        {
            Pair<Integer,Integer> val = _queue.poll();
            int u = val.getKey();
            int v = val.getValue();
            // add all children of this value in the queue if they are not visited
            if (can_move(u+1,v,g) && !visited[u+1][v])
            {
                visited[u+1][v] = true;
                _queue.offer(new Pair<Integer,Integer>(u+1,v));
            }
            if (can_move(u-1,v,g) &&  !visited[u-1][v])
            {
                visited[u-1][v] = true;
                _queue.offer(new Pair<Integer,Integer>(u-1,v));
            }
            if (can_move(u,v-1,g) && !visited[u][v-1])
            {
                visited[u][v-1] = true;
                _queue.offer(new Pair<Integer,Integer>(u,v-1));
            }
            // move right
            if (can_move(u,v+1,g) &&  !visited[u][v+1])
            {
                visited[u][v+1] = true;
                _queue.offer(new Pair<Integer,Integer>(u,v+1));
            }
            // move \
            if (can_move(u-1,v-1,g) && !visited[u-1][v-1])
            {
                visited[u-1][v-1] = true;
                _queue.offer(new Pair<Integer,Integer>(u-1,v-1));
            }
           // move \
            if (can_move(u+1,v+1,g) && !visited[u+1][v+1])
            {
                visited[u+1][v+1] = true;
                _queue.offer(new Pair<Integer,Integer>(u+1,v+1));
            }
            // move /
            if (can_move(u-1,v+1,g) && !visited[u-1][v+1])
            {
                visited[u-1][v+1] = true;
                _queue.offer(new Pair<Integer,Integer>(u-1,v+1));
            }
            // move /
            if (can_move(u+1,v-1,g) && !visited[u+1][v-1])
            {
                visited[u+1][v-1] = true;
                _queue.offer(new Pair<Integer,Integer>(u+1,v-1));
            }
        }
    }
    public static boolean countIslands_doit(int[][] g, boolean visited[][], int u, int v)
    {
        // mark the cell as visited.
        visited[u][v] = true;
        // move down
        if (can_move(u+1,v,g))
        {
            if (!visited[u+1][v])
                return countIslands_doit(g, visited,u+1,v);
        }
        // move up
        if (can_move(u-1,v,g))
        {
            if (!visited[u-1][v])
                return countIslands_doit(g, visited,u-1,v);
        }
        // move left
        if (can_move(u,v-1,g))
        {
            if (!visited[u][v-1])
                return countIslands_doit(g, visited,u,v-1);
        }
        // move right
        if (can_move(u,v+1,g))
        {
            if (!visited[u][v+1])
                return countIslands_doit(g, visited,u,v+1);
        }
        // move \
        if (can_move(u-1,v-1,g))
        {
            if (!visited[u-1][v-1])
                return countIslands_doit(g, visited,u-1,v-1);
        }
        // move \
        if (can_move(u+1,v+1,g))
        {
            if (!visited[u+1][v+1])
                return countIslands_doit(g, visited,u+1,v+1);
        }
        // move /
        if (can_move(u-1,v+1,g))
        {
            if (!visited[u-1][v+1])
                return countIslands_doit(g, visited,u-1,v+1);
        }
        // move /
        if (can_move(u+1,v-1,g))
        {
            if (!visited[u+1][v-1])
                return countIslands_doit(g, visited,u+1,v-1);
        }
        return true;
    }
    public static int countIslands(int[][] g)
    {
        boolean[][] isvisited = new boolean[g.length][g[0].length];
        for (int idx = 0; idx < g.length; ++idx)
        {
            for (int jdx = 0; jdx < g[0].length; ++jdx)
            {
                isvisited[idx][jdx] = false;
            }
        }
        int count = 0;
        // for each cell, do a dfs from the cell.
        for  (int idx = 0; idx < g.length; ++idx)
        {
            for (int jdx = 0; jdx < g[0].length; ++jdx)
            {
                if (g[idx][jdx] != 0 && isvisited[idx][jdx] == false)
                {
                    //countIslands_doit(g,isvisited, idx,jdx);
                    countIslands_bfs(g,isvisited,idx,jdx);
                    ++count;
                }
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        int g[][]= {    {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
        };
        int count = countIslands(g);
        System.out.println("Count: " + count);
    }
}
