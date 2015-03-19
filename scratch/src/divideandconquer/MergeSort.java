package divideandconquer;

public class MergeSort {

	public static void merge(int[] vals, int[] helper, int lo, int mid, int hi)
	{
		for (int idx = lo; idx <= hi; ++idx)
			helper[idx] = vals[idx];
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; ++k)
		{
			if (i > mid) vals[k] = helper[j++];
			else if ( j > hi) vals[k] = helper[i++];
			else if (helper[j] < helper[i]) vals[k] = helper[j++];
			else vals[k] = helper[i++];
		}
	}
	public static void mergeWhile(int[] vals, int[] helper, int lo, int mid, int hi)
	{
		for (int idx = 0; idx <= hi; ++idx)
			helper[idx] = vals[idx];
		int i = lo;
		int j = mid+1;
		int k = i;
		while (i <= mid && j <= hi)
		{
			if (helper[i] <= helper[j])
			{
				vals[k] = helper[i];
				++i;
			}
			else
			{
				vals[k] = helper[j];
				++j;
			}
			++k;
		}
		while (i <= mid)
		{
			vals[k++] = helper[i++];
		}
	}
	public static void mergesort(int[] vals,int[] helper, int start, int end)
	{
		if (end <= start)
			return;
		int mid = start + (end-start)/2;
		mergesort(vals,helper,start,mid);
		mergesort(vals,helper,mid+1,end);
		//merge(vals,helper,start,mid,end);
		mergeWhile(vals,helper,start,mid,end);
	}
	public static void main(String[] args)
	{
		int [] vals = { 4, 1, 2, 9,3, 1 };
		int [] helper = new int[vals.length];
		mergesort(vals,helper, 0, vals.length-1);
		System.out.println("test");
	}
}
