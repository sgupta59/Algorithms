package divideandconquer;

public class FindPeak1D {

	public static int findPeak1D(int[] a, int lo, int hi)
	{
		if (lo == hi)
			return a[lo];
		if (lo+1 == hi)
			return a[lo] > a[hi] ? a [lo] : a[hi];
		int mid = lo + (hi-lo)/2;
		// if mid > mid-1 and mid > mid+1, solution found
		if (a[mid] > a[mid+1] && a[mid] > a[mid-1])
			return a[mid];
		else if (a[mid] > a[mid-1] && a[mid] < a[mid]+1)
			return findPeak1D(a, mid+1,hi);
		return findPeak1D(a,lo, mid-1);
		
	}
	public static void main(String[] args)
	{
		int arr[] = {1, 3, 50, 10, 9, 7, 56};
		if (arr.length == 0)
			throw new IllegalArgumentException("Empty array not accepted");
		int peak = findPeak1D(arr, 0, arr.length-1);
		System.out.println("Peak: " + peak);
	}
}
