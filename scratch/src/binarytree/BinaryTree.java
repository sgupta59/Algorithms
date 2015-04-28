package binarytree;

import java.io.IOException;

public class BinaryTree {

	Node _root = null;
	
	public BinaryTree()
	{
		
	}
	
	public void put(int value)
	{
		_root = addNode_r(_root, value);
	}
	
	public Node root()
	{
		return _root;
	}
	
	private Node addNode_r(Node node, int value)
	{
		/** If node is null, create a node and return it. The back ward path will update
		 * the pointers.
		 */
		if (node == null)
			return new Node(value);
		if (value == node.value)
			return node;
		// if value is less than node's value, insert in left subtree and update
		// the left child of the current node
		if (value < node.value)
			node.left = addNode_r(node.left,value);
		else
		{
			// insert in right subtree and update the right child pointer
			node.right = addNode_r(node.right,value);
		}
		// set the size of the node	
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public int size()
	{
		return size(_root);
	}
	
	private int size(Node node)
	{
		if (node == null)
			return 0;
		return node.size;
	}
	
	public void inorder()
	{
		inorder_r(_root);
	}
	private void inorder_r(Node node)
	{
		if (node == null)
			return;
		inorder_r(node.left);
		System.out.print(node.value + " ");
		inorder_r(node.right);
	}
	
	public Node find(int value)
	{
		return find_r(_root, value);
	}
	
	private Node find_r(Node node, int value)
	{
		if (node == null)
			return null;
		if (node.value == value)
			return node;
		return value < node.value ? find_r(node.left, value) : find_r(node.right, value);
	}
	
	public int min()
	{
		return _root == null ? -1 : min_r(_root).value;
	}
	
	private Node min_r(Node node)
	{
		if (node.left == null)
			return node;
		return min_r(node.left);
	}
	
	public int max()
	{
		return _root == null ? Integer.MAX_VALUE : max_r(_root).value;
	}
	
	public Node max_r(Node node)
	{
		if (node.right == null)
			return node;
		return max_r(node.right);
	}
	
	/**
	 * Floor of a value is the largest value in the tree that is <= value
	 * @param key
	 * @return
	 */
	public int floor(int key)
	{
		Node flnode = floor_r(_root,key);
		if (flnode == null)
			return -1;
		return flnode.value;
	}
	
	/**
	 * if key is equal to root.value, then this is the floor (==) case.
	 * if key is < root.value, then the floor is in the left subtree or null (< min of tree).
	 * if key is > root.value, then floor is in the right subtree and if not found, floor is the root of 
	 * the subtree.
	 * @param root
	 * @param key
	 * @return
	 */
	private Node floor_r(Node root, int key)
	{
		if (root == null)
			return null;
		/** If value found, this is the floor */
		if (key == root.value)
			return root;
		/** If key is less than the value, then it is in the left subtree. this can be null */
		if (key < root.value)
			return floor_r(root.left,key);
		/** If key is > the root's value, then the floor is in the right subtree or the root in case
		 *  the key is greater than all values in teh right sub tree.
		 */
		Node tmp = floor_r(root.right,key);
		if (tmp == null)
			return root;
		return tmp;
	}
	
	/**
	 * ceiling of a value in the tree
	 * @param value
	 * @return
	 */
	public int ceil(int value)
	{
		Node flnode = ceil_r(_root,value);
		if (flnode == null)
			return -1;
		return flnode.value;
	}
	
	/**
	 * Ceiling is the smallest value >= key.
	 * if node's value is same as the key, then the node is the ceiling.
	 * if node's value < key, then ceiling is in the left sub tree -or- the node's value
	 * in case the key is the larget value of all values in the left sub tree.
	 * if node's value > key, then the ceiling is in the right subtree or is null.
	 * @param node
	 * @param value
	 * @return
	 */
	public Node ceil_r(Node node, int value)
	{
		if (node == null)
			return null;
		if (node.value == value)
			return node;
		if (value > node.value)
			return ceil_r(node.right,value);
		Node tmp = ceil_r(node.left, value);
		if (tmp == null)
			return node;
		return tmp;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/** S E A R C H E X A M P L E */
		BinaryTree tree = new BinaryTree();
		// root
		tree.put(35);
		
		// level 1
		tree.put(15);
		tree.put(45);
		
		// level 2
		tree.put(3);
		tree.put(30);
		tree.put(60);
		
		// level 3
		tree.put(25);
		tree.put(33);
		tree.put(55);
		tree.put(80);
		
		// level 4
		tree.put(16);
		tree.put(22);
		

		tree.inorder();
		int flval = tree.floor(17);
		int cval = tree.ceil(90);
		int minval = tree.min();
		Node test = tree.find(23);
		tree.inorder();
		
	}
	 

}
