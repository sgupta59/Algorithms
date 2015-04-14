package graph;

import java.util.Comparator;

public class Edge implements Comparator<Edge>{


	public int u;
	public int v;
	public int w;

	public Edge(int u, int v, int w)
	{
		this.u = u;
		this.v = v;
		this.w = w;
	}

    @Override
    public int compare(Edge arg0, Edge arg1)
    {
        // TODO Auto-generated method stub
        if (arg0.w == arg1.w)
            return 0;
        return arg0.w < arg1.w ? -1 : 1;
    }
}
