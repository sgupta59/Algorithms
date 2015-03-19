package dynamicprogramming;

public class KnapSackUnbound {
	static int M(int[] v, int[] w, int C)
    {
       int[] sol, mySol;
       int i, myFinalSol;

       sol   = new int[v.length];
       mySol = new int[v.length];

       /* ---------------------------
	  Base cases
	  --------------------------- */
       if ( C == 0 )
       {
	   return(0);
       }

       /* ==============================================
          Divide and conquer procedure
	  ============================================== */

       /* ---------------------------------------
          Solve the appropriate smaller problems
	  --------------------------------------- */
       for ( i = 0; i < v.length; i++ )
       {
          if ( C >= w[i] )
             sol[i] = M( v, w, C-w[i] ); // Knapsack capacity reduced by w[i]
                                         // because it has item i packed in 
				         // it already   
          else
	     sol[i] = 0;        // Not enough space to  pack item i
       }

       /* ---------------------------------------------
          Use the solutions to the smaller problems
	  to solve original problem
	  --------------------------------------------- */
       for ( i = 0; i < v.length; i++ )
       {
          if ( C >= w[i] )
	     mySol[i] = sol[i] + v[i];   // Value is increased by v[i]
                                         // because it has item i packed in 
				         // it already
          else
             mySol[i] = 0;        // Not enough space to  pack item i
       }


       /* *************************
          Find the best (maximum)
	  ************************* */
       myFinalSol = mySol[0];
       for ( i = 1; i < v.length; i++ )
          if ( mySol[i] > myFinalSol )
	      myFinalSol = mySol[i];

       return myFinalSol; 	// Return the overal best solution
   }
	public static int KnapSackUnbound_Memoized(int[] v, int[] w, int C)
	{
		int[] sol = new int[v.length];
		// variable being reduced is the weight and the value needed is the price
		// therefore, create an array of length C+1 where the index is the capacity
		// and array[idx] is the value at capacity idx
		int[] memo = new int[C+1];
		memo[0] = 0; // capacity 0 means there is no value.
		// for each capacity idx
		for (int idx = 1; idx <= C; ++idx)
		{
			// initialize solution for each stage
			for (int i = 0; i < sol.length; ++i)
				sol[i] = Integer.MIN_VALUE;
			// for each weight item j
			for (int j = 0; j < w.length; ++j)
			{
				// if capacity is more than the weight
				if (idx >= w[j])
				{
					// total value for weight w[j] is value of j + the previous
					// value of hte knapsack without the wight
					sol[j] = v[j] + memo[idx-w[j]];
				}
			}
			int maxval = sol[0];
			for (int i = 1; i < sol.length; ++i)
			{
				if (sol[i] > maxval)
					maxval = sol[i];
			}
			memo[idx] = maxval;
		}
		return memo[C];
	}
	public static int KnapSackUnbound_Recursive(int[] v, int[] w, int C)
	{
		// terminating condition of recursive function.
		if (C == 0)
			return 0;
		int[] sol = new int[v.length];
		// all solutions have a minimum value as we need a maximum value.
		for (int idx = 0; idx < sol.length; ++idx)
			sol[idx] = Integer.MIN_VALUE;
		for (int idx = 0; idx < sol.length; ++idx)
		{
			if (C >= w[idx])
			{
				// solution is an item of W[idx] taken out of W and the corresponding 
				// value v[idx] added to the solution.
				sol[idx] = KnapSackUnbound_Recursive(v, w, C-w[idx])+v[idx];
			}
		}
		int maxval = sol[0];
		for (int idx = 0; idx < sol.length; ++idx)
		{
			if (sol[idx] > maxval)
				maxval = sol[idx];
		}
		return maxval;
	}
	public static void main(String[] args)
	{
		int[] v = {1, 2, 3, 5, 6, 8};
	    int[] w = {2, 3, 5, 8, 9, 13};

	    int C, r;

	    C = 25;                  // Around 53 it starts to slow...
	    r = M(v, w, C);
	    r = KnapSackUnbound_Recursive(v, w, C);
	    r = KnapSackUnbound_Memoized(v, w, C);
	       System.out.println("Max value packed in backpack of capacity " 
					+ C + " = " + r);
	}
}
