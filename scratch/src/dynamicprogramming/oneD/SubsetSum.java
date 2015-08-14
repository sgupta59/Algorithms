package dynamicprogramming.oneD;

import java.util.Arrays;

public class SubsetSum {

	public static int subsetsum_cubic(int[] a)
	{
		/*MaxSoFar := 0.0
				for L := 1 to N do
				for U := L to N do
				Sum := 0.0
				for I := L to U do
				Sum : = Sum + X[I]
				 Sum now contains the sum of X[L..U] 
				MaxSoFar := max(MaxSoFar, Sum)*/
		int MaxSoFar = 0;
	    for (int L = 0; L < a.length; ++L)
	    {
	    	for (int U = L; U < a.length; ++U)
	    	{
	    		int sum = 0;
	    		for (int I = L; I <= U; ++I) {
	    			sum = sum + a[I];
	    		}
	    		System.out.println("Sum: " + sum + ", so far: " + MaxSoFar + ", selected: " + Math.min(sum, MaxSoFar));
	    		MaxSoFar = Math.min(sum, MaxSoFar);
	    	}
	    }
	    return MaxSoFar;
	}
	public static int subsetsum_quadraatic(int[] a)
	{
		int maxsum = 0;
		for (int i = 0; i < a.length; ++i) { 
			int sum = 0;
			for (int j = i; j < a.length; ++j) {
				sum += a[j];
				maxsum = Math.max(sum, maxsum);
			}
		}
		return maxsum;
	}
	
	public static int substesum_cummulative(int[] a)
	{
		int maxsum = 0;
		int[] c = new int[a.length];
		c[0] = a[0];
		for (int i = 1; i < a.length; ++i)
			c[i] = c[i-1]+a[i];
		for (int i = 1; i < a.length; ++i)
		{
			for (int j = i; j < a.length; ++j)
			{
				maxsum = Math.max(maxsum, c[j]-c[i-1]);
			}
		}
		return maxsum;
	}
	public static int subsetsum_linear(int[] a) 
	{
		int maxSoFar = 0;
		int maxEndingHere = 0;
		int maxval = 0;
 
		for (int i = 0; i < a.length; ++i)
		{
			int a1 = a[i];
			if (a[i] > maxval)
				maxval = a[i];
			maxEndingHere = Math.max(maxEndingHere+a[i], 0);
			maxSoFar = Math.max(maxEndingHere, maxSoFar);
		}
		return maxSoFar;
	}
	public static void main(String[] args)
	{
		int[] a = {31, -41, 59, 26, -53, 58 ,97 ,-93 ,-23, 84};
		subsetsum_cubic(a);
		substesum_cummulative(a);
		System.out.println("Sum: " + subsetsum_linear(a));
	}
}
