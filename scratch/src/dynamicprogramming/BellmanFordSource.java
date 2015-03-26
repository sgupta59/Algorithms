package dynamicprogramming;

public class BellmanFordSource {

	public static void printArr(int[] d)
	{
		System.out.println("Vertex		Distrance from source\n");
		for (int idx = 0; idx < d.length; ++idx)
			System.out.println(idx + "		" + d[idx]);
		System.out.println("");
	}
	public static boolean relax(int[] d, int u, int v, int w)
	{
		if (d[u] != Integer.MAX_VALUE && d[u] + w < d[v])
		{
			d[v] = d[u] + w;
			return true;
		}
		return false;
	}
	public static void BellmnFordSingSource(int[][] g, int s)
	{
		int[] d = new int[g.length];
		int[] p = new int[g.length];
		for (int idx = 0; idx < g.length; ++idx)
		{
			d[idx] = Integer.MAX_VALUE;
			p[idx] = -1;
		}
		// mark the source vertex as having distance 0, i.e. from s to s is 0.
		d[s] = 0;
		for (int i = 1; i < g.length; ++i)
		{
			for (int u = 0; u < g.length; ++u)
			{
				for (int v = 0; v < g.length; ++v)
				{
					if (g[u][v] != 0)
					{
						if (relax(d, u, v, g[u][v]))
						{
							p[v] = u;
						}
					}
				}
			}
		}
		printArr(d);
	}
	public static void main(String[] args)
	{
		int[][] g1 = {
				{0, -1, 4, 0, 0},
				{0,  0, 3, 2, 2},
				{0,  0, 0, 0, 0},
				{0,  1, 5, 0, 0},
				{0, 0, 0, -3, 0}
		};
		int[][] g = {
			     /**  t, a   , b, c  , d, e  */
				{0,0,0,0,0,0}, /** t */
				{-3,0,-4, 0, 0, 0},  /** a */
				{0, 0,0, 0,-1,-2},  /** b */
				{3,0,8,0,0,0},      /** c */
				{4,6,0,0,0,0},      /** d */
				{2,0,0,-3,0,0}    /** e */
		};
		BellmnFordSingSource(g, 0);
	}
}
