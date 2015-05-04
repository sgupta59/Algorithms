package codility.Lesson6;

import java.util.Arrays;

/**
 * Created by yaodh on 2014/12/6.
 * 1. EquiLeader
 * Find the index S such that the leaders of the sequences
 * A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] are the same.
 * Task description
 * A non-empty zero-indexed array A consisting of N integers is given.
 * The leader of this array is the value that occurs in more than half of the elements of A.
 * An equi leader is an index S such that 0 <= S < N - 1 and two sequences
 * A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] have leaders of the same value.
 * For example, given array A such that:
 * A[0] = 4
 * A[1] = 3
 * A[2] = 4
 * A[3] = 4
 * A[4] = 4
 * A[5] = 2
 * we can find two equi leaders:
 * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
 * 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 * The goal is to count the number of equi leaders. Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
 * For example, given:
 * A[0] = 4
 * A[1] = 3
 * A[2] = 4
 * A[3] = 4
 * A[4] = 4
 * A[5] = 2
 * the function should return 2, as explained above.
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [-1,000,000,000..1,000,000,000].
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class Equileader {

	/*public int solution1(int[] A)
	{
		// first find the dominator of A
		int dom = 0;
		int idx = 0;
		int[] sums = new int[A.length];
		for (int i = 0; i < A.length; ++i)
		{
			++sums[A[i]];
			if (idx == 0)
			{
				dom = A[i];
				++idx;
			} 
			else if (dom == A[i])
			{
				++idx;
			}
			else
			{
				--idx;
			}
		}
		int size = 0;
		for (int i = 0; i < A.length; ++i)
		{
			if (A[i] == dom)
				++size;
		}
		if (size < A.length/2)
			return -1;
		int count = 0;
		for (int i = 0; i < sums.length; ++i)
		{
			//if (sums[i] > sums[i-1] && A[i] != 
		}
	}*/
	public static  int solution1(int M, int[] A) {
	        int N = A.length;
	        int[] count = new int[M + 1];
	        for (int i = 0; i <= M; i++)
	            count[i] = 0;
	        int maxOccurence = 1;
	        int index = 0;
	        for (int i = 0; i < N; i++) {
	            if (count[A[i]] > 0) {
	                int tmp = count[A[i]];
	                if (tmp > maxOccurence) {
	                    maxOccurence = tmp;
	                    index = i;
	                }
	                count[A[i]] = tmp + 1;
	            } else {
	                count[A[i]] = 1;
	            }
	        }
	        return A[index];
	    }
	
	public static int test(int[] A, int[] B)
	{
		Arrays.sort(A);
		Arrays.sort(B);
		int min = -1;
		for (int i = 0; i < A.length; ++i)
		{
			for (int j = 0; j < A.length; ++j)
			{
				if (A[i] == B[j])
				{
					return A[i];
				}
				if (A[i] > B[j])
					continue;
				if (A[i] < B[j])
					break;
			}
		}
		return min;
	}
	
	public static int deviation(int[] A)
	{
		long sum = 0;
		for (int i = 0; i < A.length; ++i)
			sum += A[i];
		long avg = (sum)/A.length;
		int idx = 0;
		long maxdev = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; ++i)
		{
			long diff = Math.abs(A[i]-avg);
			if (diff > maxdev)
			{
				maxdev = diff;
				idx = i;
			}
		}
		return idx == -1 ? idx : A[idx];
	}
	public static int solution2(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
                int tmp = count[A[i]];
                if (tmp > maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                    count[A[i]] = tmp + 1;
                }
                
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] val1 = new int[200000];
		for (int i = 0; i < 200000; ++i)
		{
			val1[i] = i*100;
		}
		int s = solution2(10000,new int[]{3,4,5,1,1});
		int[] vals = new int[100000];
		long test1 = 0;
		for (int i = 0; i < 100000; ++i)
		{
			vals[i] = 2147483647;
			test1 += 2147483647;
		}
		if (test1 < 9223372036854775807l)
		{
			System.out.println("abc");
		}
		int t = deviation(vals);
		//int t = deviation(new int[]{2147483647, 2147483646, 2147483645, 2147483644});
		int k = test(new int[]{1,2,3,4,5,6,9,10,15},new int[] {8,11,14,20});
		int i = solution1(3, new int[]{1,1,1,1,1,1,1});
		int j= solution1(8, new int[]{1,2,3,4,5,6,7});
		System.out.println("");
	}

}
