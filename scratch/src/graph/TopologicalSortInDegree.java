package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSortInDegree {

	private static class Vertex {
		// add constructor.
		int indegree;
		int id;
		List<Vertex> adjList = new LinkedList<Vertex>();
		Vertex(int id) 
		{
			this.id = id;
			this.indegree = 0;
		}
		
		void addEdge(Vertex v)
		{
			adjList.add(v);
		}
		
		public String toString()
		{
			return "Vertex: " + id;
		}
		
		public boolean equals(Object other)
		{
			if (this == other)
				return true;
			if (!(other instanceof Vertex))
				return false;
			Vertex v = (Vertex)other;
			return v.id == this.id;
		}
		
		public int hashCode()
		{
			return this.id;
		}
	}
	
	private static class Graph {
		
		List<Vertex> vertices = new ArrayList<Vertex>();
		Graph() {
			
		}
		
		void addEdge(Vertex from, Vertex to) {
			if (vertices.contains(from) == false)
				vertices.add(from);
			if (vertices.contains(to) == false)
				vertices.add(to);
			from.addEdge(to);
		}
		
		Map<Vertex, Integer> getIndegrees()
		{
			Map<Vertex,Integer> indegreeMap = new HashMap<Vertex,Integer>();
			for (Vertex v : vertices)
			{
				for (int i = 0; i < v.adjList.size() ; ++i) {
					v.adjList.get(i).indegree++;
				}
			}
			for (Vertex v : vertices)
				indegreeMap.put(v, v.indegree);
			return indegreeMap;
		}
	}
	
	public static void topologicalSort(Graph g)
	{
		Map<Vertex,Integer> degreeMap = g.getIndegrees();
		Queue<Vertex> zeroQueue = new LinkedList<Vertex>();
		for (Map.Entry<Vertex, Integer> entry : degreeMap.entrySet()) 
		{
			if (entry.getValue() == 0)
				zeroQueue.add(entry.getKey());
		}
		System.out.println("Vertex: "  );
		while (zeroQueue.isEmpty() == false) {
			Vertex from = zeroQueue.poll();
			System.out.print(from.id + ", ");
			for (Vertex to : from.adjList) {
				if (--to.indegree == 0)
					zeroQueue.offer(to);
			}
		}
	}
	
	public static void main(String[] args)
	{
		TopologicalSortInDegree.Graph g = new TopologicalSortInDegree.Graph();
		Vertex v1 = new TopologicalSortInDegree.Vertex(1);
		Vertex v2 = new TopologicalSortInDegree.Vertex(2);
		Vertex v3 = new TopologicalSortInDegree.Vertex(3);
		Vertex v4 = new TopologicalSortInDegree.Vertex(4);
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v2, v4);
		g.addEdge(v3, v2);
		g.addEdge(v3, v4);
	    topologicalSort(g);
	}
}
