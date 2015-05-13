package binarytree;

import utils.TreeNode;

public class LCA2 {

	public static boolean isChild(TreeNode root, TreeNode p)
	{
		if (root == null)
			return false;
		if (root == p)
			return true;
		return isChild(root.left,p) || isChild(root.right,p);
	}
	public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null)
			return root;
		/*if (root == p || root == q)
			return root;*/
		boolean is_p_left = isChild(root.left,p);
		boolean is_q_left = isChild(root.left,q);
		if (is_p_left != is_q_left)
			return root;
		if (is_p_left)
			return LCA(root.left, p, q);
		return LCA(root.right,p,q);
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
