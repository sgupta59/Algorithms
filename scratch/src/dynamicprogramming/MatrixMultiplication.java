package dynamicprogramming;

public class MatrixMultiplication {

	public static void multiplyMatrix(int[][] m1, int[][] m2, int[][] m3)
	{
		
	}
	
	public static int[][] createMatrix(int rows, int columns)
	{
		int[][]m = new int[rows][];
		for (int idx = 0; idx < rows; ++idx)
		{
			m[idx] = new int[columns];
		}
		for (int idx = 0; idx < rows; ++idx)
		{
			for (int jdx = 0; jdx < columns; ++jdx)
			{
				m[idx][jdx] = -1;
			}
		}
		return m;
	}
	public static int[][] multiply(int[][] m1, int[][] m2)
	{
		int nrows1 = m1.length;
		int ncols1 = m1[0].length;
		int nrows2 = m2.length;
		int ncols2 = m2[0].length;
		if (ncols1 != nrows2)
			return null;
		int[][] m3 = createMatrix(nrows1, ncols2);
		// for each row of m1
		for (int idx = 0; idx < nrows1; ++idx)
		{
			// for each column of m2
			for (int jdx = 0; jdx < ncols2; ++jdx)
			{
				int sum = 0;
				// for each column of the matrix.
				for (int kdx = 0; kdx < ncols1; ++kdx)
				{
					sum += m1[idx][kdx]*m2[kdx][jdx];
				}
				m3[idx][jdx] = sum;
			}
		}
		return m3;
	}
	public static void printMatrix(int[][] m)
	{
		for (int idx = 0; idx < m.length; ++idx)
		{
			for (int jdx = 0; jdx < m[idx].length; ++jdx)
			{
				System.out.print(m[idx][jdx] + " ");
			}
			System.out.println("\n");
		}
	}
	public static void testmatrix()
	{
		int rows = 3, columns = 3;
		int[][] m1 =  createMatrix(3, 3);
		int[][] m2 = createMatrix(3,3);
		m1[0][0] = 1;m1[0][1] = 2;m1[0][2] = 3;
		m1[1][0] = 4;m1[1][1] = 5;m1[1][2] = 6;
		m1[2][0] = 7;m1[2][1] = 8;m1[2][2] =9;
		m2[0][0] = 9;m2[0][1] = 8;m2[0][2] = 7;
		m2[1][0] = 6;m2[1][1] = 5;m2[1][2] = 4;
		m2[2][0] = 3;m2[2][1] = 2;m2[2][2] =1;
		printMatrix(m1);
		System.out.print("--------------\n");
		printMatrix(m2);
		System.out.print("--------------\n");
		int [][]m3  = multiply(m1,m2);
		printMatrix(m3);
		System.out.print("--------------\n");
		System.out.println("");
	}
	public static void main(String[] args)
	{

		
	}
}
