package codility.lesson3;
/**
 * Created by yaodh on 2014/12/6.
 * 1. CountDiv
 * Compute number of integers divisible by k in range [a..b].
 * Task description
 * Write a function:
 * class Solution { public int solution(int A, int B, int K); }
 * that, given three integers A, B and K, returns the number of integers within the range [A..B] that are 
 * divisible by K, i.e.:
 * { i : A ≤ i ≤ B, i mod K = 0 }
 * For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
 * Assume that:
 * A and B are integers within the range [0..2,000,000,000];
 * K is an integer within the range [1..2,000,000,000];
 * A ≤ B.
 * Complexity:
 * expected worst-case time complexity is O(1);
 * expected worst-case space complexity is O(1).
 */
public class CountDiv {
	public static int solution(int A, int B, int K) {
        
        if (A%K == 0)
        {
        	return (B-A)/K + 1;
        }
        
        return (B/K - ((A/K)+1))+1;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(3,33,2));
		System.out.println(solution(3,33,3));
		System.out.println(solution(3,33,11));
		System.out.println(solution(3,33,23));
		
	}

}
