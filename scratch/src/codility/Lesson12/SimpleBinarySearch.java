package codility.Lesson12;

public class SimpleBinarySearch {

	public static int solution(int[] A, int x)
	{
		int start = 0;
		int end = A.length-1;
		int result = -1;
		while (start <= end)
		{
			 
			int mid = (end-start)/2;
			if (A[mid] <= x )
			{
				start = mid+1;
				result = mid;
			}
			else
			{
				end = mid-1;
			}
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[]{1,2,3}, 3));
	}

}
