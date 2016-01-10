package sorting;

public class QuickSort {


	public static void quicksort(int[] a)
	{
		int lo = 0; int hi = a.length-1;
		quicksort_doit(a, lo, hi);
		return;
	}

	public static void quicksort_doit(int[] a, int lo, int hi)
	{
		if (lo < hi)
		{
			int mid = lo + (hi-lo)/2;
			int p = a[mid];
			int i = lo;
			int j = hi;
			while (i <= j)
			{
				while (a[i] < a[mid])++i;
				while (a[j] > a[mid])--j;
				if (i <= j)
				{
					int tmp = a[j];
					a[j] = a[i];
					a[i] = tmp;
					--j;++i;
				}
			}
			quicksort_doit(a, lo, mid-1);
			quicksort_doit(a, mid+1, hi);
		}
	}


	public static int partition(int[] a, int lo, int hi)
	{
		int i = lo;
		int j = hi+1;
		int p = a[lo];
		while (true) {
			while (a[++i] < p) {
				if (i > hi) break;
			}
			while (a[--j] > p) {
				if (j < lo) break;
			}
			if (i >= j)
				break;
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
		int tmp = a[lo];
		a[lo] = a[j];
		a[j] = tmp;
		return j;
	}
	public static void quicksort_2(int[] a, int lo, int hi)
	{
		if (lo >= hi)
			return;
		int p = partition(a, lo, hi);
		quicksort_2(a, lo, p-1);
		quicksort_2(a, p+1, hi);
	}

	/**
	 *  Selection is 0 based, i.e. find the 0th item, 1st item etc.
	 */
	public static int select(int[] a, int lo, int hi, int idx)
	{
	    if (lo == hi)
	        return a[lo];
	    int p = partition(a, lo, hi);
	    if (p == idx)
	        return a[p];
	    if (idx < p)
	        return select(a, lo, p-1,idx);
	    return select(a, p+1, hi, idx);
	}
	public static int select_clrs(int[] a, int lo, int hi, int idx)
	{
	    if (lo == hi)
	        return a[lo];
	    int p = partition(a, lo, hi);
	    int k = (p-lo)+1;
	    if (k == idx) return a[p];
	    if (idx < k) return select_clrs(a, lo, p-1, idx);
	    return select_clrs(a, p+1, hi, idx-k);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Get a random generated array
		int[] a = getArray();
		int[] b = {5,3,1,6,2,4,9};
		int[] c = new int[b.length];
		System.arraycopy(b, 0, c, 0, b.length);
		//for (int i = 0; i < b.length; ++i)
		//{
		//    System.arraycopy(b, 0, c, 0, b.length);
		//    System.out.println(i + "'th item: "+ select(c,0,c.length-1,i));
		//}
		for (int i = 0; i < b.length; ++i)
        {
            System.arraycopy(b, 0, c, 0, b.length);
            System.out.println(i+1 + "'th item: "+ select_clrs(c,0,c.length-1,i+1));
        }
		quicksort_2(b,0, b.length-1);
		quicksort(a);
		printArray(a);
	}

	public static int partition_3(int[] a, int lo, int hi)
	{
	    int i = lo;
	    int j = hi+1;
	    int p = a[lo];
	    while (true)
	    {
	        while (i > hi && a[i] < p)
	            ++i;
	        while (j < lo && a[j] > p)
	            --j;
	        if (i >= j)
	            break;
	        int tmp = a[j];
	        a[j] = a[i];
	        a[i] = tmp;
	    }
	    int tmp = a[j];
	    a[j] = a[lo];
	    a[lo] = tmp;
	    return j;
	}

	public static void printArray(int[] a){
		for(int i : a){
			System.out.print(i+" ");
		}
	}

	public static int[] getArray(){
		int size=10;
		int []array = new int[size];
		int item = 0;
		for(int i=0;i<size;i++){
			item = (int)(Math.random()*100);
			array[i] = item;
		}
		return array;
	}
}
