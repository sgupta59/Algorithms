package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/** 
 * http://www.geeksforgeeks.org/iterative-preorder-traversal/ 
 * http://www.math.ucla.edu/~wittman/10b.1.10w/Lectures/Lec18.pdf
 */
/** http://learn.hackerearth.com/tutorial/trees/19/iterative-tree-traversals/
 *  Saurabh school
 * @author kg
 *
 */
public class Traversals {

	Node _tree;
	
	Traversals()
	{
		
	}
	
	public static void preOrderTraversal1(Node root)
	{
		if (root == null)
			return;
		Deque<Node> stack = new ArrayDeque<Node>();
		stack.push(root);
		while (stack.isEmpty() == false)
		{
			Node node = stack.pop();
			System.out.print(" " + node._value);
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		System.out.println("\n");
	}
	public static void preOrderTraversal2(Node node)
	{
		 
		if (node == null)
			return;
		Deque<Node> stack = new ArrayDeque<Node>();
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				System.out.print(" " + node._value);
				stack.push(node);
				node = node.left;
			}
			if (!stack.isEmpty()) {
				Node n1 = stack.pop();
				node = n1.right;
			}
		}
		System.out.println("");
	}
	public static void main(String[] args)
	{
		 int[][] a1 = {{1,2},{3,4,5},{6,7,8,9},{}};
		 System.out.println(a1.length);
		 Float f = new Float("12");
		// Create a tree first.
		Node root = new Node(10);
		root.left = new Node(8);
		root.right = new Node(2);
		root.left.left = new Node(3);
		root.left.right = new Node(5);
		root.right.left = new Node(2);
		preOrderTraversal1(root);
		preOrderTraversal2(root);
	}
}

class Node
{
	int _value;
	Node left;
	Node right;
	Node(int value)
	{
		_value = value;
	}
}
