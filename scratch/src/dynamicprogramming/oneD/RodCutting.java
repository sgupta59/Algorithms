package dynamicprogramming.oneD;

/**
 * http://cc.ee.ntu.edu.tw/~ywchang/Courses/Alg/unit4.pdf
 * https://www.cs.virginia.edu/~shelat/4102/wp-content/uploads/2009/08/L7-09-delivered.pdf
 * 
 * Given a rod of length n and prices for different widths find the maximum cost such that
 * the price is maximized while the length remains <=n
 * e.g.
 *  w[i] = 1,2,3,4, 5, 6, 7, 8, 9
 *  p[i] = 1,5,7,9,10,16,18,20,22
 *  n = 8
 *  
 *  Needed is OPT(n). 
 *  at OPT(n), we need max of { p(1) + OPT(n-1) , p(2) + opt(n-2), p(3) + opt(n-3)... p(n) + opt(0)
 *  i.e. from the end, cut off 1", 2", 3", 4", ... etc.
 *  Base cases will be
 *  p(0) = 0, i.e price of zero width is 0
 *  p(<1) = 0, i.e. price of width less than 0 is 0.
 *  
 *  This is a tree with n children and each of these can further have n children.
 *  
 * @author kg
 *
 */
public class RodCutting 
{
	// cost = O(2^n)
	public static int rodcut_r(int[] width, int[] price, int[] cut, int length)
	{
		if (length == 0)
		{
			return 0;
		}
		int opt = Integer.MIN_VALUE;
		for (int idx = 1; idx < width.length; ++idx)
		{
			if (width[idx] <= length)
			{
				int res = rodcut_r(width, price, cut,length-width[idx])+price[idx];
				if (res > opt)
				{
					opt = res;
					cut[length] = width[idx];
				}
			}
		}
		return opt;
	}
	public static int rodcut_topdown(int[] width, int[] price, int[] cut, int[] memo, int length)
	{
		if (length == 0)
		{
			memo[length] = 0;
			return memo[length];
		}
		if (memo[length] != -1)
		{
			return memo[length];
		}
		int opt = Integer.MIN_VALUE;
		 
		for (int idx = 1; idx < width.length; ++idx)
		{
			if (width[idx] <= length)
			{
				int sol = rodcut_topdown(width,price, cut, memo, length-width[idx])+price[idx];
				if (sol > opt)
				{
					opt = sol;
					 
					cut[length] = width[idx];
				}
			}
		}
		memo[length] = opt;
		return memo[length];
	}
	public static int rodcut_bottomsup(int[] width, int[] price, int[] cut, int length)
	{
		int[] memo = new int[width.length];
		memo[0] = 0;
		for (int idx = 1; idx < width.length; ++idx)
		{
			int opt = Integer.MIN_VALUE;
			for (int jdx = 1; jdx <= idx; ++jdx)
			{
				int val = price[jdx] + memo[idx-jdx];
				if (val > opt)
				{
					opt = val;
					memo[idx] = val;
					cut[idx] = width[jdx];
				}
			}
		}
		return memo[length];
	}
	
	  
	public static void printCuts(int[] cuts, int num)
	{
		while (num > 0)
		{
			System.out.println("Cut Index: " + cuts[num]);
			num = num - cuts[num];
		}
	}
	public static void main(String[] args)
	{
		int[] n =     {0,1,2 ,3, 4, 5, 6, 7, 8, 9, 10};
		int[] price = {0,1, 5 ,8 ,9, 10, 17 ,17 ,20, 24 ,30};
		int[] temp = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] s = {0,0,0,0,0,0,0,0,0,0,0};
		int[] cut = new int[n.length];
		 
		int maxprice = rodcut_r(n,price,cut,8);
		System.out.println("Max Revenue for 8 : " + maxprice);
		// for n = 8, solution is (2,6) = 22 validated.
		// for n = 4, solution 0s (2,2) = 10 validated.
		// solutions in http://cc.ee.ntu.edu.tw/~ywchang/Courses/Alg/unit4.pdf
		printCuts(cut, 8);
		int[] memo = new int[n.length];
		for (int idx = 0; idx < memo.length; ++idx)
			memo[idx] = -1;
		maxprice = rodcut_topdown(n,price,cut,memo,8);
		printCuts(cut, 8);
		maxprice = rodcut_bottomsup(n,price,cut,8);
		printCuts(cut, 8);

	}
	
}
