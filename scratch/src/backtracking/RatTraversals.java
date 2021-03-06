/*
 * RatTraversals.java
 *
 * Created on: Apr 7, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package backtracking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class RatTraversals
{
    private static int START_ROW = 1;
    private static int START_COL = 1;
    private static int STOP_ROW = 0;
    private static int STOP_COL = 0;
     
    // print the maze
    public static void printMaze(int[][] maze)
    {
        for (int i = 0; i < maze.length; ++i)
        {
            for (int j = 0; j < maze[i].length; ++j)
            {
                if (maze[i][j] == 1)
                {
                    System.out.print(".");
                }
                else if (maze[i][j] == 0)
                {
                    System.out.print(" ");
                }
                else if (maze[i][j] == 2)
                {
                    System.out.print("^");
                }
                else if (maze[i][j] == 3)
                {
                    System.out.print("#");
                }
            }
            System.out.println("");
        }
        return;
    }
    public static int[][] createMaze(File file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String[] pair = reader.readLine().split(" ");
        int rows = Integer.valueOf(pair[0]);
        int cols = Integer.valueOf(pair[1]);
        // Allocate the maze, there are 2 extra rows/columns as row 0 and the last row is a wall.
        int[][] maze = new int[rows+2][cols+2];
        // Set the walls
        for (int j = 0; j <= cols+1; ++j)
        {
            // row 0 is a wall.
            maze[0][j] = 1;
            // last row is a wall.
            maze[rows+1][j] = 1;
        }
        for (int j = 0; j <= rows+1; ++j)
        {
            // column 0 is a wall
            maze[j][0] = 1;
            // Last column is a wall
            maze[j][cols+1] = 1;
        }
         
        for (int i = 1; i <= rows; ++i)
        {
            for (int j = 1; j <= cols; ++j)
            {
                maze[i][j] = 0;
            }
        }
        pair = reader.readLine().split(" ");
        START_ROW = Integer.valueOf(pair[0]);
        START_COL = Integer.valueOf(pair[1]);
        pair = reader.readLine().split(" ");
        STOP_ROW = Integer.valueOf(pair[0]);
        STOP_COL = Integer.valueOf(pair[1]);
        String line = null;
        while ((line = reader.readLine()) != null)
        {
            pair = line.split(" ");
            int x = Integer.valueOf(pair[0]);
            int y = Integer.valueOf(pair[1]);
            maze[x][y] = 1;
        }
        System.out.println("Maze: " );
        printMaze(maze);
        reader.close();
        return maze;

    }

    /**
     * If the target row/column are reached return true
     * false otherwise.
     * 
     * @param x
     * @param y
     * @return
     */
    public static boolean isASolution(int x, int y)
    {
        if (x == STOP_ROW && y == STOP_COL)
            return true;
        return false;
    }
    
    /**
     * If row,col is 0, this mean this is an unvisited cell. return true.
     * Otherwise this is either a wall or has been visited 
     * @param row
     * @param col
     * @param maze
     * @return
     */
    private static boolean canMove(int row, int col, int[][] maze)
    {
        if (maze[row][col] == 0)
            return true;
        return false;
    }

    public static boolean Traversal_DFS(int row, int col, int[][] maze)
    {
        // if this is a solution, then mark the maze cell and return true.
        if (isASolution(row, col))
        {
            maze[row][col] = 3;
            return true;
        }

        // otherwise, mark the cell as visited and process its children.
        maze[row][col] = 2;
        if (canMove(row,col-1, maze) && Traversal_DFS(row,col-1, maze))
        {
        	// mark this as success.
            maze[row][col-1] = 3;
            return true;
        }
        if (canMove(row,col+1, maze))
        {
            // move right
            if (Traversal_DFS(row,col+1, maze))
            {
            	maze[row][col+1] = 3;
                return true;
            }
        }
        if (canMove(row-1,col, maze))
        {
            // move up
            if (Traversal_DFS(row-1,col, maze))
            {
            	maze[row-1][col] = 3;
                return true;
            }
        }
        if (canMove(row+1,col, maze))
        {
            // move down
            if (Traversal_DFS(row+1,col, maze))
            {
            	maze[row+1][col] = 3;
                return true;
            }
        }
        // Failure, unmark the cell and return false
        maze[row][col] = 0;
        return false;
    }
    public static void main(String[] args) throws IOException
    {
         File mazeFile = new File(RatTraversals.class.getResource("/resources/maze.dat").getFile());
         int[][] maze = createMaze(mazeFile);
         if (Traversal_DFS(START_ROW,START_COL, maze))
         {
        	 // If succesful, mark the start row/column as true
        	 maze[START_ROW][START_COL] = 3;
        	 System.out.println("Success: " );
         }
         printMaze(maze);
         System.out.println("abc");
    }
}
