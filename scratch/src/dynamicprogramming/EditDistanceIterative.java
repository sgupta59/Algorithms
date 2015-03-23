package dynamicprogramming;

public class EditDistanceIterative {

	public static int replaceOrMatch(char c1, char c2)
	{
		if (c1 == c2)
			return 0;
		return 1;
	}
	public static int delete(char c1)
	{
		return 1;
	}
	
	public static int insert(char c1)
	{
		return 1;
	}
	public static void main(String[] args)
	{
		String s1 = "SPAKE";
		String s2 = "PARK";
		int len1 = s1.length()+1;
		int len2 = s2.length()+1;
		int[][] DP = new int[len1][len2];
		for (int idx = 0; idx < len1; ++idx)
		{
			for (int jdx = 0; jdx < len2; ++jdx)
			{
				DP[idx][jdx] = 0;
			}
		}
		for (int idx = 1; idx < len1; ++idx)
			DP[idx][0] = idx;
		for (int jdx = 1; jdx < len2; ++jdx)
			DP[0][jdx] = jdx;
		System.out.println("abc");
		for (int idx = 1; idx <len1; ++idx)
		{
			for (int jdx = 1; jdx < len2; ++jdx)
			{
				int match = DP[idx-1][jdx-1]+replaceOrMatch(s1.charAt(idx-1),s2.charAt(jdx-1));
				int delete = DP[idx-1][jdx] + delete(s1.charAt(idx-1));
				int insert = DP[idx][jdx-1] +insert(s2.charAt(jdx-1));
				int min = Integer.MAX_VALUE;
				if (match <= min)
					min = match;
				if (delete < min)
					min = delete;
				if (insert < min)
					min = insert;
				DP[idx][jdx] = min;
			}
		}
		
		int idx = len1-1;int jdx = len2-1;
		while (len1 >= 0 && len2 >= 0)
		{
			if (DP[idx][jdx] == 
					DP[idx-1][jdx-1]+replaceOrMatch(s1.charAt(idx-1), s2.charAt(jdx-1)))
			{
				idx = idx-1;
				jdx = jdx-1;
				System.out.println("Replace");
				continue;
			}
			if (DP[idx][jdx] ==  DP[idx-1][jdx] + delete(s1.charAt(idx-1)))
			{
				idx = idx-1;
				System.out.println("Delete");
				continue;
			}
			if (DP[idx][jdx] ==  DP[idx][jdx-1] +insert(s2.charAt(jdx-1)))
			{
				jdx = jdx-1;
				System.out.println("Insert");
				continue;
			}
		}
		System.out.println("");
	}
}
