package dynamicprogramming;

/**
 * Given a series of jobs, their start times and times and a weight, find a schedule with the 
 * maximum weight, consider
 * i       1  2  3  4  5  6  7  8
 * s[i] = {1, 3, 0, 4, 3, 5, 6, 8}
 * E[i] = {4, 5, 6, 7, 8, 9, 10, 11}
 * W[i] = {2, 1, 1, 3, 4, 1, 1, 2}
 *  
 *  1. Sort the jobs by the finish times such that E[i] < E[i+1]
 *  2. For a job ending at j, find a previous job i < j such that i > than all other i < j and
 *     F[i] < F[j]
 *     e.g. for job 6 finishes at time 9, the previous largest index that finishes before 
 *          job 6 starts is job 2.
 *     This just creates an array such that p[j] is the index of largest index which finishes before
 *     job j starts.
 *  3. Now, the optimum solution substructure is as follows, consider a job at index j
 *  OPT(j) = 
 *          case 1: j is not part of the optimum solution, i.e. OPT(j-1)
 *          case 2: j is part of the optimum solution then it is wj+OPT(p[j])
 *                  i.e. weight of job j and the optimum solution for job that ends before j starts.
 *          Take the maximum of this., i.e.
 *          max{OPT(j-1), wj+OPT(P[j])}
 *          
 *  The recursion is: OPT(j) = max { 1 + OPT(P[j])+wj, OPT(j-1)}
 *  Solution will be at OPT(n).
 *   
 * @author kg
 *
 */
public class ActivitySelection {

	public static void main(String[] args)
	{
		int startT[] = { 0, 1, 3, 0, 4, 3, 5, 6, 8};
		int endT[] = { 0, 4, 5, 6, 7, 8, 9, 10, 11};
		int[] W  = {0, 2, 1, 1, 3, 4, 1, 1, 2};
		int p[] = new int[startT.length];
		p[0] = 0;
		int opt[] = new int[startT.length];
		for (int idx = 1; idx < startT.length; ++idx)
			if (startT[idx] >= endT[idx-1])
				p[idx] = idx-1;
		System.out.println(p);
	}
}
