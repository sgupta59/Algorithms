package dynamicprogramming;

/************************************************************************
Source: https://www.cs.virginia.edu/~shelat/4102/wp-content/uploads/2009/08/L8-09-delivered.pdf

Matrix chain multiplication: consider
  A1*A2*A3*A4*A5...An
  
  Subproblems will be (A1)(A2...An) or (A1A2)(A3...An)... (A1....An-1)(An)
  so this is a suffix or a prefix problem, sub problems are in the range of 
  A1, A1A2, A1A2A3, A2A3A4 etc
  or more precisely
  
  OPT(i, j) = 0 if i == j
            = min { OPT(i, k) + OPT(k+1, j) + Rows i* columns k * columns j
            where i <= k < j
            
* */

public class MatrixChainMultiplication
{
	/**
	 * Matrix that stores the multiplications needed to calculate OPT(i,j)
	 * M[i][j] = OPT(i,j) i.e. multiply ai to aj.
	 * This matrix has the optimal solution.
	 * M[i][j] = 0 if i == j
	 * M[i][j] = 0 if i < j the matrix is an upper diagonal matrix.
	 * M[0][j] and M[i][0] are unused, i.e. the 0th row and column are not used.
	 */
   
	
   private int[][] m;

   /** The position to split an optimal solution to a subproblem.
    * <code>s[i][j]</code> is an index <i>k</i> such that
    * <code>m[i][j]</code> = <code>m[i][k]</code> +
    * <code>m[k+1][j]</code> + <code>p[i-1] * p[k] * p[j]</code>, for
    * <i>i</i> = 1, 2, ..., <i>n</i>-1 and <i>j</i> = 2, 3, ...,
    * <i>n</i>.  Entries <code>s[i][j]</code> for <i>i</i> = 0,
    * <i>i</i> = <i>n</i>, <i>j</i> &#x2264; 1, or <i>i</i> &gt;
    * <i>j</i> are unused.  */
   private int[][] s;

   /** How many matrices are in the chain. */
   private int n;

   /**
    * Computes an optimal parenthesization of a matrix-chain product,
    * allocating the instance variables and storing the result in
    * them.
    *
	*/
   public MatrixChainMultiplication(int[] p)
   {
	n = p.length ;	// how many matrices are in the chain
	m = new int[n][n];	// overallocate m, so that we don't use index 0
	s = new int[n][n];	// same for s
	int sol = matrixOrder_r(1,n-1,p);
	 System.out.println("Cost: " + sol + ", Path: " + optimalPath(1,n-1));
	 for (int idx = 0; idx < n; ++idx)
	 {
		 for (int jdx = 0; jdx < n; ++jdx)
		 {
			 if (idx == jdx)
				 m[idx][jdx] = 0;
			 else
				 m[idx][jdx] = Integer.MAX_VALUE;
		 }
	 }
	sol =  matrixOrder_m(1,n-1,p);
	System.out.println("Cost: " + sol + ", Path: " + optimalPath(1,n-1));
	matrixOrder(p);
	System.out.println("Cost: " + m[1][n-1] + ", Path: " + optimalPath(1,n-1)); 
   }
   
   /**
    * Recursive method for matrix multily cost
    * Path storage still requires a nxn matrix?
    * @param i
    * @param j
    * @param p
    * @return
    */
   public int matrixOrder_r(int i, int j, int[] p)
   {
	   int optval = Integer.MAX_VALUE;
	   if (i == j)
	   {
		   return 0;
	   }
	   for (int k = i; k < j; ++k)
	   {
		   int sol = matrixOrder_r(i, k, p) + matrixOrder_r(k+1,j,p) + 
				   p[i-1]*p[k]*p[j];
		   if (sol < optval)
		   {
			   optval = sol;
			   s[i][j] = k;
		   }
	   }
	   return optval;
   }
   
   public int matrixOrder_m(int i, int j, int[] p)
   {
	   if (i == j)
	   {
		   m[i][j] = 0;
		   return m[i][j];
	   }
	   if (m[i][j] != Integer.MAX_VALUE)
		   return m[i][j];
	   int optval = Integer.MAX_VALUE;
	   for (int k = i; k < j; ++k)
	   {
		   int sol = matrixOrder_m(i, k, p) + matrixOrder_m(k+1, j, p) + 
				   p[i-1]*p[k]*p[j];
		   if (sol < m[i][j])
		   {
			   m[i][j] = sol;
			   s[i][j] = k;
		   }
	   }
	   return m[i][j];
   }
   public void matrixOrder(int[] p)
   {
	   // initializse the whole matrix to be 0, this is already set to 0.
	   // Bottom to Top
	   for (int i = m.length-1; i > 0; --i)
	   {
		   // Left to right
		   for (int j = i; j <  m.length; ++j)
		   {
			   int minval = Integer.MAX_VALUE;
			   for (int k = i; k < j; ++k)
			   {
				   int sol = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
				   if (sol < minval)
				   {
					   minval = sol;
					   m[i][j] = minval;
					   s[i][j] = k;
				   }
			   }
		   }
	   }
   }
   /**
    * 0 0 0 0 0 0 
    * 0 0 15750 7875 9375 11875 
    * 0 0 0 2625 4375 7125 
    * 0 0 0 0 750 2500 
    * 0 0 0 0 0 1000 
    * 0 0 0 0 0 0 
    * ((A[1](A[2]A[3]))(A[4]A[5]))
    */
   

   private String optimalPath(int i, int j)
   {
	   if (i == j)
	   {
		   return "A[" + i + "]";
	   }
	   else
	   {
		   return "(" + optimalPath(i, s[i][j]) + optimalPath(s[i][j]+1,j) + ")";
	   }
   }
   

   
   /** Returns a <code>String</code> describing an optimal
    * parenthesization of the entire matrix chain. */
   public String toString()
   {
	return optimalPath(1, n-1);
   }
   
   public static void main(String[] args)
   {
	   int[] p = {30,35,15,5,10,20};
	   MatrixChainMultiplication mp =new MatrixChainMultiplication(p);
	   System.out.println(mp.toString());
	   
   }
}
