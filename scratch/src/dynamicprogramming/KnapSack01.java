package dynamicprogramming;

/**
 * http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/DynProg/knapsack3.html
 * 
 * NOTE: The 0-1 knapsack problem is a binary tree. Either an item goes into the knapsack or it does not.
 * 
 * so, KP(C, k) is the solution where
 *    C = capacity of the knapsack
 *    k = number of items available.
 *    
 *    KP(C,k) = max { KP(C-w[k], k-1) + v[k], KP(C, k-1) }
 *              where C >= w[k]
 *              if C == 0 KP(C,k), i.e KP(0,k) = 0  --> Capacity is 0 so no value
 *              if k == 0 KP(C,k) i.e. KP(C, 0) = 0 --> No items so value is 0
 *     Recursive solution will be exponential as this is how the tree will be generated.
 * @author kg
 *
 */
public class KnapSack01 {

	public static int KnapSack01_i(int[] v, int[] w, int W)
	{
		// rows = items length, columns = Total weight
		int[][] T = new int[w.length+1][W+1];
		// initialize rows, corresponds to j == 0 in recursive case
		for (int idx = 0; idx < W+1;++idx)
		{
			T[0][idx] = 0;
		}
		// initialize columns, if weigth is 0, then there are no items. again
		// this is W==0 in recursive case
		for (int idx = 0; idx < w.length+1;++idx)
		{
			T[idx][0] = 0;
		}
		// for all items now calculate and populate the table
		for (int idx = 1; idx < w.length+1; ++idx)
		{
			// for each item.
			for (int jdx = 1; jdx < W+1;++jdx)
			{
				int val1 = Integer.MIN_VALUE;
				if (jdx >= w[idx-1])
				{
					val1 = v[idx-1]+T[idx][jdx-w[idx-1]];
				}
				int val2 = T[idx-1][jdx];
				T[idx][jdx] = Math.max(val1, val2);
			}
		}
		return T[w.length][W];
	}
	public static int KnapSack01_r(int[] v, int[] w, int W, int j)
	{
		// terminator for recursive condition.
		if (W == 0)
			return 0;
		// if index j is 0, then return
		if (j == 0)
			return 0;
		
		// either pick an item at index j (which is j-1 for array access) 
		// or do not pick the item at j. Then recursively solve for one less item
		int sol1 = Integer.MIN_VALUE;
		if (W >= w[j-1])
		{
			sol1 = v[j-1] + KnapSack01_r(v, w, W-w[j-1], j-1);
		}
		// weight at index j was not picked.
		int sol2 = KnapSack01_r(v, w, W, j-1);
			

		
		return Math.max(sol1, sol2);
	}
	public static void main(String[] args)
	{
		int[] v = {1, 2, 3, 5, 6, 9};
	    int[] w = {2, 3, 5, 8, 9, 13};

	    int W, r;

	    W = 13;                  // Around 53 it starts to slow...
	    r = KnapSack01_r(v,w,W, v.length);
	    r = KnapSack01_recursive(W, w, v, v.length);
	    int[][] memo = new int[W+1][v.length+1];
	    for (int idx = 0; idx < W+1; ++idx)
	    {
	    	for (int jdx = 0; jdx < v.length+1; ++jdx)
	    		memo[idx][jdx] = -1;
	    }
	    r = KnapSack01_r_memoized(W, w,v,v.length,memo);
	    r = KnapSack01_i(v,w,W);
	    System.out.println("abc");
	}
	public static int KnapSack01_recursive(int C, int[] w, int[] v, int k)
	{
		if (k == 0 || C == 0)
			return 0;
		int sol1 = KnapSack01_recursive(C, w, v, k-1);
		int sol2 = 0;
		if (C >= w[k-1])
			sol2 = KnapSack01_recursive(C-w[k-1], w, v, k-1) + v[k-1];
		return Math.max(sol1, sol2);
	}
	public static int KnapSack01_r_memoized(int C, int[] w, int [] v, int k, int[][] memo)
	{
		// if capacity is 0 or items is 0, then return 0.
		if (C == 0 || k == 0)
		{
			memo[C][k] = 0;
			return 0;
		}
		// if memo exists, return that.
		if (memo[C][k-1] != -1)
		{
			return memo[C][k-1];
		}
		else
		{
			memo[C][k-1] = KnapSack01_r_memoized(C, w, v, k-1, memo);
		}
		if (C >= w[k-1])
		{
			if (memo[C-w[k-1]][k-1] != -1)
				return memo[C-w[k-1]][k-1];
			memo[C-w[k-1]][k-1] = KnapSack01_r_memoized(C-w[k-1], w, v, k-1, memo)+v[k-1];
			return Math.max(memo[C][k-1], memo[C-w[k-1]][k-1]);
		}
		return memo[C][k-1];
	
	}
}
