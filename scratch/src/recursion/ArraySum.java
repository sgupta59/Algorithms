package recursion;

public class ArraySum {

	public static int arraysum(int[] a, int[] b, int i, int max, int diff,int[] res)
	{
		if (i == max)
			return 0;
		int carry = arraysum(a, b, i+1,max,diff,res);
		int a1, b1;
		if (a.length < max)
			a1 = i-diff <= 0 ? 0 : a[i-diff];
		else 
			a1 = a[i];
		if (b.length < max)
			b1 = i-diff <= 0 ? 0 : b[i-diff];
		else
			b1 = b[i];
		int sum = a1 + b1  + carry;
		res[i+1] = sum%10;
		carry = sum/10;
		return carry;
	}
	public static void main(String[] args)
	{
		int[] a = {5, 6, 7, 8};
		int[] b = { 6,7,8}; 
		int[] res = new int[a.length+1];
		int max = a.length;
		int diff = a.length-b.length;
		int carry = arraysum(a, b, 0, max,diff, res);
		if (carry > 0)
			System.out.println("abc");
	}
}
