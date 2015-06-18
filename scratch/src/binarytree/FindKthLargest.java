package binarytree;

public class FindKthLargest {

	Node kthLargest(Node root, int ik) {
		// Create a new "K"
		K k = new K(ik);

		// Traverse the tree from largest to smallest
		return reverseIOT(root, k);
	}

	
	/**
	 * Traverse a subtree in reverse in-order (right,node,left)
	 * @param node	The subtree to traverse
	 * @param k		Exit with k.k == 0
	 * @return		The kth largest node
	 */
	private Node reverseIOT(Node node, K k) {
		if(node == null)
			return null;
	
		Node n = reverseIOT(node.right, k);
		if(n != null)
			return n;

		if(--k.k == 0)
			return node;
			
		return reverseIOT(node.left, k);
	}


	/**
	 * Node class as defined by problem
	 */
	private static class Node {
		private Node(int value) {
			this.value = value;
		}
		private final int value;
		private Node left;
		private Node right;
		
		public String toString() {
			return String.valueOf(value);
		}
	}


	/**
	 * This class holds a mutable integer
	 */
	private class K {
		private int k;
		
		private K(int k) {
			this.k = k;
		}
	}
	
	
	
	/**
	 * Test harness
	 * @param args	Ignored
	 */
	public static void main(String args[]) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		
		node4.left  = node2;
		node4.right = node6;
		node2.left  = node1;
		node2.right = node3;
		node6.left  = node5;
		node6.right = node7;
	
		FindKthLargest app = new FindKthLargest();

		System.out.println(app.kthLargest(node4, 3));
	}

}