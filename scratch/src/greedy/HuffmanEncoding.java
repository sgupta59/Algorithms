package greedy;

import java.util.Comparator;
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
			printHuffmanCodes(root.left(), codes, top);
		}
		if (root.right() != null)
		{
			printHuffmanCodes(root.right(), codes, top);
		}
		if (root.left() == null && root.right() == null)
		{
			System.out.print(root.data() + ":");
			printArray(codes,top);
		}
	}
	
	public static void printArray(int[] codes, int top)
	{
		for (int i = 0; i < codes.length; ++i)
		{
			System.out.print(codes[i]);
		}
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Queue<Integer> intpq = new PriorityQueue(6);
		Random rand = new Random();
		for (int i = 0; i < 7; ++i)
			intpq.add(new Integer(rand.nextInt(100)));
		for (int i = 0; i < 7; ++i)
		{
			Integer in = intpq.poll();
			System.out.println("Processing Integer: " + in);
			intpq.offer(in);
		}*/
		Queue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(6);
		/*Comparator<HuffmanNode> nodecomp = new Comparator<HuffmanNode>() {

			@Override
			public int compare(HuffmanNode arg0, HuffmanNode arg1) {
				// TODO Auto-generated method stub
				if (arg0==arg1)
					return 0;
				return arg0.frequency() > arg1.frequency() ? -1 : 0;
			}
			
		};
		pq = new PriorityQueue<HuffmanNode>(6,nodecomp);*/
		pq.add(new HuffmanNode(5,'c'));
		pq.add(new HuffmanNode(1,'c'));
		pq.add(new HuffmanNode(6,'c'));
		pq.add(new HuffmanNode(9,'c'));
		pq.add(new HuffmanNode(2,'c'));
		while (!pq.isEmpty())
		{
			HuffmanNode node = pq.poll();
			System.out.println("");
		}
		
		char[] data = {'a', 'b', 'c', 'd', 'e', 'f'};
		int freq[] = {5,9,12,13,16,45};
		HuffmanCodeTest(data, freq);
	}
	 

}
