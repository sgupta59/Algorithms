package divideandconquer;

public class MedianDivideConquer {

	public static int median(int arr[], int start, int end)
	{
		int n =  (end-start)+1;
		if (n % 2 == 0)
		{
			return (arr[n/2] + arr[n/2-1])/2;
		}
		return arr[start+n/2];
	}
	public static int max(int x, int y)
	{
		return x > y ? x : y;
	}
	public static int min(int x, int y)
	{
		return x < y ? x : y;
	}
	public static int getMedian(int[] a,int ai, int aj, int[] b, int bi, int bj)
	{
		int m1 = 0; int m2 = 0;
		int n = (aj-ai)+1;

		if (n == 1)
			return (a[ai]+b[bi])/2;
		if (n == 2)
		{
			int a1 = a[ai];
			int b1 = b[bi];
			int a2 = a[aj];
			int b2 = b[bj];
			return (max(a[ai],b[bi])+min(a[aj],b[bj]))/2;
		}
		m1 = median(a, ai,aj);
		m2 = median(b, bi, bj);
		if (m1 == m2)
			return m1;
		if (m1 < m2)
		{
			if (n %2 == 0)
			{
				int as = n/2+ai;
				int ae = aj;
				int bs = bi;
				int be = bi+n/2;
				return getMedian(a, as, aj, b, bi, be-1);
			}
			else 
			{
				int as = n/2+ai;
				int ae = aj;
				int bs = bi;
				int be = bi+n/2;
				return getMedian(a, as, ae, b, bi, bi+n/2);
			}
		}
		else 
		{
			if (n%2 == 0)
			{
				int as = ai;
				int ae = ai+n/2-1;
				int bs = bi+n/2;
				int be = bj;
				return getMedian(a, ai,ai+n/2-1,b,bi+n/2,bj);
			}
			else
			{
				int as = ai;
				int ae = ai+n/2;
				int bs = bi+n/2;
				int be = bj;
				return getMedian(a,ai,ai+n/2, b, bs,bj);
			}
		}
	}
	public static void main(String[] args)
	{
		//int ar1[] = {1,2,3,5,8};
	    //int ar2[] = {4,6,9,10,15};
	    //int ar2[] = {1, 12, 15, 26,27};
	    //int ar1[] = {40,45,55,56,58};
	    int ar1[] = {1, 12, 15, 26};
	    int ar2[] = {40,45,55,56};
	    int median = getMedian(ar1, 0, ar1.length-1, ar2, 0, ar2.length-1);
	    System.out.println("Median: " + median);
	}
}
