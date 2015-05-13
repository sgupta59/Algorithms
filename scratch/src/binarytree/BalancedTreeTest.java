package binarytree;

import utils.AssortedMethods;
import utils.TreeNode;

/**
 * https://github.com/gaylemcd/ctci/blob/master/java/Chapter%204/Question4_1/QuestionBrute.java
 * @author kg
 *
 */
public class BalancedTreeTest {

	public static int treeHeight(TreeNode node)
	{
		if (node == null)
			return 0;
		return Math.max(treeHeight(node.left), treeHeight(node.right))+1;
	}
	/**
	 * O(N^2) complexity as the heights are recalculated every time
	 * @param node
	 * @return
	 */
	public static boolean isBalanced1(TreeNode node)
	{
		if (node == null)
			return true;
		int lh = treeHeight(node.left);
		int rh = treeHeight(node.right);
		if (Math.abs(lh-rh) > 1)
			return false;
		return isBalanced1(node.left) && isBalanced1(node.right); 
	}
	
	/**
	 * Checks the height of a tree rooted at node.
	 * Returns -1 if the tree is unbalanced or the height of the tree.
	 * @param node
	 * @return
	 */
	public static int checkHeight(TreeNode node)
	{
		if (node == null)
			return 0;
		int lh = checkHeight(node.left);
		if (lh == -1)
			return -1;
		int rh = checkHeight(node.right);
		if (rh == -1)
			return -1;
		if (Math.abs(lh-rh) > 1)
			return -1;
		return Math.max(lh, rh)+1;
	}
	public static boolean isBalanced(TreeNode node)
	{
		if (node == null)
			return true;
		return checkHeight(node) != -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		System.out.println("Root? " + root.data);
		System.out.println("Is balanced? " + isBalanced(root));
		
		// Could be balanced, actually, but it's very unlikely...
		TreeNode unbalanced = new TreeNode(10);
		for (int i = 0; i < 10; i++) {
			unbalanced.insertInOrder(AssortedMethods.randomIntInRange(0, 100));
		}
		System.out.println("Root? " + unbalanced.data);
		System.out.println("Is balanced? " + isBalanced(unbalanced));
	}

}
