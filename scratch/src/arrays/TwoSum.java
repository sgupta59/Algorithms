package arrays;
import java.util.Arrays;
import java.util.HashMap;


public class TwoSum {

    /**
     * Brute force solution. O(N^2).
     * @param a
     * @param x
     * @return
     */
	public static int TwoSum_bruteForce(int[] a,int lo, int hi, int x)
	{
		int count = 0;
		for (int i = lo; i < hi; ++i)
		{
			for (int j = i+1; j < a.length; ++j)
			{
				if (a[i]+a[j] == x)
				{
					System.out.println("Pair: (" + a[i] + "," + a[j] + ")");
					++count;
				}
			}
		}
		return count;
	}

	/**
	 *
	 * Hash map solution, O(N) but with O(N) space.
	 * Find 2 items that sum to x.
	 * Store <x-a[i], a[i]> in a map
	 * x-a[i] is the an a[j] such that a[j] + a[i] = x i.e. a[j] = x - a[i]
	 * just look up the value.
	 * does not have to be sorted.
	 * @param a
	 * @param x
	 * @return
	 */
	public static int TwoSum_hashed(int[] a, int x)
	{
		int count = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i < a.length; ++i)
		{
			int diff = x-a[i];
			if (map.get(a[i]) != null) {
				int v2 = map.get(a[i]);
				System.out.println("Pair: (" + a[i] + "," + v2 + ")");
				++count;
			} else {
				map.put(diff, a[i]);
			}
		}
		return count;
	}

	/**
	 * O(N) solution but needs sorting.
	 *  Sort the input aray.
	 *  For each item a[i], check the ending element A[j] (j = n-1 initially)
	 *  if a[i] + a[j] == x, this is a solution.
	 *  if a[i] + a[j] > x, then a[j] is larger, decreemnt j by 1 and try again.
	 *  if a[i] + a[j] < x, then increment a[i] to increase the sum.
	 * @param a
	 * @param x
	 * @return
	 */
	public static int TwoSum_scan(int[] a, int lo, int hi, int x)
	{
		int count = 0;
		Arrays.sort(a);
		for (int i = lo, j = hi-1; i < j ; )
		{
			if (a[i] + a[j] == x)
			{
				System.out.println("Pair: (" + a[i] + "," +  a[j] + ")");
				++i;
				--j;
				++count;
				continue;
			}
			if (a[i] + a[j] > x)
			{
				--j;
				continue;
			}
			if (a[i] + a[j] < x)
			{
				++i;
				continue;
			}
		}
		return count;
	}

	public static int TwoSum_binarySearch(int[] a, int lo, int hi, int x)
	{
		int count = 0;

	     for (int i = lo; i < hi/2; ++i)
	     {
	    	 int diff = x - a[i];
	    	 /**
	    	  * Checking for diff > a[i] as this is a sorted array, the diff is the other item
	    	  * and it has to be greater than a[i]
	    	  */
	    	 if (diff > a[i])
	    	 {
	    		 int idx = Arrays.binarySearch(a, i+1,hi,diff);
	    		 if (idx > 0)
	    		 {
	    			 System.out.println("Pair: (" + a[i] + "," +  a[idx] + ")");
	    			 ++count;
	    		 }
	    	 }
	     }
	     return count;
	}

	/**
	 * Find if an array has elements that sum to 0.
	 * This algorithm takes cubic time.
	 * @param a
	 * @return
	 */
	public static void ThreeSum_cubic(int[] a)
	{
	    for (int i = 0; i < a.length; ++i)
	    {
	        for (int j = i+1; j < a.length; ++j)
	        {
	            for (int k = j + 1; k < a.length; ++k)
	            {
	                if (a[i] + a[j] + a[k] == 0)
	                    System.out.println("[" + a[i] + "," + a[j] + "," + a[k] + "]");
	            }
	        }
	    }
	}

	/**
	 * a N^2logn solution.
	 * sort the array at cost nlogn
	 * then for each tuple<i, j> do a binary search for k.
	 * @param args
	 */
	public static void ThreeSum_binsearch(int[] a, int k)
	{
	    // sort the array
	    Arrays.sort(a);
	    for (int i = 0; i < a.length; ++i)
	    {
	        for (int j = i+1; j < a.length; ++j)
	        {
	            int diff = k - (a[i] + a[j]);
	            int idx = Arrays.binarySearch(a, j+1, a.length, diff);
	            if (idx > 0)
	            {
	                System.out.println("[" + a[i] + "," + a[j] + "," + a[idx] + "]");
	            }
	        }
	    }
	}

	public static void ThreeSum_quadratic(int[] a, int k)
	{
	    // sort the array
	    Arrays.sort(a);
	    for (int i = 0; i < a.length; ++i)
	    {
	        int diff = k - a[i];
	        // search a[i+1...n] for the diff item.
	        // there could be more than one matches but all are found in O(n) time
	    }
	}
	public static void main(String[] args)
	{
		int[] intArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int count1 = TwoSum_bruteForce(intArray, 0, intArray.length, 16);
		/** Sort the array here as both the 2 options use the sorted array. */
		Arrays.sort(intArray);
		int count2 =  TwoSum_binarySearch(intArray,0, intArray.length,16);
		int count3 = TwoSum_scan(intArray,0, intArray.length,16);
		int[] a = {-1,0,1,2,-1, -4};
		ThreeSum_cubic(a);
		ThreeSum_binsearch(a,0);
		assert count1 == 8;
	}
}
