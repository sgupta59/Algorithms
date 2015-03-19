package divideandconquer;

public class CountInversions {

	public static int merge(int [] vals, int[] aux, int lo, int mid, int hi)
	{
		int count = 0;
		for (int idx = lo; idx <= hi; ++idx)
			aux[idx] = vals[idx];
		int i = lo;
		int j = mid+1;
		int k = lo;
		for (int idx = lo; idx <= hi; ++idx)
		{
			if (i > mid) vals[k++] = aux[j++];
			else if (j > hi) vals[k++] = aux[i++];
			else if (aux[i] <= aux[j]) vals[k++] = aux[i++];
			else {
				vals[k++] = aux[j++];
				count = count + mid - i+1;
			}
		}
		return count;
	}
	public static int mergeAndCount(int[] vals, int[] aux, int start, int end)
	{
		if (end <= start)
			return 0;
		int mid = start + (end-start)/2;
		int count = mergeAndCount(vals,aux,start,mid);
		count += mergeAndCount(vals,aux,mid+1,end);
		return count +  merge(vals,aux,start,mid,end);
	}
	public static void main(String[] args)
	{
		int [] vals = {4, 1, 2, 9,3, 1 };
		//int [] vals = { 2 ,4, 1, 3 ,5};
		int [] helper = new int[vals.length];
		int count = mergeAndCount(vals,helper,0,vals.length-1);
		System.out.println("abc");
	}
}
