package dynamicprogramming;

import java.util.Vector;
/** 
 * https://courses.engr.illinois.edu/cs473/fa2011/lec/08_notes.pdf  
 * http://www.8bitavenue.com/2011/11/dynamic-programming-longest-increasing-sub-sequence/
 * @author kg
 *
 */
public class LongestIncreasingSubsequence {
	static int ncount1 = 0;
	static int ncount2 = 0;
	public static int lis_r(int[] arr,int n,  int[] max_ref)
	{
		if (n == 1)
			return 1;
		int res = 0;
		// max length with arr[n] part of the solution.
		int max_ending_here = 1; 
		// max_ref can be the max length without a[n] as part of solution.
		for (int i = 1; i < n; ++i)
		{
			res = lis_r(arr, i, max_ref);
			if (arr[i-1] < arr[n-1] && res+1 > max_ending_here)
				max_ending_here = res+1;
		}
		if (max_ref[0] < max_ending_here)
			max_ref[0] = max_ending_here;
		return max_ending_here;
	}
	public static int lis(int[] arr)
	{
		int[] max = {1};
		Vector<Integer> path = new Vector<Integer>();
		int[] path1 = new int[arr.length+1];
		for (int idx = 0; idx < path1.length; ++idx)
			path1[idx] = -1;
		int[] vals = LIS_ending_memoized(arr, arr.length);
		int c = LIS_ending(arr, arr.length);
		int b = LIS_smaller(arr, arr.length, Integer.MAX_VALUE);
		int a = LIS_take1(arr, max, arr.length,path);
		lis_r(arr,arr.length, max);
		return max[0];
	}
	public static void printArray(int[] arr, int n)
	{
		for (int idx = 0; idx < n; ++idx)
		{
			System.out.print(" " + arr[idx]);
		}
		System.out.println("");
	}
	public static void printMatrix(int[][] a)
	{
		for (int idx = 0; idx < a.length; ++idx)
		{
			for (int j = 0; j < a[idx].length; ++j)
			{
				System.out.print(" " + a[idx][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}
	/** Take 1 from 08_handout.pdf */
	public static int LIS_take1(int[] arr, int[] max, int n,Vector<Integer> path)
	{
		
		++ncount1;
		if (n <= 0)
			return 0;
		printArray(arr, n);
		// find LIS for n-1 elements 
		int m = LIS_take1(arr, max, n-1, path);
		Vector<Integer> vec = new Vector<Integer>();
		for (int idx = 0; idx < n-1; ++idx)
		{
			if (arr[idx] < arr[n-1])
				vec.add(arr[idx]);
		}
		int[] B = new int[vec.size()];
		// collect all elements that are less than A[n]
		for (int idx = 0; idx < B.length; ++idx)
			B[idx] = vec.get(idx);
		// find the lis for the B vector, 1+LIS(b) gives LIS(A[1...n))
		int m1 = LIS_take1(arr, max, B.length,path) + 1;
		if (m1 > m)
		{
			m = m1;
		}
		return m;
	}
	
	public static int LIS_smaller(int[] arr, int n, int x)
	{
		++ncount2;
		if (n <= 0)
			return 0;
		int m = LIS_smaller(arr, n-1, x);
		int m1 = 0;
		if (arr[n-1] < x)
		{
			m1 = LIS_smaller(arr, n-1, arr[n-1])+1;
			if (m1 > m)
				m = m1;
		}
		return m;
	}
	public static int LIS_smaller1(int[] a, int N)
	{
		int[] A = new int[a.length+1];
		for (int idx =0; idx < a.length; ++idx)
			A[idx] = a[idx];
		A[A.length-1] = Integer.MAX_VALUE;
		int[][] L = new int[N+2][N+2];
		for (int j = 0; j < N+1; ++j)
			L[0][j] = 0;
		for (int i = 1; i <= N+1; ++i)
		{
			for (int j = i; j <= N+1; ++j)
			{
				L[i][j] = L[i-1][j];
				if (A[i] < A[j])
				{
					L[i][j] = Math.max(L[i][j], 1+L[i-1][i]);
				}
				printMatrix(L);
			}
		}
		printMatrix(L);
 		return L[N][N+1];
	}
	
	/** https://courses.engr.illinois.edu/cs473/fa2011/lec/08_notes.pdf */ 
	public static int LIS_ending(int[] A, int n)
	{
		if (n <= 0)
			return 0;
		
		int m = 1;
		for (int idx = 0; idx < n-1; ++idx)
		{
			
			if (A[idx] < A[n-1])
			{
				int m1 = LIS_ending(A, idx+1) + 1;
				if (m1 > m)
				{
					m = m1;

				}
			}
		}
		return m;
	}
	/** https://courses.engr.illinois.edu/cs473/fa2011/lec/08_notes.pdf */
	public static int[] LIS_ending_memoized(int[] A, int n)
	{
		int[] L = new int[A.length];
		int[][] matrix = new int[A.length+1][A.length+1];
		
		for (int idx = 0; idx < n; ++idx)
		{
			L[idx] = 1;
			
			for (int j = 0; j < idx; ++j)
			{
				matrix[idx+1][j+1] = L[idx];
				if (A[j] < A[idx])
				{
					L[idx] = Math.max(L[idx], L[j]+1);
				}
			}
		}
		return L;
	}
	
	/** http://www.8bitavenue.com/2011/11/dynamic-programming-longest-increasing-sub-sequence/ */
	/**
	 * Maximum subsequence ending at value j
	 * L(j) = max(1+L(i))
	 * where i = [0,j-1] or 0 <= i < j
	 * and A[j] > A[i]
	 * 
	 * @param A
	 */
	public static void LIS_memoized1(int[] A)
	{
		// create an array to hold the length of the longest increasing subsequence
		int[] L = new int[A.length];
		// Initially all subsequences are of length 1
		for (int idx = 0; idx < L.length; ++idx)
			L[idx] = 1;
		// create an array to store the parent pointers. 
		// b[i] points to the previous value of the max subsequence, e.g.
		// b[j] points to index i in the array that has the max subsequence without A[j]
		int[] b = new int[A.length];
		for (int idx = 0; idx < b.length; ++idx)
			b[idx] = -1;
		// maximum subsequence.
		int maxj = 0;
		// this variable points to the index where the maximum subsequence ends
		int end = 1;
		// max value of i < j that has the maximum value.
		int maxi = 0;
		int[][] mat = new int[A.length][A.length];
		for (int j = 1; j < A.length; ++j)
		{
			// for now the pointer in the j aray where the maximum subsequence ends is j
			b[j] = j;
			// for all i's up to j
			for (int i = 0; i < j; ++i)
			{
				if (A[i] < A[j] && 1+L[i] > maxi)
				{
					maxi = L[i] + 1;
					mat[i][j] = maxi;
					b[j] = i;
				}
			}
			L[j]  = maxi;
			maxi = 0;
			if (L[j] > maxj)
			{
				maxj = L[j];
				end = j;
			}
		}
		for (int j = end; j >= 0; j=b[j])
		{
			System.out.print(" " + A[j]);
		}
		System.out.println("Max subsequence length: " + maxj);
		
	}
	public static int LIS_memoized2(int[] arr)
	{
		int[] L = new int[arr.length];
		int[][] m = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; ++i)
		{
			L[i] = 1;
			for (int j = 0; j < i; ++j)
			{
				if (arr[j] < arr[i])
				{
					L[i] = Math.max(L[i], 1+L[j]);
				}
				m[i][j] = L[i];
				printMatrix(m);
			}
		}
		
		return 0;
	}
	public static void main(String[] args)
	{	
		int[] arr2 = { 9, 11, 2, 13, 7, 15};
		LIS_memoized2(arr2);
		LIS_memoized1(arr2);
		int[] arr = {0, 9, 11, 2, 13, 7, 15};
		LIS_smaller1(arr,6);

		int arr1[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int max = lis(arr1);
		System.out.println("");
	}
}
