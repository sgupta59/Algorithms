package graph;



public class Edge implements Comparable<Edge> {


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
	public int compareTo(Edge arg0) {
		if (this == arg0)
			return 0;
		Edge e2 = (Edge)arg0;
		if (w == e2.w)
			return 0;
		return w < e2.w ? -1 : 1;
	}

    
}
