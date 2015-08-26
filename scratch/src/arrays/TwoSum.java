package arrays;
import java.util.Arrays;
import java.util.HashMap;


public class TwoSum {

	public static int TwoSum_bruteForce(int[] a, int x)
	{
		int count = 0;
		for (int i = 0; i < a.length; ++i)
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

	public static int TwoSum_scan(int[] a, int x)
	{
		int count = 0;
		for (int i = 0, j = a.length-1; i < j ; )
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

	public static int TwoSum_binarySearch(int[] a, int x)
	{
		int count = 0;
	     Arrays.sort(a);
	     for (int i = 0; i < a.length/2; ++i)
	     {
	    	 int diff = x - a[i];
	    	 if (diff > a[i])
	    	 {
	    		 int idx = Arrays.binarySearch(a, i+1,a.length,diff);
	    		 if (idx > 0)
	    		 {
	    			 System.out.println("Pair: (" + a[i] + "," +  a[idx] + ")");
	    			 ++count;
	    		 }
	    	 }
	     }
	     return count;
	}
	public static void main(String[] args)
	{
		int[] intArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int count =  TwoSum_binarySearch(intArray,16);
		assert count == 8;
	}
}
