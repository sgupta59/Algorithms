package codility.chapter01;
/**
 * Created by yaodh on 2014/12/4.
 * PermMissingElem
 * Find the missing element in a given permutation.
 * Task description
 * A zero-indexed array A consisting of N different integers is given.
 * The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * Your goal is to find that missing element.
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a zero-indexed array A, returns the value of the missing element.
 * For example, given array A such that:
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * Assume that:
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class PermMissingElement {

	public static int solution(int[] A)
	{
		// If the number were not missing, there will be A.length+1 numbers. their sum is
		int sumExpected = (A.length+1)*(A.length+2)/2;
		int sum = 0; 
		// sum of the existing array
		for (int i = 0; i < A.length; ++i)
			sum += A[i];
		return sumExpected - sum;
	}
	
	public static int solution_1(int[] A)
	{
		int N = A.length;
		// put items in their right index or untill the item is larger than the length
		for (int i = 0; i < N; ++i)
		{
			if (A[i] == i+1)
				continue;
			while (A[i] != i+1 && A[i] <= N)
				swap(A, i, A[i]-1);
		}
		int missing = 0;
		for ( missing= 0;missing< N; ++missing)
		{
			if (A[missing] > N)
				return missing+1;
		}
		// corner case.
		return missing+1;
	}
	public static void swap(int[] A, int i, int j)
	{
		A[i] = A[i] ^ A[j];
		A[j] = A[i] ^ A[j];
		A[i] = A[i] ^ A[j];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {2,1,4,5};
		System.out.println(solution_1(A));
		System.out.println(solution(A));
	}

}