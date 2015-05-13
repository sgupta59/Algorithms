package binarytree;

import utils.TreeNode;

public class LCA3 {

	/**
	 * Bottoms up approach. The LCA can be:
	 * 1. null if root is null.
	 * 2. if root is p or q, this can happen say at the root of the tree, then we assume the other node is in the tree
	 * 3. check for p, q on the left side and check for p, q on the right side
	 *    if both left and right are not null, then p and q diverge, return the root
	 *    if one is null, then the LCA is the non null portion.
	 *    
	 *    Complexity is O(n) as all nodes are visited and O(logn) stack space?
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q)
	{
		// root is null, return null
		if (root == null)
			return null;
		// if root is p or q, then return the root. 
		if (root == p || root == q)
			return root;
		// LCA on left side
		TreeNode L = LCA(root.left, p, q);
		// LCA on right side
		TreeNode R = LCA(root.right, p, q);
		// if both sides are not null, then there is a divergence
		if (L != null && R != null)
			return root;
		// one of the sides is null, return the other.
		return L == null ? R : L;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		TreeNode n3 = root.find(1);
		TreeNode n7 = root.find(7);
		TreeNode ancestor = LCA(root, n3, n7);
		System.out.println(ancestor.data);
	}

}
