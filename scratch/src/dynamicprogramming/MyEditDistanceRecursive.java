package dynamicprogramming;

public class MyEditDistanceRecursive {

	public static int REPLACE = 0;
	public static int INSERT = 1;
	public static int DELETE = 2;
	static int M = 10;
	static int N = 10;
	static class Cell {
		int cost;
		int parent;
	}
	// The table is rows = pattern, column = text i.e.
	//        [a] [b] [c] [d] 
	//  [a]
	//  [b]
	//  [c] 
	// pattern = abc
	// text = abcd.
	static Cell[][] table = new Cell[M][N];
	
	public static void initTable()
	{
		for (int idx = 0; idx < M; ++idx)
			for (int jdx = 0; jdx < N; ++jdx)
			{
				Cell cell  = new Cell();
				cell.cost = -1;
				cell.parent = -1;
				table[idx][jdx] = cell;
			}
		// for [row][0] the pattern text can be deleted to match 0 characters.
		for (int idx = 1; idx < M; ++idx)
		{
			table[idx][0].cost = idx;
			table[idx][0].parent = DELETE;
		}
		// for [0][jdx] columns, i.e. first row, the text can be inserted into empty pattern to
		// get number of inserts
		for (int jdx = 1; jdx < N; ++jdx)
		{
			table[0][jdx].cost = jdx;
			table[0][jdx].parent = INSERT;
		}
	}
	public static int matchCost(char c1, char c2)
	{
		if (c1 == c2)
			return 0;
		return 1;
	}
	public static int editDistance(String s, String t, int i, int j)
	{
		if (i == 0 && j == 0)
			return 0;
		if (i == 0) return j; // insertions
		if (j == 0) return i; // deletions
		int costs[] = {0,0,0};
		costs[REPLACE] = editDistance(s, t, i-1, j-1) + matchCost(s.charAt(i), t.charAt(j));
		costs[INSERT] = editDistance(s, t, i, j-1) + 1;
		costs[DELETE] = editDistance(s, t, i-1, j) + 1;
		int defcost = costs[REPLACE];
		table[i][j].parent = REPLACE;
		for (int idx = 0; idx < 3; ++idx)
		{
			if (costs[idx] < defcost)
			{
				table[i][j].parent = idx;
				defcost = costs[idx];
			}
		}
		table[i][j].cost = defcost;
		return defcost;
	}
	public static void main(String[] args)
	{
		String pattern = "abc";
		String text = "abcd";
		
	}
}
