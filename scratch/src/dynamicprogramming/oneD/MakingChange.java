package dynamicprogramming.oneD;

public class MakingChange {

	public static int[] coins = {25,10,5,1};
	
	public static int makeChange(int amount)
	{
		if (amount <= 0)
			return 0;
		int retval = Integer.MAX_VALUE;
		for (int idx = 0; idx < coins.length; ++idx)
		{
			if (coins[idx] <= amount)
			{
				int count = makeChange(amount-coins[idx])+1;
				if (count < retval)
					retval = count;
			}
		}
		return retval;
	}
	
	public static int makeChangeMemoized(int amount, int[] memo)
	{
		if (amount<= 0)
		{
			return 0;
		}
		if (memo[amount] > 0)
		{
			return memo[amount];
		}
		int reval = Integer.MAX_VALUE;
		for (int idx = 0; idx < coins.length; ++idx)
		{
			if (coins[idx] <= amount )
			{
				int count = 0;
				count = makeChangeMemoized(amount-coins[idx],memo)+1;
			    if (count < reval)
				{
					reval = count;
				}
			}
		}
		memo[amount] = reval;
		return reval;
	}
	public static void main(String[] args)
	{
		int size = makeChange(30);
		System.out.println("Recursive change : " + size);
		int[] memo = new int[31];
		for (int idx = 0; idx < 31; ++idx)
			memo[idx] = 0;
		size = makeChangeMemoized(30, memo);
		System.out.println("Memoized Recursive change : " + size);
	}
}
