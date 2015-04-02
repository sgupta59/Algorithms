package dynamicprogramming.oneD;

/**
 * Source: https://www.cs.virginia.edu/~shelat/4102/wp-content/uploads/2009/08/L7-09-delivered.pdf
 * 
 * You can take one or two steps on a set of stairs, how many ways are there to get to a stair of
 * n steps.
 * 
 * To find: T(n) = number of ways to get to stair of n steps.
 * 
 * At some step j, j can be reached from either j-1 or from j-2, i.e. 
 * 
 * T(j) = T(j-1) + T(j-2) 
 *       { number of ways to from step j-1 } + {number of ways to get from step j-2}
 * 
 * e.g. T(0) = 1
 *      T(1) = 1, i.e only one way to get to stair#1
 *      T(2) = 2, 2 ways to get to stair#2, from T(0) and T(1)
 *      T(3) = number of ways to get from step#1 + number of ways to get from step #2.
 *           = 1 + 2 = 3
 *           
 *   complexity is: T(n-1) + T(n-2) so exponential? because each node has two children. so 2^n
 * @author kg
 *
 */
public class StairWays {

	/**
	 * Recursive implementation 
	 * I am doing T(0) = 0, i.e. there is 0 ways to get to stair 0 or it can be 1 way to get to 
	 *                      stair 0? Then T(2) does not have to be hard coded.
	 * @param n
	 * @return
	 */
	public static int stairs_r(int n)
	{
		if (n == 1)
			return 1;
		//if (n == 2)
		//	return 2;
		if (n == 0)
			return 1;
		return stairs_r(n-1) + stairs_r(n-2);
	}
	
	// Memoized version, since there is only one variable, this is an array.
	// memo size is n+1 as n is one based and we need stair 0.
	public static int stairs_top_down(int n, int[] memo)
	{
		if (memo[n] != -1)
			return memo[n];
		if (n == 0 || n == 1)
		{
			memo[n] = 1;
			return memo[n];
		}
		memo[n] = stairs_top_down(n-1,memo) + stairs_top_down(n-2,memo);
		return memo[n];
	}
	
	public static int stairs_bottoms_up(int n)
	{
		// create an array to hold the results
		int[] memo = new int[n+1];
		// assign the base case
		memo[0] = 1;
		memo[1] = 1;
		for (int j = 2; j <= n; ++j)
		{
			memo[j] = memo[j-1] + memo[j-2];
		}
		// solution is at memo[n]
		return memo[n];
	}
	 
	public static void main(String[] args)
	{
		int n = 5;
		int res = stairs_r(5);
		System.out.println(res);
		int[] memo = new int[n+1];
		for (int idx = 0; idx < memo.length; ++idx)
			memo[idx] = -1;
		res = stairs_top_down(n, memo);
		System.out.println(res);
		res = stairs_bottoms_up(n);
		System.out.println(res);
	}
}
