package utils;

public class Print {

	public static void matrix(int[][] a)
	{
		for (int idx = 0; idx < a.length; ++idx)
		{
			for (int j = 0; j < a[idx].length; ++j)
			{
				System.out.print(" " + a[idx][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}	
}
