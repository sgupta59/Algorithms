package divideandconquer;

public class MedianSortedArrays {

	public static int max(int a, int b)
	{
		return a > b ? a : b;
	}
	
	public static int min(int a, int b)
	{
		return a < b ? a : b;
	}
	
	public static int ceil2(int a)
	{
		double d = ((double)a)/2;
		return (int)Math.ceil(d);
	}
	
	public static int floor2(int a)
	{
		double d = ((double)a)/2;
		return (int)Math.floor(d);
	}
	public static int median_search(int[] a, int[] b, int left, int right, int n)
	{
		if (left > right)
		{
			int l1 = 0;
			int l2 = ceil2(n)-a.length-1;
			int l = max(l1, l2);
			int r1 = b.length-1;
			int r2 = ceil2(n)-1;
			int r = min(r1, r2);
			l = 0; r = b.length-1;
			return median_search(b, a, l, r,n);
		}
		//int i = floor2(left+right);
		//int j = floor2(n)-i-1;//ceil2(n-1)-i-1;
		int i = (left+right)/2;
		int j = (n/2)-i-1;
		if ((j <= 0 || a[i] > b[j] ) && (j == b.length-1 || a[i] <= b[j+1]))
		{
			return a[i];
		}
		else if ((j == 0 || a[i] > b[j]) && (j != b.length-1 && a[i] > b[j+1]))
		{
			return median_search(a, b, left, i-1, n);
		}
		return median_search(a, b, i+1, right, n);
	}
	public static void main(String[] args)
	{
		int ar2[] = {1, 12, 15, 26, 38};
		int ar1[] = { 39};
	    //int ar1[] = {2, 13, 17, 30, 45,48};
	    int n = ar1.length + ar2.length;
	    int l1 = 0;
	    int l2 = ceil2(n)-ar2.length-1;
	    int l = max(l1, l2);
	    int r1 = ar1.length-1;
	    int r2 =  ceil2(n);
	    int r = min(r1, r2);
	    int median = median_search(ar1, ar2, l, r, n);
	    System.out.println("abc");
	}
}
