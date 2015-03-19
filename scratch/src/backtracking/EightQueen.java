package backtracking;

public class EightQueen {

	public static int N = 8;
	
	public static void printSolution(int[] q)
	{
		for (int idx = 1; idx <= N; ++idx)
			System.out.print(" " + q[idx] + ", ");
		System.out.println("");
	}
	public static boolean isValid(int[] q, int row, int col)
	{
		for (int col1 = 1; col1 < col; ++col1)
		{
			if (q[col1] == row || 
				(col - col1) == Math.abs(row-q[col1]))
				return false;
		}
		return true;
	}
	public static int solve(int[] q, int col, int size)
	{
		if (col > N)
		{
			printSolution(q);
			return size+1;
		}
		
		for (int row = 1; row <= N; ++row)
		{
			if (isValid(q, row, col))
			{
				q[col] = row;
				size = solve(q, col+1, size);
			}
		}
		return size;
	}
	public static void main(String[] args)
	{
		int[] queens = new int[N+1];
		int size = solve(queens, 1,0);
		System.out.println("Solutions: " + size);
	}
}
