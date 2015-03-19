package divideandconquer;

/* http://www.geeksforgeeks.org/median-of-two-sorted-arrays/ */
public class MedianMergeSort {

	/** http://theoryapp.com/find-median-of-two-sorted-arrays/ */
	// Find median of two sorted arrays that might not be equal.
	// Space Complexity is O(1)
	// Time complexity is O(n)
	public static int getMedianMergeSort(int[] a, int[] b)
	{
		// get total length of the two arrays
		int n = a.length + b.length;
		int i = 0; int j = 0; int count = 0;
		int prev = 0; int curr = 0;
		for (int idx = 0; idx < n; ++idx)
		{
			// if a reached at the end, this means all elements in a are now smaller
			// than the jth element in b. This is the median as all elements after j
			// are larger than the jth element in b
			if (idx == a.length)
			{
				curr = b[j++];
			}
			else if (idx == b.length)
			{
				// if b's length is reached, this means all elements in b are now smaller
				// than ith element in a, and the ith element in a is smaller than (i+1)th 
				// element in a
				curr = a[i++];
			}
			else if (a[i] < b[j])
			{
				// This means a[i] is less than b[j] and a[i] > a[i-1] and b[j-1] so the current median
				curr = a[i++];
			}
			else 
			{
				curr = b[j++]; // reverse of above, 
			}
			// this is the middle
			if (idx == n/2)
				break;
			prev = curr;
		}
		if (n%2 != 0)
			return curr;
		return (prev+curr)/2;
	}
	
	public static void main(String[] args)
	{
		int ar1[] = {1, 12, 15, 26, 38};
	    int ar2[] = {2, 13, 17, 30, 45};
	    //int ar1[] = {1, 12, 15, 26};
	    //int ar2[] = {40,45,55,56,58};
	    int median = getMedianMergeSort(ar1, ar2);
	    System.out.println("Median: " + median);
	}
}
