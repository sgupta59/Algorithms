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
		int r1 = m1.length;
		int c1 = m1[0].length;
		int r2 = m2.length;
		int c2 = m2[0].length;
		if (c1 != r2)
			return null;
		int[][] m3 = createMatrix(r1, c2);
		 
		// complexity is:
		// for each row of m1, for each column of m1 , multiply for each row of m2
		// (m,n)x(n,l) = 
        // n.(m.l) (row1 times col2) times col 1
		// for each row of m1
		for (int i = 0; i < r1; ++i)
		{
			// for each column of m2
			for (int j = 0; j < c2; ++j)
			{
				int sum = 0;
				// for each column of the m1.
				for (int k = 0; k < c1; ++k)
				{
					
					sum = sum + m1[i][k]*m2[k][j];
				}
				m3[i][j] = sum;
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
		int[][] m1 =  createMatrix(3, 4);
		int[][] m2 = createMatrix(4,3);
		m1[0][0] = 1;m1[0][1] = 2;m1[0][2] = 3; m1[0][3] = 4;
		m1[1][0] = 4;m1[1][1] = 5;m1[1][2] = 6; m1[1][3] = 7;
		m1[2][0] = 7;m1[2][1] = 8;m1[2][2] =9;  m1[2][3] = 10;
		
		m2[0][0] = 1;m2[0][1] = 8;m2[0][2] = 7;
		m2[1][0] = 2;m2[1][1] = 5;m2[1][2] = 4;
		m2[2][0] = 3;m2[2][1] = 2;m2[2][2] =1;
		m2[3][0] = 4;m2[3][1] = 2;m2[3][2] =1;
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
		testmatrix();
		
	}
}
