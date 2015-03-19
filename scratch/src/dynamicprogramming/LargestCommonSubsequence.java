package dynamicprogramming;

public class LargestCommonSubsequence {

	public static int LCS_recursive(String x, String y, int i, int j)
	{
		// If one of the two indices is 0, then there is no common subsequence.
		if (i == 0 || j == 0)
		{
			return 0;
		}
		// if last characters match, then return 1+ recursive solution.
		if (x.charAt(i-1) == y.charAt(j-1))
		{
			return 1+LCS_recursive(x, y, i-1, j-1);
		}
		// find the LCS of i-1, j, i.e. delete ith element 
		int cost1 = LCS_recursive(x, y, i-1, j);
		// find LCS of i, j-1, i.e. delete jth element
		int cost2 = LCS_recursive(x, y, i, j-1);
		// return the max of the two
		return Math.max(cost1, cost2);
	}
	public static int LCS_memoized(String x, String y,int i, int j, int[][] T)
	{
		if (i ==0 || j == 0)
			return 0;
		if (T[i][j] != -1)
			return T[i][j];
		// if last characters match, then return 1+ recursive solution.
		if (x.charAt(i-1) == y.charAt(j-1))
		{
			T[i][j] =  1+LCS_recursive(x, y, i-1, j-1);
			return T[i][j];
		}
		// find the LCS of i-1, j, i.e. delete ith element 
		int cost1 = LCS_recursive(x, y, i-1, j);
		// find LCS of i, j-1, i.e. delete jth element
		int cost2 = LCS_recursive(x, y, i, j-1);
		// return the max of the two
		T[i][j] =  Math.max(cost1, cost2);
		return T[i][j];
	}
	public static int LCS_tableBased(String x, String y)
	{
		int[][] T = new int[x.length()+1][y.length()+1];
		// For all rows, column 0 is 0 as if it is empty, thre is no LCS
		// This is the terminating condition in recursion.
		for (int idx = 0; idx < x.length()+1; ++idx)
			T[idx][0] = 0;
		// for all columns, row 0 is 0 as this means there is an empty string.
		// this corresponds to i==0 in recursion
		for (int jdx = 0; jdx < y.length()+1; ++jdx)
			T[0][jdx] = 0;
		
		for (int i = 1; i < x.length()+1; ++i)
		{
			for (int j = 1; j < y.length()+1; ++j)
			{
				if (x.charAt(i-1) == y.charAt(j-1))
				{
					T[i][j] = 1+T[i-1][j-1];
				}
				else
				{
					T[i][j] = Math.max(T[i-1][j], T[i][j-1]);
				}
			}
		}
		return T[x.length()][y.length()];
	}
	public static int LCS_tableBasedLinear(String x, String y)
	{
		int[][] T = new int[2][y.length()+1];

		// for all columns, row 0 is 0 as this means there is an empty string.
		// this corresponds to i==0 in recursion
		for (int jdx = 0; jdx < y.length()+1; ++jdx)
			T[0][jdx] = 0;
		
		for (int i = 1; i < x.length()+1; ++i)
		{
			T[1][0] = 0;
			for (int j = 1; j < y.length()+1; ++j)
			{
				if (x.charAt(i-1) == y.charAt(j-1))
				{
					T[1][j] = 1+T[0][j-1];
				}
				else
				{
					T[1][j] = Math.max(T[0][j], T[1][j-1]);
				}
			}
			for (int idx = 0; idx < y.length()+1; ++idx)
				T[0][idx] = T[1][idx];
		}
		return T[1][y.length()];
	}
	public static void main(String[] args)
	{
		String x = "BAC";
		String y = "ABCB";
		int cost = LCS_recursive(x, y, x.length(), y.length());
		System.out.println("Recursive Cost: " + cost);
		cost = LCS_tableBased(x, y);
		System.out.println("tableBased Cost: " + cost);
		int[][] T = new int[x.length()+1][y.length()+1];
		for (int idx = 0; idx < x.length()+1; ++idx)
		{
			for (int jdx = 0; jdx < y.length()+1; ++jdx)
			{
				T[idx][jdx] = -1;
			}
		}
		cost = LCS_memoized(x, y, x.length(), y.length(), T);
		System.out.println("memoized Cost: " + cost);
		cost = LCS_tableBasedLinear(x,y);
		System.out.println("Linear Cost: " + cost);
	}
}
