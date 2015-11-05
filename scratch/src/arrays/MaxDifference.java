package arrays;

public class MaxDifference {

    /**
     *  O(N) max difference.
     *  Keep a running count of the minimum item.
     *  At each iteration, compare the current height with the minimum height and check if the difference
     *  is < or > the max difference. If > max difference, then update the current max difference.
     * @param arr
     * @return
     */
    public static int maxDiff_1(int[] arr, int start, int end)
    {
        int length = end - start;
        if (length < 2)
            return 0;
        if (length == 2)
            return arr[end] - arr[start];
        int maxdiff = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;
        for (int i = start; i < end; ++i)
        {
            maxdiff = Math.max(maxdiff, arr[i] - minHeight);
            minHeight = Math.min(minHeight, arr[i]);
        }
        return maxdiff;
    }
	public static int maxDiff(int[] arr)
	{
	    if (arr.length < 2)
	        return 0;
		int[] diff = new int[arr.length-1];
		/**
		 * Calculate the adjacent differences first. If there are N elements in the array, there are N-1 differences.
		 */
		for (int i = 0; i < diff.length; ++i)
			diff[i] = arr[i+1]-arr[i];

		int maxdiff = diff[0];
		/**
		 * we have the differences of consecutive items, i.e. A[i] - A[i-1].
		 * Either this is positive or this is negative.
		 * D[i-1] is A[i] - A[i-1] as the consecutive differences are computed above.
		 * If D[i-1] is negative, this means A[i] < A[i-1].
         *  this means D[i] (which is really A[i+1] - A[i]) remains as it is.
         *  The only way this difference is going to increase is if A[i] > A[i-1] as then A[i+1] - A[i] will increase.
         *
         *  comparing current different D[i] against maxdifference, max difference is updated
         *
         *  we only care about d[i-1] as at each step, the d[i] is updated and at (i+1), d[i] has already
         *  been updated.
		 *
		 *
		 */
		for (int i = 1; i < diff.length; ++i)
		{
			if (diff[i-1] > 0)
			{
			    diff[i] = diff[i]+diff[i-1];
			}
			if (maxdiff < diff[i])
			{
			    maxdiff = diff[i];
			}
		}
		return maxdiff;
	}

	/**
	 * O(N^4) solution.
	 * @param a
	 * @return
	 */
	public static int maxDiffPairSum_1(int[] a)
	{
	    // 1 2 3 4 5 6 7 8 9 10
	    // we need to calculate (A[j0] - A[i0]) + (A[j1] -A[i1]) which is max and
	    // i0 < j0 < i1 < j1
	    // brute force solution will be
	    int maxSum = 0;
	    for (int i = 0; i < a.length; ++i)
	    {
	        for (int j = i+1; j < a.length; ++j)
	        {
	            int diff1 = a[j] - a[i];
	            for (int k = j+1; k < a.length; ++k)
	            {
	                int diff2 = 0;
	                for (int l = k+1; l < a.length; ++l)
	                {
	                    diff2 = a[l] - a[k];
	                    if (diff2 + diff1 > maxSum)
	                        maxSum = diff2 + diff1;
	                }
	            }
	        }
	    }
	    return maxSum;
	}

	// O(N^2) as the array is divided into a range, and the max difference between each divided range
	// is found,
	public static int maxDiffPairSum_2(int[] a)
	{
	    int maxdiff = -1;
	    for (int i = 2; i < a.length; ++i)
	    {
	        int[] a1 = new int[i];
	        int[] a2 = new int[a.length-i];
	        System.arraycopy(a, 0, a1, 0, i);
	        System.arraycopy(a, i, a2, 0, a.length-i);
	        int maxdiff1 = maxDiff(a1);
	        int maxdiff2 = maxDiff(a2);
	        if (maxdiff1 + maxdiff2 > maxdiff)
	            maxdiff = maxdiff1 + maxdiff2;
	    }
	    return maxdiff;
	}
	public static void main(String[] args)
	{
		int arr[] = {1, 2, 6, 80, 100};
		int diff4 = maxDiffPairSum_1(arr);
		int diff5 = maxDiffPairSum_2(arr);
		int diff = maxDiff(arr);
		diff = maxDiff_1(arr,0,arr.length);
	}
}
