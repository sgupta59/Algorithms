/*
 * Boggle.java
 *
 * Created on: Mar 10, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package scratch.backtracking;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class Boggle
{
    public static String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GO"};

    public static char[][] board = null;
    public static boolean[][] visited = null;
    public static int[] xdir = {1, 0, -1};
    public static int[] ydir = {1, 0, -1};
    public static boolean isWord(String word)
    {
        for (int idx = 0; idx < dictionary.length; ++idx)
            if (dictionary[idx].compareTo(word) == 0)
                return true;
        return false;
    }
    public static boolean isSafe(int x, int y)
    {
        if (x >= board.length || y>= board.length || x < 0 || y < 0 || visited[x][y])
            return false;
        return true;
    }
    public static boolean solve(int x, int y, String word)
    {
        if (x >= board.length || y>= board.length || x < 0 || y < 0)
        {
            return true;
        }

        // mark the node as visited
        visited[x][y] = true;
        // if this is a word, print it
        word += board[x][y];
        //System.out.println(word);
        if (isWord(word))
        {
            System.out.println("Found: " + word);
        }
        // for each direction from x, y i.e. 8 of those.
        for (int idx = 0; idx < xdir.length; ++idx)
        {
            for (int jdx = 0; jdx < ydir.length; ++jdx)
            {
                int nx = x+xdir[idx];
                int ny = y+ydir[jdx];
                if (isSafe(nx,ny))
                    solve(nx, ny, word);
            }
        }
        visited[x][y] = false;
        return false;
    }
    public static void resetVisited()
    {
        for (int idx = 0; idx < 3; ++idx)
            for (int jdx = 0; jdx < 3; ++jdx)
                visited[idx][jdx]=false;
    }
    public static void main(String[] args)
    {
        board = new char[3][3];
        visited = new boolean[3][3];

        board[0][0] = 'G';
        board[0][1] = 'I';
        board[0][2] = 'Z';

        board[1][0] = 'U';
        board[1][1] = 'E';
        board[1][2] = 'K';

        board[2][0] = 'Q';
        board[2][1] = 'S';
        board[2][2] = 'E';
        String word = "";
        // for each cell, do a solution.
        for (int idx = 0; idx < 3; ++idx )
            for (int jdx = 0; jdx < 3; ++jdx)
            {
                resetVisited();
                solve(idx,jdx,word);
            }

    }
}
