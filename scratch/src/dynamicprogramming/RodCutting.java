package dynamicprogramming;

/**
 * http://cc.ee.ntu.edu.tw/~ywchang/Courses/Alg/unit4.pdf
 * @author kg
 *
 */
public class RodCutting 
{
	// cost = O(2^n)
	public static int cutRodRecursive(int[] price, int length)
	{
		if (length == 0)
			return 0;
		int q = Integer.MIN_VALUE;
		for (int idx = 1; idx <= length; ++idx)
		{
			int cost = price[idx] + cutRodRecursive(price, length-idx);
			if (cost > q)
				q = cost;
		}
		return q;
	}
	// cost = O(n^2) top down recursion.
	public static int curRodMemoized(int[] n, int[] price, int[] r, int num)
	{
		if (num == 0)
		{
			r[num] = 0;
			return num;
		}
		if (r[num] > 0)
			return r[num];
		int q = Integer.MIN_VALUE;
		for (int idx = 1; idx <= num; ++idx)
		{
			int cost = price[idx] + curRodMemoized(n, price, r, num-idx);
			if (cost > q)
				q = cost;
		}
		r[num] = q;
		return q;
	}
	public static int cutRodBottomsUp(int[] price, int[] r, int[] s, int num)
	{
		if (num == 0)
		{
			r[num] = 0;
			s[num] = 0;
			return num;
		}
		if (r[num] >= 0)
			return r[num];
		for (int idx = 1; idx <= num; ++idx)
		{
			int q = Integer.MIN_VALUE;
			for (int jdx = 1; jdx <= idx; ++jdx)
			{
				int cost1 = price[jdx] + cutRodBottomsUp(price, r, s, idx-jdx);
				if (cost1 > q)
				{
					s[idx] = jdx;
					q = cost1;
				}
			}
			r[idx] = q;
		}
		return r[num];
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
		int[] n =     {0,1,2,3,4, 5, 6, 7, 8, 9};
		int[] price = {0,1,5,7,9,10,16,18,20,22};
		int[] temp = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] s = {0,0,0,0,0,0,0,0,0,0};
		int maxprice = cutRodRecursive(price, 8);
		System.out.println("Max Revenue for 8 : " + maxprice);
		 //maxprice = curRodMemoized(n, price, temp, 8);
		//System.out.println("Max Revenue for 8 : " + maxprice);
		maxprice = cutRodBottomsUp(  price, temp, s, 8);
		System.out.println("Max Revenue for 8 : " + maxprice);
		printCuts(s, 8);
	}
}
