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
	private static void quicksort_1(int[] numbers, int low, int high) {
	    int i = low, j = high;
	    // Get the pivot element from the middle of the list
	    int pivot = numbers[low + (high-low)/2];
	    int v = low + (high-low)/2;
	    // Divide into two lists
	    while (i <= j) {
	      // If the current value from the left list is smaller then the pivot
	      // element then get the next element from the left list
	      while (numbers[i] < pivot) {
	        i++;
	      }
	      // If the current value from the right list is larger then the pivot
	      // element then get the next element from the right list
	      while (numbers[j] > pivot) {
	        j--;
	      }

	      // If we have found a values in the left list which is larger then
	      // the pivot element and if we have found a value in the right list
	      // which is smaller then the pivot element then we exchange the
	      // values.
	      // As we are done we can increase i and j
	      if (i <= j) {
	    	  int tmp = numbers[j];
	    	  numbers[j] = numbers[i];
	    	  numbers[i] = tmp;
	        i++;
	        j--;
	      }
	    }
	    // Recursion
	    if (low < j)
	    	quicksort_1(numbers , low, j);
	    if (i < high)
	    	quicksort_1(numbers, i, high);
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Get a random generated array
		int[] a = getArray();
		int[] b = {5,3,1,6,2,4,9};
		quicksort_2(b,0, b.length-1);
		quicksort(a);
		printArray(a);
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
