package arrays;

public class PeakFinding {

	public static int peak1D(int[] a, int i, int j)
	{
		int mid = i + (j-i)/2;
		if ((mid==0 || a[mid]  >= a[mid-1] ) && (mid+1 == a.length || a[mid] >= a[mid+1]))
			return a[mid];
		if (a[mid] < a[mid+1])
			return peak1D(a, mid+1,j);
		return peak1D(a, i, mid-1);
	}
	
	/**
	 * creatre an aux array to store maxima of each column.
	 * search this aux array for a peak
	 * complexity is:
	 *  		N^2 for finding the aux array
	 *          logn for finding peak in aux so
	 *          N^2 + logn
	 * @param a
	 */
	public static int peak2D(int[][] a)
	{
		int[] aux = new int[a[0].length];
		int nrows = a.length;
		int ncols = a[0].length;
		for (int col = 0; col < a[0].length; ++col) {
			for (int row = 0; row < a.length; ++row) {
				if (a[row][col] > aux[col])
					aux[col] = a[row][col];
			}
		}
		return peak1D(aux,0,aux.length-1);
	}
	
	public static int findMaxIdx(int[][] a, int col)
	{
		int ridx = 0, max = 0;
		for (int row = 0 ;row < a.length; ++row)
		{
			if (a[row][col] > max) {
				max = a[row][col];
				ridx = row;
			}
		}
		return ridx;
	}
	/**
	 * calculate peak in a column only when it is needed.
	 * start with middle column, find its peak.
	 * check which side of the column to go to and then calculate max there.
	 * @param a
	 * @return
	 */
	public static int peak2D_1(int[][] a, int cols)
	{
		int rows = a.length;
		 
		int mid = cols/2;
		int ridx = findMaxIdx(a,mid);
		
		if ((mid == 0 || a[ridx][mid] >= a[ridx][mid-1]) && (mid == a[0].length-1 ||a[ridx][mid] >= a[ridx][mid+1]))
			return a[ridx][mid];
		
		if (a[ridx][mid] < a[ridx-1][mid] && a[ridx+1][mid] <= a[ridx-1][mid])
			return peak2D_1(a, mid-1); 
		return peak2D_1(a, a[0].length-mid-1);
			
	}
	public static int bitonicPeak(int[] a, int lo, int hi)
	{
		if (lo == hi)
			return a[lo];
		int mid = lo + (hi-lo)/2;
		if (a[mid] < a[mid+1])
			return bitonicPeak(a, mid+1, hi);
		else if (a[mid] < a[mid-1])
			return bitonicPeak(a, lo, mid-1);
		return a[mid];
	}
	public static void main(String[] args) 
	{
		int[] bitonic_array = {10,20,30,50, 45, 35, 15, 5};
		int[] a = {4,8,5,25,20,18,13,4,5,6,10};
		System.out.println(peak1D(a,0,a.length-1));
		
		int[][] m = { {9,3,5,2,4,9,8 },
				      {7,2,5,1,4,0,3 },
				      {9,8,9,3,2,4,8 },
				      {7,6,3,1,3,2,3 },
				      {9,0,6,0,4,6,4},
				      {8,9,8,0,5,3,0},
				      {2,1,2,1,1,1,1}
				     };
		System.out.println(peak2D(m));
		System.out.println(peak2D_1(m,m[0].length));
	}
}
