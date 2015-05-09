package RangeMinimumQueries;

import utils.Print;

public class RMQ {

	/**
	 * Dynamic programming based solution as to find minimum of [i,j], we already know [i,j-1]
	 * O(N^2) solution
	 * @param A
	 * @return
	 */
	public static int[][] buildRMQ_dp(int[] A)
	{
		int[][] M = new int[A.length][A.length];
		// range of i to i has one element so it is the minimum item index.
		for (int i = 0; i < A.length; ++i)
		{
			M[i][i] = i;
		}
		for (int i = 0; i < A.length; ++i)
		{
			for (int j = i+1; j < A.length; ++j)
			{
				// each of M[i][j] is minimum element in range [i,j] but [i, j-1] is already known.
				if (A[M[i][j-1]] < A[j])
					M[i][j] = M[i][j-1];
				else
					M[i][j] = j;
			}
		}
		return M;
	}
	 
	/**
	 * O(N^3) solution using brute force.
	 * @param A
	 * @return
	 */
	public static int[][] buildRMQ_simple(int[] A)
	{
		int[][] M = new int[A.length][A.length];
		// for each item in the table, find the minimum in a range.
		for (int i = 0; i < A.length; ++i)
		{
			for (int j = i; j < A.length; ++j)
			{
				// find the minimum item in range [i,j]
				int mini = i;
				for (int k = i; k <= j; ++k)
				{
					if (A[k] < A[mini])
						mini = k;
				}
				M[i][j] = mini;
			}
		}
		 
		return M;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {16,18,33,98};
		// Gives a solution <P(n), Q(n)> where
		// P(n) = preprocessing time
		// Q(n) = query time.
		// simplest solution is <O(1), O(n)> i.e. always scan.
		// dp table <O(N^2), O(1)> as table is built in O(N^2) space and time complexity.
		int[][] M = buildRMQ_simple(A);
		M = buildRMQ_dp(A);
		Print.matrix(M);
	}

}
