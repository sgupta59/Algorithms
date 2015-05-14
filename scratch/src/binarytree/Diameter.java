package binarytree;

import utils.TreeNode;

public class Diameter {

	
	/**
	 * Complexity O(N^2)?
	 * T(n) = 4T(n/2)+1;
	 * T(n/2) = 4T(n/4)+1
	 * T(n/4) = 4T(n/16)+1
	 * T(n) = 4^kT(n/2^k)+1
	 * let n = 2^k or k = logn
	 * T(n) = 4^lognT(1) or
	 *      = n^log4
	 *      = n^2 as log4 = 2?
	 * @param root
	 * @return
	 */
	public static int TreeDiameter(TreeNode root)
	{
		/** Base condition */
		if (root == null)
			return 0;
		int dl = TreeDiameter(root.left);
		int dr = TreeDiameter(root.right);
		int lh = height(root.left);
		int rh = height(root.right);
		int dc = lh+rh+1;
		return Math.max(dc, Math.max(dl,dr));
	}
	
	public static int height(TreeNode root)
	{
		if (root == null)
			return 0;
		return Math.max(height(root.left), height(root.left))+1;
	}
	public static class TreeDiaInfo
	{
		public TreeDiaInfo(int height, int diameter)
		{
			this.height = height;
			this.diameter = diameter;
		}
		int height;
		int diameter;
	}
	public static TreeDiaInfo TreeDiameterOpt(TreeNode root)
	{
		/** Base condition */
		if (root == null)
			return new TreeDiaInfo(0,0);
		int dl = TreeDiameter(root.left);
		int dr = TreeDiameter(root.right);
		int lh = height(root.left);
		int rh = height(root.right);
		int dc = lh+rh+1;
		return new TreeDiaInfo(Math.max(lh, rh)+1,Math.max(dc, Math.max(dl,dr)));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 /* Constructed binary tree is 
		        1
		      /   \
		    2      3
		  /  \
		4     5
		*/
	TreeNode root = new TreeNode(1);
	root.left        = new TreeNode(2);
	root.right       = new TreeNode(3);
	root.left.left  = new TreeNode(4);
	root.left.right = new TreeNode(5);
	System.out.println("Tree Diameter: " + TreeDiameter(root));
	}

}
