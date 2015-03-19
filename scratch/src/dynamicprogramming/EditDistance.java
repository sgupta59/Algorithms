package dynamicprogramming;

public class EditDistance {

	/**
	 * Calculate the edit distance from x to y using recursive formulation. The formulation is:
	 * 
	 * E(i, j) = 
	 *    min of 
	 *        E(i-1, j-1) => if x[i] == y[j]
	 *        E(i-1, j-1) +1 ==> If x[i] != y[j]
	 *        E(i-1, j) + 1  ==> Insert at x[i] a value
	 *        E(i, j-1) + 1  ==> Delete y[j] and compare x(i) and y(j-1)
	 * @param x
	 * @param y
	 * @param i
	 * @param j
	 * @return
	 */
	public static int EditDistance_Recursive(String x, String y, int i, int j)
	{
		if (i == 0)
		{
			// Case#1 
			// x is empty, this means there are length of y insertions to convert x to y.
			return j;
		}
		if (j == 0)
		{
			// Case# 2
			// y is empty, this means there are x.length deletions.
			return i;
		}
		if (x.charAt(i-1) == y.charAt(j-1))
		{
			// Case# 3
			// if the last character is the same, then calculate x-> edit distance for
			// E(i-1, j-1)
			return EditDistance_Recursive(x, y, i-1, j-1);
		}
		else
		{
			// Case# 4 Insert Case
			// something was inserted at say x(i+1), then x(i+1) and y(j) match so 
			// compare x(i) and y(j-1)
			int insertCost = EditDistance_Recursive(x, y, i, j-1) + 1;
			// x(i) was deleted. Now compare
			// x(i-1) and y(j)
			// Case 4 Delete Case
			int deleteCost = EditDistance_Recursive(x, y, i-1, j) + 1;
			// replace x(i) with y(j) or y(j) with x(i), then compare
			// x(i-1) and y(j-1)
			int replaceCost = EditDistance_Recursive(x, y, i-1, j-1) + 1;
			int mincost = insertCost;
			if (mincost > deleteCost)
				mincost = deleteCost;
			if (mincost > replaceCost)
				mincost = replaceCost;
			return mincost;
		}
	}
	public static int EditDistance_memoized(String x, String y)
	{
		// rows = x.length+1
		// cols = y.length+1
		int[][] T = new int[x.length()+1][y.length()+1];
		// if row is 0, then means x is empty and y has data in it, so
		// Case# 1 from recursive solution
		for (int j = 0; j < y.length()+1; ++j)
			T[0][j] = j;
		// for j = 0, i..e column 0, means y is empty and x has data in it.
		// this represents either inserts on x or deletes on y
		// Case# 2 in recursive case
		for (int i = 0; i < x.length()+1; ++i)
			T[i][0] = i;
		for (int i = 1; i < x.length()+1; ++i)
		{
			for (int j = 1; j < y.length()+1; ++j)
			{
				if (x.charAt(i-1) == y.charAt(j-1))
				{
					// Case# 3 from recursive. Notice that T[i][j] is populated
					T[i][j] = T[i-1][j-1];
				}
				else
				{
					// Case# 4 Insert Case
					int insertCost = T[i][j-1];
					// Case#4 Delete on X
					int deleteCost = T[i-1][j];
					// Case#4 Replace Case
					int replaceCost = T[i-1][j-1];
					int min = insertCost;
					if (deleteCost < min)
						min = deleteCost;
					if (replaceCost < min)
						min = replaceCost;
					T[i][j] = min+1;
				}
			}
		}
		return T[x.length()][y.length()];
	}
	
	public static void main(String[] args)
	{
		String s1 = "man";
		String s2 = "moon";
		
		int mincost = EditDistance_Recursive(s1, s2, s1.length(), s2.length());
		int mincost1 = EditDistance_memoized(s1, s2);
		System.out.println("Cost is: " + mincost + " " + mincost1 + " diff " + (mincost-mincost1) );
		
	}
}
