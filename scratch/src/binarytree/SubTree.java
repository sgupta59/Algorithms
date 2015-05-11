package binarytree;

import utils.AssortedMethods;
import utils.TreeNode;

/**
 * Source: https://github.com/gaylemcd/ctci/blob/master/java/Chapter%204/Question4_8/Question.java
 * Complexity:
 * 		1. For each node in Tree t1, check if t2's root is a node, so very coarse: O(nm)
 *      2. However, in reality, not every node in tree t1 will be a root of tree t2, so if there are k nodes in T2 which are in T1
 *         O(n+km)
 * @author kg
 *
 */
public class SubTree {

	public static boolean check(TreeNode t1, TreeNode t2)
	{
		if (t1 == null && t2 == null)
			return true;
		if (t1 == null && t2 != null)
			return false;
		if (t1 != null && t2 == null)
			return false;
		if (t1.data != t2.data)
			return false;
		return check(t1.left, t2.left) && check(t1.right, t2.right);
	}
	public static boolean containsTree(TreeNode t1, TreeNode t2)
	{
		
		/** Null tree is a subtree of another node */
		if (t2 == null && t1 != null)
			return true;
		// if t1 and t2 have the same root, i.e. data
		if (t1.data == t2.data)
			check(t1,t2);
		containsTree(t1.left,t2);
		containsTree(t1.right,t2);
		return true;
	}
	public static void main(String[] args) {
		// t2 is a subtree of t1
		int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int[] array2 = {2, 4, 5, 8, 9, 10, 11};
		
		TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t2 = AssortedMethods.createTreeFromArray(array2);

		if (containsTree(t1, t2))
			System.out.println("t2 is a subtree of t1");
		else
			System.out.println("t2 is not a subtree of t1");

		// t4 is not a subtree of t3
		int[] array3 = {1, 2, 3};
		TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t4 = AssortedMethods.createTreeFromArray(array3);

		if (containsTree(t3, t4))
			System.out.println("t4 is a subtree of t3");
		else
			System.out.println("t4 is not a subtree of t3");
	}
}
