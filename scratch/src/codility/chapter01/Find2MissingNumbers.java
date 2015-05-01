package codility.chapter01;

/**
 * Find 2 missing numbers in an array
 * @author kg
 *
 */
public class Find2MissingNumbers {

	public static int[] solution(int[] A)
	{
		int len = A.length+2;
		int sumLinear = (len)*(len+1)/2;
		int currentLinear = 0;
		for (int i = 0; i < A.length; ++i)
			currentLinear += A[i];
		
		int sumSquare = (len)*(len+1)*(2*len+1)/6;
		int currentSquare = 0;
		for (int i = 0; i < A.length; ++i)
			currentSquare += A[i]*A[i];
		int xplusy = sumLinear-currentLinear;
		int xsquareplusysquare = sumSquare-currentSquare;
		int twoxy = xplusy*xplusy-xsquareplusysquare;
		int xminusy = (int)Math.sqrt(xsquareplusysquare-twoxy);
		int x = (xplusy+xminusy)/2;
		int y = (xplusy-xminusy)/2;
		return new int[] {x,y};
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] missing = solution(new int[]{1,2,5,6});
		System.out.println("Missing Numbers: " + missing[0] + "," + missing[1]);
	}

}
