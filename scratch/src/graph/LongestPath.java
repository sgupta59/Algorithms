package graph;

import generics.chapter4.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class LongestPath {

    private static int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    private static int getLength_doit(char[][] mat, int[][] dp, int xpos, int ypos, char s)
    {
    	// if x and y are not valid, return 0.
    	if (xpos < 0 || xpos >= mat.length || ypos >= mat[xpos].length || ypos < 0)
    		return 0;
    	if (mat[xpos][ypos] != s+1)
    		return 0;
    	if (dp[xpos][ypos] != -1)
    		return dp[xpos][ypos];
    	int sum = 0;
    	// now move.
    	for (int k = 0; k < x.length; ++k)
    		sum = Math.max(sum, 1+getLength_doit(mat,dp,xpos+x[k],ypos+y[k], mat[xpos][ypos]));
    	return dp[xpos][ypos] = sum;
    }
	public static int getLength(char[][] a, char s)
	{
		int[][] dp = new int[a.length][];
		for (int i = 0; i < dp.length; ++i)
			dp[i] = new int[a[0].length];
		for (int i = 0; i < a.length; ++i)
			for (int j = 0; j < a[i].length; ++j)
				dp[i][j] = -1;
		int len = 0;
		// find the path for all occurances of character s
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[i].length; ++j) {
				// if this cell is the character s, then look for paths.
				if (a[i][j] != s)
					continue;
				// make a move and get the path from s to the next cell.
				// The return value will be either 0 or path from s's successor to the end.
				for (int k = 0; k < x.length; ++k) 
					len = Math.max(len, 1+getLength_doit(a, dp, i+x[k],j+y[k],s));
			}
		}
		return len;
	}
	
	private static LinkedList<Pair<Integer,Integer>> getPositions(Pair<Integer,Integer> pos, char[][] mat, char s)
	{
		LinkedList<Pair<Integer,Integer>> list = new LinkedList<Pair<Integer,Integer>>();
		// given a position in the graph, get all its children.
		for  (int k = 0; k < x.length; ++k)
		{
			int x_1 = pos.first+x[k];
			int y_1 = pos.second+y[k];
			if (x_1 < 0 || x_1 >= mat.length || y_1 < 0 || y_1 >= mat.length )
				continue;
			if (s+1 != mat[x_1][y_1])
				continue;
			list.add(new Pair<Integer, Integer>(x_1,y_1));
		}
		return list;
	}
	private static int getLength_bfs_doit(char[][] mat, int[][] dp, char s, int i, int j)
	{
		int sum = 0;
		//if (i <0 || i >= mat.length || j < 0 || j >= mat.length)
		//	return sum;
		int maxpath = Integer.MIN_VALUE;
		// do a bfs from (i,j).
		Queue<Pair<Integer,Integer>> queue = new LinkedList<Pair<Integer,Integer>>();
		// add the input to the queue
		queue.offer(new Pair<Integer, Integer>(i,j));
		++dp[i][j];
		maxpath = dp[i][j];
		while (!queue.isEmpty()) 
		{
			// get the queue value
			Pair<Integer,Integer> pos = queue.poll();
			LinkedList<Pair<Integer,Integer>> children = getPositions(pos,mat,mat[pos.first][pos.second]);
			if (children.size() == 0)
				maxpath =Math.max(maxpath, dp[pos.first][pos.second]);
			for (Pair<Integer,Integer> child : children) 
			{
				queue.offer(child);
				dp[child.first][child.second] =  dp[pos.first][pos.second]+1;
			}
			

		}
		return maxpath;
	}
	public static int getLength_bfs(char[][] mat, char s)
	{
		int[][] dp = new int[mat.length][];
		for (int i = 0; i < mat.length; ++i)
			dp[i] = new int[mat[i].length];
		for (int i = 0; i < mat.length; ++i)
			for  (int j = 0; j < mat[0].length; ++j)
				dp[i][j] = 0;
		int sum = 0;
		for (int i = 0; i < mat.length; ++i) {
			for (int j = 0; j < mat.length; ++j) {
				if (mat[i][j] != s)
					continue;
				//for (int k = 0; k < x.length; ++k) 
				sum = Math.max(sum, getLength_bfs_doit(mat, dp, s, i , j ));
			}
		}
		return sum;
	}
	public static void main(String[] args)
	{
		char[][] mat = { 
				{'a','c','d'},
                { 'h','b','a'},
                { 'i','g','f'}};
		System.out.println(getLength_bfs(mat,'a'));
		System.out.println(getLength(mat,'e'));
		System.out.println(getLength(mat,'b'));
		System.out.println(getLength(mat,'f'));		
	}
}
