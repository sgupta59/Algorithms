package dynamicprogramming;

public class MakingChange {

	public static int makeChange_DP(int[] coins, int n)
	{
		if (n == 0)
			return 0;
		int[] amt = new int[n+1];
		amt[0] = 0;
		int[] newsol = new int[coins.length];
		for (int idx = 1; idx < amt.length; ++idx)
		{
			for (int kdx = 0; kdx < coins.length; ++kdx)
				newsol[kdx] = Integer.MAX_VALUE;
			for (int kdx = 0; kdx < coins.length; ++kdx)
			{
				if (coins[kdx] <= idx)
				{
					newsol[kdx] = 1 + amt[idx - coins[kdx]];
				}
			}
			int min = newsol[0];
			for (int kdx = 1; kdx < coins.length;++kdx)
			{
				if (newsol[kdx] < min)
				{
					min = newsol[kdx];
				}
			}
			amt[idx] = min;
		}
		return amt[n];
	}
	// this is a recrsive solution, so needs a terminating condition
	public static int makeChange_Recursive(int[] coins, int n)
	{
		if (n == 0)
			return 0;
		int[] mysol = new int[coins.length];
		for (int idx = 0; idx < coins.length; ++idx)
			mysol[idx] = Integer.MAX_VALUE;
		for (int idx = 0; idx < coins.length; ++idx)
		{
			if (coins[idx] <= n)
			{
				mysol[idx] = makeChange_Recursive(coins, n-coins[idx]) + 1;
			}
		}
		int minvalue = mysol[0];
		for (int idx = 1; idx < coins.length; ++idx)
		{
			if (minvalue > mysol[idx])
				minvalue = mysol[idx];
		}
		return minvalue;
	}
	public static void main(String[] args)
	{
		int[] coins = {1, 3, 9, 19, 26};
		int n = 66;
		// int ncoins = makeChange_Recursive(coins, n);
		//System.out.println("Coins: " + ncoins);
		int ncoins1 = makeChange_DP(coins, n);
		System.out.println("Coins: " + ncoins1);
	}
}
