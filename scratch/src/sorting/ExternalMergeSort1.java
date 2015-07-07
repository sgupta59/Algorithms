package sorting;

import java.util.PriorityQueue;

/**
 * Merge a huge file into a sorted file doing N way merge.
 * @author kg
 *
 */
public class ExternalMergeSort1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1, 11, 13, 44, 80};
		int[] arr2 = {12, 30, 56, 77};
		int[] arr3 = {0, 18, 33, 55};
		// create 3 array buffers
		ArrayBuffer ab1 = new ArrayBuffer(arr1);
		ArrayBuffer ab2 = new ArrayBuffer(arr2);
		ArrayBuffer ab3 = new ArrayBuffer(arr3);
		// create a priority queue of array buffers.
		PriorityQueue<ArrayBuffer> pq = new PriorityQueue<ArrayBuffer>();
		pq.add(ab1);
		pq.add(ab2);
		pq.add(ab3);
		while (!pq.isEmpty())
		{
			ArrayBuffer buf = pq.poll();
			int i = buf.poll();
			System.out.println(i);
			if (buf.hasNext())
				pq.add(buf);
		}
	}

	private static class ArrayBuffer implements Comparable<ArrayBuffer> {
		private int[] array;
		int currentpointer;
		private ArrayBuffer(int[] array)
		{
			this.array = array;
			currentpointer = 0;
		}
		
		public int compareTo(ArrayBuffer o)
		{
		     int i1 = peek();
		     int i2 = o.peek();
		     return (i1 > i2 ? 1 : (i1 == i2) ? 0 : -1);
		}
		
		public int peek()
		{
			return array[currentpointer];
		}
		
		public int poll()
		{
			int val = array[currentpointer];
			++currentpointer;
			if (currentpointer == array.length)
				currentpointer = -1;
			return val;
		}
		
		public boolean hasNext()
		{
			return currentpointer != -1;
		}
	}
}
