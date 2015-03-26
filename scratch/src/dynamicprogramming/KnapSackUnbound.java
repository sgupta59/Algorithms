package dynamicprogramming;

/**
 * www.mathcs.emory.edu/~cheung/Courses/323a/Syllabus/DynProg/knapsack2.html
 * @author kg
 *
 * Integer Knapsack problem unbound:

1. Any item can be picked multiple times.
2. given v1, v2, v3 and w1, w2, w3, maximise n1*v1 + n2*v2 + n3*v3 such that w1*n1 + w2*n2 + w3*n3 <= C 
   where C is the capacity of the knapsack.
   
3. an item is picked or it is not picked. but this is a tree with 3 nodes (in this case), and the max has to be found.

  KP(C) = max { KP(C-wi) + vi}
          for (i = 0; i < v.length; ++i) where C-w[i] >= 0
  if C == 0, KP(C) = 0.
  
4. This can be solved recursively, then from the tree, you can see multiple problems are solved multiple times.
5. Memoize the multipley solved problems, e.g. if KP(C-wi) is solved once, then store the results in an array say
   memo[C-wi] and return it. 
6. Draw a recursion tree to understand it.

 */
public class KnapSackUnbound {
	
	public static int KnapSackUnbound_DPTable(int[] v, int[] w, int C)
	{
		int[] sol = new int[v.length];
		// variable being reduced is the weight and the value needed is the price
		// therefore, create an array of length C+1 where the index is the capacity
		// and array[idx] is the value at capacity idx
		int[] memo = new int[C+1];
		memo[0] = 0; // capacity 0 means there is no value.
		// for each capacity idx
		for (int c = 1; c <= C; ++c)
		{
			// initialize solution for each stage
			for (int i = 0; i < sol.length; ++i)
				sol[i] = Integer.MIN_VALUE;
			// for each weight item j
			for (int j = 0; j < w.length; ++j)
			{
				// if capacity is more than the weight
				if (c >= w[j])
				{
					// total value for weight w[j] is value of j + the previous
					// value of hte knapsack without the wight
					sol[j] = v[j] + memo[c-w[j]];
				}
			}
			int maxval = sol[0];
			for (int i = 1; i < sol.length; ++i)
			{
				if (sol[i] > maxval)
					maxval = sol[i];
			}
			memo[c] = maxval;
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
	     
	    int[] memo = new int[C+1];
	    for (int idx = 0; idx < memo.length; ++idx)
	    	memo[idx] = -1;
	    r = KnapsackUnbound_Recursive_Memoized(v,w,C,memo);
	    r = KnapSackUnbound_Recursive(v, w, C);
	    r = KnapSackUnbound_DPTable(v, w, C);
	       System.out.println("Max value packed in backpack of capacity " 
					+ C + " = " + r);
	}
	
	/**
	 * memo array has the result found so far. if memo[idx] == -1, then it has not been solved.
	 * 
	 * @param v
	 * @param w
	 * @param C
	 * @param memo
	 * @return
	 */
	public static int KnapsackUnbound_Recursive_Memoized(int[] v, int [] w, int C, int[] memo)
	{
		if (memo[C] != -1)
			return memo[C];
		if (C == 0)
		{
			memo[C] = 0;
			return 0;
		}
		
		int[] locsol = new int[v.length];
		for (int idx = 0; idx < v.length; ++idx)
		{
			if (C >= w[idx])
			{
				locsol[idx] = KnapsackUnbound_Recursive_Memoized(v, w, C-w[idx],memo) + v[idx];
			}
		}
		int maxval = Integer.MIN_VALUE;
		for ( int idx = 0; idx < locsol.length; ++idx)
		{
			if (locsol[idx] > maxval)
				maxval = locsol[idx];
		}
		memo[C] = maxval;
		return maxval;
	}
}
