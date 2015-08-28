package sorting;

public class Inversions {

	private static int merge(int[] a, int[] aux, int lo, int mid, int hi)
	{
		for (int k = lo; k <= hi; ++k)
			aux[k] = a[k];
		int i = lo;
		int j = mid+1;
		int inversions = 0;
		for (int k = lo; k <= hi; ++k)
		{
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (a[j] < a[i]) {
				a[k] = aux[j++];
				inversions = inversions + (mid-i+1);
			} else {
				a[k] = aux[i++];
			}
		}
		return inversions;
	}
	public static int inversions(int[] a, int[] aux, int lo, int hi)
	{
		if (hi <= lo)
			return 0;
		int mid = lo + (hi-lo)/2;
		int invleft = inversions(a, aux, lo, mid);
		int invright = inversions(a, aux, mid+1, hi);
		int inv_combined = merge(a, aux, lo, mid, hi);
		int inversions =  invleft + invright + inv_combined;
		return inversions;
	}
	private static int brute(int[] a, int lo, int hi) {
        int inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (a[j] < a[i]) inversions++;
        return inversions;
    }
	public static int kendall_tau(int[] a, int[] b)
	{
		int[] ainv = new int[a.length];
		for (int i = 0; i < a.length; ++i)
			ainv[a[i]] = i;
		int[] binv = new int[a.length];
		for (int i = 0; i < b.length; ++i)
			binv[i] = ainv[b[i]];
		int[] aux = new int[a.length];
		int inversions =  inversions(binv,aux,0,ainv.length-1);
		return inversions;
		
	}
	public static void main(String[] args)
	{
		int[] a = { 3,5,1,2,4};
		int[] a1 = {0,3, 1, 6, 2, 5, 4 };
		int[] a2 = {1, 0 ,3 ,6, 4, 2 ,5};
		int kd = kendall_tau(a1,a2);
		int[] aux = new int[a.length];
		int inversions = brute(a,0,a.length-1);
		inversions = inversions(a,aux, 0, a.length-1);
	}
}
