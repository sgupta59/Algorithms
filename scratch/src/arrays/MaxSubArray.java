package arrays;
/**
 * Source: http://www.cse.ust.hk/faculty/golin/COMP271Sp03/Notes/L02.pdf
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class MaxSubArray {

	public static int maxSubArray_kdane(int[] arr)
	{
		int max_curr = 0;
		int max = 0;
		for (int i = 0; i < arr.length; ++i)
		{
			max_curr = max_curr + arr[i];
			if (max_curr < 0)
				max_curr = 0;
			max = Math.max(max, max_curr);
		}
		return max;
	}
	public static int maxSubArray_dynamic(int[] arr)
	{
		int curr = 0;
		int max = 0;
		for (int i = 0; i < arr.length; ++i)
		{
			curr = Math.max(arr[i], arr[i]+curr);
			max = Math.max(max, curr);
		}
		return max;
	}

	public static int maxSubArray_linear(int[] arr)
	{
	    int[] tmparr = new int[arr.length];
	    tmparr[0] = arr[0];
	    for (int i = 1; i < tmparr.length; ++i)
	        tmparr[i] = arr[i] + tmparr[i-1];
	    int min = arr[0];
	    int max = arr[0];
	    for (int i = 1; i < tmparr.length; ++i)
	    {
	        // sum up to i is this.
	        int sum_i = tmparr[i-1]+arr[i];
	        if (sum_i - min > max)
	            max = sum_i - min;
	        if (sum_i < min)
	            min = sum_i;
	    }
	    return max;
	}
	/**
	 * Brute force solution example. This is O(N^3)
	 * @param a
	 * @return
	 */
	public static int maxSubArraySum_bruteForce(int[] a)
	{
	    int maxsum = 0;
	    for (int i = 0; i < a.length; ++i)
	    {
	        for (int j = i; j < a.length; ++j)
	        {
	            int sum = 0;
	            for (int k = i; k <= j; ++k)
	                sum = sum + a[k];
	            maxsum = Math.max(sum, maxsum);
	        }
	    }
	    return maxsum;
	}

	public static int maxSubArraySum_N2(int[] a)
	{
	    int maxsum = 0;
	    for (int i = 0; i < a.length; ++i) {
	        int sum = 0;
	        for (int j = i; j < a.length; ++j) {
	            sum = sum + a[j];
	            maxsum = Math.max(sum, maxsum);
	        }
	    }
	    return maxsum;
	}

	public static int maxSubArraySum_DivideAndConquer(int[] a, int lo, int hi)
	{
	    if (lo == hi)
	        return a[lo];
	    int mid = lo + (hi-lo)/2;
	    int left = maxSubArraySum_DivideAndConquer(a, lo, mid);
	    int right = maxSubArraySum_DivideAndConquer(a, mid+1, hi);
	    int sum_l = Integer.MIN_VALUE;
	    int sum = 0;
	    for (int i = mid; i >= lo; --i)
	    {
	        sum = sum + a[i];
	        if (sum > sum_l)
	            sum_l = sum;
	    }
	    int sum_r = Integer.MIN_VALUE;
	    sum = 0;
        for (int i = mid+1; i <= hi; ++i)
        {
            sum = sum + a[i];
            if (sum > sum_r)
                sum_r = sum;
        }
        int sum_c = sum_l+sum_r;
        if (sum_c > left)
            return Math.max(sum_c, right);
        return Math.max(left, right);
	}
	public static void main(String[] args)
	{
		int[] arr1 = {-2, -3, 4, -1, -2, 1, 5, -3};
		/** Max sum is 5 + 2 - 1 + 3 = 9 */
		/** If each item represents profits made in a year, then the max profit made was 9 dollars from year year 5 to year 8 */
		int[] arr = { -3, 2, 1, -4, 5, 2, -1, 3, -1};
		System.out.println(maxSubArraySum_bruteForce(arr));
		System.out.println(maxSubArraySum_N2(arr));
		System.out.println(maxSubArraySum_DivideAndConquer(arr,0,arr.length-1));
		System.out.println(maxSubArray_kdane(arr));
		System.out.println(maxSubArray_dynamic(arr));
	}
}
