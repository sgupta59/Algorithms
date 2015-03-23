package dynamicprogramming;

public class ActivitySelection {

	public static void main(String[] args)
	{
		int startT[] = { 0, 1, 3, 0, 4, 3, 5, 6, 9};
		int endT[] = { 0, 4, 5, 6, 7, 8, 9, 10, 11};
		int p[] = new int[startT.length];
		p[0] = 0;
		int opt[] = new int[startT.length];
		for (int idx = 1; idx < startT.length; ++idx)
			if (startT[idx] >= endT[idx-1])
				p[idx] = idx-1;
		System.out.println(p);
	}
}
