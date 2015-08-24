package arrays;

public class MaxDifference {

	public static int maxDiff(int[] arr)
	{
		int[] diff = new int[arr.length-1];
		for (int i = 0; i < diff.length; ++i)
			diff[i] = arr[i+1]-arr[i];
		
		int maxdiff = diff[0];
		for (int i = 1; i < diff.length; ++i) {
			if (diff[i-1] > 0)
				diff[i] = diff[i]+diff[i-1];
			if (maxdiff < diff[i])
				maxdiff = diff[i];
		}
		return maxdiff;
	}
	public static void main(String[] args)
	{
		int arr[] = {1, 2, 6, 80, 100};
		int diff = maxDiff(arr);
	}
}
