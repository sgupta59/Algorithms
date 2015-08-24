package arrays;

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
	public static int maxSubArray_d(int[] arr)
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
	public static void main(String[] args)
	{
		int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println(maxSubArray_d(arr));
		System.out.println(maxSubArray_kdane(arr));
	}
}
