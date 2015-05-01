package codility.chapter2;

import java.util.BitSet;

/**
 * Created by yaodh on 2014/12/5.
 * MissingInteger
 * Find the minimal positive integer not occurring in a given sequence.
 * Task description
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.
 * For example, given:
 * A[0] = 1
 * A[1] = 3
 * A[2] = 6
 * A[3] = 4
 * A[4] = 1
 * A[5] = 2
 * the function should return 5.
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class MissingInteger {

	public static int solution1(int[] A) {
    int n = A.length;
    BitSet hash = new BitSet(n + 1);
    for (int i = 0; i < n; i++) {
        if (A[i] > 0 && A[i] <= n) {
            hash.set(A[i]);
        }
    }
    for (int i = 1; i <= n; i++) {
        if (!hash.get(i)) return i;
    }
    return -1;
    } 
	public static int solution2(int[] A)
	{
		// do a counting sort.
		for (int i = 0; i < A.length; ++i)
		{
			if (A[i] > 0 )
			{
				swaps(A, i, A[i]-1);
			}
		}
		for (int i = 0; i < A.length; ++i)
			if (A[i] != i+1)
				return i+1;
		return -1;
	}
	
	public static void swaps(int[] A, int x, int y)
	{
		int tmp = A[x];
		A[x] = A[y];
		A[y] = tmp;
	}
	 

 
public static void main(String[] args) {
	System.out.println(solution1(new int[]{1,3,6,4,1,2}));
	System.out.println(solution2(new int[]{1,3,6,4,1,2}));
}

}
