package dynamicprogramming.TwoD;

import utils.Print;

/**
 * References: Dynamic Programming page 294 Steven Skienna
 *             https://courses.cs.washington.edu/courses/cse417/05wi/slides05/05dp-linpart.pdf
 * @author kg
 *
 */
public class LinearPartitionProblem {

	public static void createLinearPartition(int[] part, int k)
	{
		// create a part.n+1 by k+1 matrix. This is for 1 based indexing.
		int[][] M = new int[part.length+1][k+1];
		int[][] D = new int[part.length+1][k+1];
		// create a prefix sum array
		int[] p = new int[part.length+1];
		for (int i = 0; i < part.length;++i)
			p[i+1] = p[i]+part[i];
		// initialize the boundary conditions.
		for (int i = 1; i < M.length; ++i)
			M[i][1]=p[i];
		for (int i = 1; i < M[1].length; ++i)
			M[1][i] = p[1];
		Print.matrix(M);
		for (int i = 2; i < M.length; ++i)
		{
			for (int j = 2; j < M[i].length; ++j)
			{
				M[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= i-1; ++x)
				{
					int cost = Math.max(M[x][j-1], p[i]-p[x]);
					if (M[i][j] > cost)
					{
						M[i][j] = cost;
						D[i][j] = x;
					}
				}
			}
		}
		Print.matrix(M);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] partition = {1,2,3,4,5,6,7,8,9};
		createLinearPartition(partition,3);
		
	}

}
