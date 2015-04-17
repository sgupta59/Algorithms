package greedy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding-set-2/
 *
 * @author kg
 *
 */
public class HuffmanEncoding {

	public static void HuffmanCodeTest(char[] data, int[] frequency)
	{
		// first create and add nodes to a priority queue.
		Queue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
		for (int i = 0; i < data.length; ++i)
		{
			pq.add(new HuffmanNode(frequency[i],data[i]));
		}

		while (pq.size() != 1)
		{
			HuffmanNode left = pq.poll();
			HuffmanNode right = pq.poll();
			HuffmanNode newNode = new HuffmanNode(left.frequency()+right.frequency(), 'k');
			newNode.setLeft(left);
			newNode.setRight(right);
			pq.add(newNode);
		}
		HuffmanNode root = pq.poll();
		int[] codes = new int[data.length];
		printHuffmanCodes(root, codes,0);
		System.out.println("");
	}

	public static void printHuffmanCodes(HuffmanNode root, int[] codes, int top)
	{
		if (root.left() != null)
		{
		    codes[top] = 0;
			printHuffmanCodes(root.left(), codes, top+1);
		}
		if (root.right() != null)
		{
		    codes[top] = 1;
			printHuffmanCodes(root.right(), codes, top+1);
		}
		if (root.left() == null && root.right() == null)
		{
			System.out.print(root.data() + ":");
			printArray(codes,top);
		}
	}

	public static void printArray(int[] codes, int top)
	{
		for (int i = 0; i < top; ++i)
		{
			System.out.print(codes[i]);
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		char[] data = {'a', 'b', 'c', 'd', 'e', 'f'};
		int freq[] = {5,9,12,13,16,45};
		HuffmanCodeTest(data, freq);
	}


}
