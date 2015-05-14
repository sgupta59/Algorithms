package binarytree;

import utils.TreeNode;

/**
 * A full binary tree is a tree in which each node has either 2 children or one child.
 * http://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/
 * @author kg
 *
 */
public class FullTree {

	/**
	 * <O(logn), O(n)> complexity
	 * @param node
	 * @return
	 */
	public static boolean isFull(TreeNode node)
	{
		if (node == null)
			return true;
		return isFull(node.left) && isFull(node.right); 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
	    TreeNode root = new TreeNode(10);
	    root.left = new TreeNode(20);
	    root.right = new TreeNode(30);
	 
	    root.left.right = new TreeNode(40);
	    root.left.left = new TreeNode(50);
	    root.right.left = new TreeNode(60);
	    root.right.right = new TreeNode(70);
	 
	    root.left.left.left = new TreeNode(80);
	    root.left.left.right = new TreeNode(90);
	    root.left.right.left = new TreeNode(80);
	    root.left.right.right = new TreeNode(90);
	    root.right.left.left = new TreeNode(80);
	    root.right.left.right = new TreeNode(90);
	    root.right.right.left = new TreeNode(80);
	    root.right.right.right = new TreeNode(90);
	    System.out.println("Tree full status: " + isFull(root));
	}

}
