package arrays;

public class TrappingWater {
	private static int getVolume(int[] A, boolean isForward, int end, int[] boundary) {  
		   // skip zeros  
		   int cur = 0;  
		   if (!isForward) cur = A.length - 1;  
		   while (cur != end && A[cur] == 0) {  
		     cur += (isForward ? 1 : -1);  
		   }  
		   
		   // calculate trapped volume  
		   int vol = 0, newEnd = cur;  
		   for (int i=cur, tempSum=0; i!=end; i+=(isForward ? 1 : -1)) {  
		     if (A[i] >= A[newEnd]) {  
		       vol += tempSum;  
		       // reset  
		       tempSum = 0;  
		       newEnd = i;  
		     } else {  
		       tempSum += (A[newEnd] - A[i]);  
		     }  
		   }  
		   boundary[0] = newEnd;  
		   return vol;  
		 }  
		   
		 public static int trap(int[] A) {  
		   int[] boundary = new int[1];  
		   int vol = getVolume(A, true, A.length, boundary);  
		   vol += getVolume(A, false, boundary[0]-1, boundary);  
		   
		   return vol;  
		 }  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {0,1,0,2,1,0,1,3,2,1,2,1};
		int sum = trap(A);
		System.out.println(sum);
	}

}
