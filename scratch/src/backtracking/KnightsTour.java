package backtracking;
/**
 * http://www.cs.armstrong.edu/liang/intro9e/supplement/SupplementKnightTour.pdf -  Case study
 * C:\Users\kg\Desktop\Personal\local\java\Introduction_to_Java Programming_Comprehensive_Version.pdf
 * http://www.cs.armstrong.edu/liang/intro9e/ExampleByChapters.html
 * chapter 30
 * @author kg
 *
 * Complexity
 * 
 * Let T(n) denote the time for finding a Hamiltonian path in a graph of n vertices and d
 * be the largest degree among all vertices. Clearly,
 * T(n) = ( d*T(n- 1)) = d*d*T(n-2) = d*d*d*T(n-3)...  = d*d*...dn01*T(n-1) = d^n where n = number of vertices
 */

public class KnightsTour {
	private static int[] xMoves = {-2, -2, -1,  1, -1, 1,  2, 2};
	private static int[] yMoves = {-1,  1, -2, -2,  2, 2, -1, 1};
	 
	public static boolean tour(int[][] board, int x, int y, int count)
	{
		// increment count by 1
		++count;
		// mark the baord
		board[x][y] = count;
				
		if (count == board.length*board.length)
			return true;
		
		// for all valid moves.
		for (int i = 0; i < xMoves.length; ++i)
		{
			int x1 = x + xMoves[i];
			int y1 = y + yMoves[i];
			if (isValid(x1, y1, board))
			{
				if (tour(board, x1, y1, count))
					return true;
			}
		}
		--count;
		board[x][y] = 0;
		
		return false;
	}
	
	private static boolean isValid(int x, int y, int[][] board)
	{
		if (x < 0 || x >= board.length)
			return false;
		if (y < 0 || y >= board[0].length)
			return false;
		if (board[x][y] != 0)
			return false;
		return true;
	}
	
	private static void printBoard(int[][] board)
	{
		for (int i = 0; i < board.length; ++i)
		{
			for (int j = 0; j < board[i].length; ++j)
				System.out.print("  " + board[i][j] + "  ");
			System.out.println("");
		}
		System.out.println("-----------------------------------");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] grid = new int[8][8];
		int count = 0;
		int x = 0; int y= 0;
		boolean status = tour(grid, x, y, count);
		printBoard(grid);
		System.out.println("Status is: " + status);
	}

}
