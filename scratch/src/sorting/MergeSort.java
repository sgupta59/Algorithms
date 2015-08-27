package sorting;

import utils.Print;

public class MergeSort {

	public static void merge(int[] arr, int[] aux, int lo, int mid, int hi)
	{
		for (int k = lo; k <= hi; ++k)
			aux[k] = arr[k];
		int i = lo;
		int j = mid+1;
		for (int k = lo; k <= hi; ++k)
		{
			if (i > mid)     			arr[k] = aux[j++];
			else if (j > hi) 			arr[k] = aux[i++];
			else if (aux[i] < aux[j]) 	arr[k] = aux[i++];
			else 						arr[k] = aux[j++];
		}
	}
	public static void mergesort_topdown(int[] a, int[] aux, int lo, int hi)
	{
		if (lo >= hi)
			return;
		int mid = lo + (hi-lo)/2;
		mergesort_topdown(a, aux, lo, mid);
		mergesort_topdown(a,aux,mid+1,hi);
		merge(a, aux, lo, mid, hi);
	}
	
	public static void mergesort_bu(int[] a, int[] aux)
	{
		int N = a.length;
		for (int width = 1; width < N; width = 2*width) {
			for (int k = 0; k < N-width; k = k + 2*width) {
				int lo = k;
				int mid = k+width-1;
				int hi = Math.min(k+2*width-1,N-1);
				merge(a,aux,lo,mid,hi);
			}
		}
	}
	public static void main(String[] args)
	{
		int[] arr = {1, 50, 25, 33, 2 , 20, 45, 20, 60, 3};
		int[] aux = new int[arr.length];
		Print.array("arr", arr);
		mergesort_bu(arr,aux);
		aux = new int[arr.length];
		mergesort_topdown(arr, aux, 0, arr.length-1);
		System.out.println(arr);
	}
}
