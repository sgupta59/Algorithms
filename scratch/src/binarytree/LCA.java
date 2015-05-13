package binarytree;

import utils.TreeNode;

/*
 * http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-ii.html
 * https://github.com/gaylemcd/ctci/tree/master/java/Chapter%204/Question4_7
 * http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html
 */
public class LCA {
	static int TWO_NODES_FOUND = 2;
	static int ONE_NODE_FOUND = 1;
	static int NO_NODES_FOUND = 0;
	/*
	 * Return number of nodes found in the subtree rooted at root.
	 * 
	 */
	public static int countNodes(TreeNode root, TreeNode p, TreeNode q)
	{
		// if root is null, return 0
		if (root == null)
			return 0;
		int children = 0;
		if (root == p || root == q)
			children = 1;
		children += countNodes(root.left,p,q);
		if (children == 2)
			return children;
		children += countNodes(root.right, p, q);
		return children;
	}
	 
	public static TreeNode LCA1(TreeNode root, TreeNode p, TreeNode q)
	{
		// if p and q are the same, and the root is same as p then return the root.
		if (q == p && (root.left == q || root.right == q)) return root;
		int leftcount = countNodes(root.left, p, q);
		if (leftcount == 2)
		{
			// both nodes are on the left subtree.
			if (root.left == p || root.left == q)
				return root.left;
			return LCA1(root.left, p, q);
		} 
		else if (leftcount == 1)
		{
			// left subtree has one node. this means the root is either p or q
			if (root == p) return root;
			else if (root == q) return root;
		}
		int rightcount = countNodes(root.right, p, q);
		if (rightcount == 2)
		{
			if (root.right == p || root.right == q)
				return root.right;
			return LCA1(root.right, p, q);
		}
		else if (rightcount == 1)
		{
			if (root == p) return root;
			else if (root == q) return root;
		}
		if (leftcount == 1 && rightcount == 1)
			return root;
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
			int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			TreeNode root = TreeNode.createMinimalBST(array);
			TreeNode n3 = root.find(1);
			TreeNode n7 = root.find(7);
			TreeNode ancestor = LCA1(root, n3, n7);
			System.out.println(ancestor.data);

	}
}
