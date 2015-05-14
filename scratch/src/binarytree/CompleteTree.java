package binarytree;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 * 
 * Complete Binary Tree:
 * 1. All levels except possibly the last level are completely full.
 * 2. All nodes are as far left as possible.
 * @author kg
 *
 */
public class CompleteTree {

	/**
	 * Complexity is <O(N), O(N)> as nodes get added to the queue for storage
	 * and traversed.
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isComplete(TreeNode root)
	{
		boolean singleNode = false;
		// create a queue
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		// add the root to the queue
		queue.offer(root);
		while (!queue.isEmpty())
		{
			TreeNode candidate = queue.poll();
			if (candidate.left != null)
			{
				// if a node with only one child node has been found and another node is found that has a left child
				// this means a node with one child is found 2 levels before this node. This is not a complete
				// binary tree, something like:
				// 
				//         X
				//        / \
				//       Y   B   Y node is a node with single child
				//      /
				//     Z         Z is the candidate with a non null left child 
				//    /
				//   A
				if (singleNode)
					return false;
				queue.offer(candidate.left);
			}
			else
			{
				// candidate has left node as null, this can happen only in the last level.
				singleNode = true;
			}
			if (candidate.right != null)
			{
				if (singleNode)
					return false;
				queue.offer(candidate.right);
			}
			else
			{
				singleNode = true;
			}
		}
		return true;
	}
	/**
	 * Height based complete tree test.
	 * 
	 * left height can not be less than the right height.
	 * 
	 * <O(log(n), O(n)> for space and time
	 * @param root
	 * @return
	 */
	public static int isCompleteHeight(TreeNode root)
	{
		// terminating condition 
		if (root == null)
			return 0;
		int lh = isCompleteHeight(root.left);
		if (lh == -1)
			return lh;
		int rh = isCompleteHeight(root.right);
		if (rh == -1)
			return rh;
		if (lh < rh)
			return -1;
		return Math.max(lh,rh)+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* Let us construct the following Binary Tree which
	      is not a complete Binary Tree
	            1
	          /   \
	         2     3
	        / \     \
	       4   5     6
	    */
		TreeNode root  = new TreeNode(1);
		  root.left         = new TreeNode(2);
		  root.right        = new TreeNode(3);
		  root.left.left   = new TreeNode(4);
		  root.left.right  = new TreeNode(5);
		  root.right.right = new TreeNode(6);
		  int h = isCompleteHeight(root);
		  System.out.println("Complete Tree: " + isComplete(root));
	}

}
