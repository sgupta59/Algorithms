package divideandconquer;
/** http://www.keithschwarz.com/interesting/code/?dir=single-sell-profit */
/** http://www.geeksforgeeks.org/stock-buy-sell/ **/ 
/** http://www.geeksforgeeks.org/maximum-difference-between-two-elements/  */
public class SingleSellProfit1 {

	public static int findmin(int[] a, int lo, int hi )
	{
		int min = a[lo];
		for (int idx = lo; idx <=hi; ++idx)
		{
			if (a[idx] < min)
				min = a[idx];
		}
		return min;
	}
	public static int findmax(int[] a, int lo, int hi )
	{
		int max = a[lo];
		for (int idx = lo; idx <=hi; ++idx)
		{
			if (a[idx] > max)
				max = a[idx];
		}
		return max;
	}
	public static int profit1(int[] a, int lo, int hi)
	{
		if (hi-lo == 1)
		{
			return a[hi] > a[lo] ? (a[hi]-a[lo]) : (a[lo]-a[hi]);
		}
		/** In case the numbers in array a[lo] to a[hi] are odd hi - lo can be 0 */
		if (hi - lo < 1)
			return 0;
		int mid = lo + (hi-lo)/2;
		int left = profit1(a, lo, mid);
		int right = profit1(a, mid+1,hi);
		int middle = findmax(a, mid+1,hi) - findmin(a,lo,mid);
		if (left > right) return left > middle ? left : middle;
		return right > middle ? right : middle;
	}
	public static void main(String[] args)
	{
		int[] prices = {2, 7, 1, 8, 2, 8, 4, 5, 9, 0, 4, 5};
		int maxprofit = profit1(prices, 0, prices.length-1);
		System.out.println("Profit: " + maxprofit);
		
	}
}
