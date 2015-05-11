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
	public static void array(String name, int[] array)
	{
		System.out.print("[" + name + "]:");
		for (int i = 0; i < array.length; ++i)
			System.out.print(" " + array[i]);
		System.out.println();
	}
	public static void array(int[] path, int start, int end) 
	{
		for (int i = start; i <= end; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}
}
