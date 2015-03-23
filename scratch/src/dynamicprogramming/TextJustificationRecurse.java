package dynamicprogramming;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class TextJustificationRecurse {
	private static int[] DP = null;
	private static int[] parent=null;
	private static int pageLength = 6;
	public static int total_length(ArrayList<String> text, int start, int end)
	{
		int size = 0;
		String sequence = "";
		for (int idx = start; idx <= end; ++idx)
		{
			size = size + text.get(idx).length()  ;
			sequence += " " + text.get(idx);
		}
		size = size + (end-start);
		System.out.println("Sequence: [" + sequence+"]");
		return size;
	}
	public static int badness(ArrayList<String> text, int start, int end)
	{
		if (start > end)
			return Integer.MAX_VALUE;
		int size = total_length(text,start,end);
		if (size > pageLength)
			return Integer.MAX_VALUE;
		return (pageLength-size)*(pageLength-size)*(pageLength-size);
	}
	public static int justifySuffix(ArrayList<String> text, int start, int end)
	{
		if (start < text.size())
		{
			System.out.println("Starting at: " + text.get(start));
		}
		int min = Integer.MAX_VALUE;
		if (DP[start] != Integer.MAX_VALUE)
		{
			return DP[start];
		}
		int jdx = 0 ;
		for (jdx = start+1;jdx <= text.size(); ++jdx)
		{
			int current1 = badness(text,start,jdx-1);
			int current2 = DP[jdx];
			if (current1 == Integer.MAX_VALUE || current2 == Integer.MAX_VALUE)
			{
				continue;
			}
			int current = current1 + current2;
			if (current < min)
			{
				DP[start]=current;
				parent[jdx-1]=start;
				min = current;
			}
		}
		
		return min;
	}
	public static int justifyPrefix(ArrayList<String> text, int start, int end)
	{
		
		parent[0] = 0;
		if (DP[start] != Integer.MAX_VALUE)
			return DP[start];
		System.out.println("Starting at: " + text.get(start));
		for (int jdx = 1; jdx <= start; ++jdx)
		{			
			int cost = badness(text,jdx,start);
			if (cost == Integer.MAX_VALUE || DP[jdx-1] == Integer.MAX_VALUE)
				continue;
			int min = cost + DP[jdx-1];
			if (min < DP[start])
			{
				DP[start] = min;
				parent[start] = jdx;
			}
			
		}
		return 0;
	}
	public static void main(String[] args)
	{
		String paragraph = "aaa bb cc dddd";
		StringTokenizer tokens = new StringTokenizer(paragraph);
		ArrayList<String> words = new ArrayList<String>();
		while (tokens.hasMoreElements())
		{
			words.add(tokens.nextToken());
		}
		pageLength = 6;
		int size = words.size()+1;
		DP = new int[words.size()+1];
		parent = new int[words.size()+1];
		for (int idx = 0; idx < words.size()+1;++idx)
		{
			DP[idx] = Integer.MAX_VALUE;
			parent[idx] = -1;
		}
		parent[words.size()] = words.size();
		
		int start = 0; int end = words.size();
		
		 DP[words.size()]=0;
		for (int idx = end; idx >= 0; --idx)
		{
			justifySuffix(words,idx,0);
		}
		 
		/*DP[0] = 0;
		words.add(0,"");
		for (int idx = 1; idx <= words.size(); ++idx)
		{
			justifyPrefix(words,idx,0);
		}*/
		printSolution(parent,words.size()-1);
		System.out.println("");
	}
	public static int printSolution(int[] p, int n)
	{
		int k = 0;
		if (p[n] == 0)
			k = 0;
		else
		{
			int val = p[n];
			k = printSolution(p, p[n]-1);
			k = k+1;
		}
		System.out.println("Line # " + k + ", From Word no: " + p[n] + " to word: " +n);
		return k;
	}
}
