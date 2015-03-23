package dynamicprogramming;
//https://code.google.com/p/algorithmpractise/source/browse/trunk/
//http://www.wou.edu/~broegb/Cs345/MaxSubsequenceSum.pdf
// http://users.cs.fiu.edu/~weiss/dsaajava2/code/MaxSumTest.java
// http://www.wou.edu/~broegb/Cs345/MaxSubsequenceSum.pdf
//http://math.purduecal.edu/~rlkraft/cs33200-2011/chap15/DynamicProgrammingExamples.pdf
// http://www.cs.ru.nl/~chaack/teaching/CIS500s00/Transpar/trans15.pdf
//http://www.cs.ru.nl/~chaack/teaching/CIS500s00/
//https://code.google.com/p/algorithmpractise/source/browse/trunk/DynamicProgramming/1_MaxValueContiguousSubsequence/MaxValueContiguousSubsequence.cpp

public class MaxSubsequenceSum {

	// O(n^3 solution 
	public static int maxSubSum(int[] a)
	{
		int sum = 0;
		for (int idx = 1; idx < a.length; ++idx)
		{
			for (int jdx = idx; jdx < a.length; ++jdx)
			{
				int tmp = 0;
				for  (int kdx = idx; kdx <= jdx; ++kdx)
				{
					tmp += a[kdx];
				}
				if (tmp > sum)
					sum = tmp;
			}
		}
		return sum;
	}
	
	public static int maxSubSum2(int[] a)
	{
		int sum = 0;
		for (int idx = 1; idx < a.length; ++idx)
		{
			int tmp = 0;
			for (int jdx = idx; jdx < a.length; ++jdx)
			{
				tmp += a[jdx];
				if (tmp > sum)
					sum = tmp;
			}
		}
		return sum;
	}
	/**
     * Linear-time maximum contiguous subsequence sum algorithm.
     */
    public static int maxSubSum4( int [ ] a )
    {
        int maxSum = 0, thisSum = 0;

        for( int j = 0; j < a.length; j++ )
        {
            thisSum += a[ j ];

            if( thisSum > maxSum )
                maxSum = thisSum;
            else if( thisSum < 0 )
                thisSum = 0;
        }

        return maxSum;
    }
	public static void main(String[] args)
	{
		int a[ ] = { 0, 4, -3, 5, -2, -1, 2, 6, -2 };
		int sum = maxSubSum(a);
		System.out.println("maxSubSum Cubic " + sum);
		sum = maxSubSum4(a);
		System.out.println("maxSubSum Quadratic " + sum);
	}
}
