package binarytree;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import utils.Print;

public class BinaryTree {

	Node _root = null;
	static int s_counter = 0;
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
	
	/**
	 * Pre order traversal. Used to print document table of contents
	 * http://www.orcca.on.ca/~yxie/courses/cs2210b-2011/htmls/notes/06-tree.pdf
	 */
	public void preorder()
	{
		preorder_r(_root);
	}
	public void preorder_r(Node node)
	{
		if (node == null)
			return;
		System.out.print(node.value + " ");
		preorder_r(node.left);
		preorder_r(node.right);
	}
	
	/**
	 * Post order traversal: calculate size of a directory tree.
	 * http://www.orcca.on.ca/~yxie/courses/cs2210b-2011/htmls/notes/06-tree.pdf
	 */
	public void postorder()
	{
		postorder_r(_root);
	}
	
	private void postorder_r(Node node)
	{
		if (node == null)
			return;
		postorder_r(node.left);
		postorder_r(node.right);
		System.out.print(node.value() + " ");
	}
	
	/**
	 * Print an arithmatic expression.
	 */
	public void inorder()
	{
		inorder_r(_root);
	}
	private void inorder_r(Node node)
	{
		if (node == null)
			return;
		inorder_r(node.left);
		System.out.print("("+ node.value + "," + node.id + ")");
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
	 * Select the kth item in the tree. The tree items are indexed by 0.
	 * The kth item will have (k-1) items less than it, i.e. on the left sub tree
	 * 
	 * @param k
	 * @return
	 */
	public int select(int k)
	{
		Node n = select_r(_root,k);
		if (n == null)
			return -1;
		return n.value;
	}
	
	private Node select_r(Node node, int k)
	{
		if (node == null)
			return null;
		if (k == size(node.left))
			return node;
		if (k < size(node.left))
			return select_r(node.left,k);
		return select_r(node.right, k-size(node.left)-1);
		
	}
	
	public int rank(int value)
	{
		return rank_r(_root,value);
	}
	
	private int rank_r(Node node, int value)
	{
		if (node == null)
			return 0;
		if (node.value == value)
			return size(node.left);
		if (value < node.value)
			return rank_r(node.left,value);
		return 1 + size(node.left) + rank_r(node.right, value); 
	}
	
	public void deleteMin()
	{
		_root = deleteMin_r(_root);
	}
	
	private Node deleteMin_r(Node node)
	{
		if (node.left == null)
			return node.right;
		node.left =  deleteMin_r(node.left);
		node.size = size(node.left)+ size(node.right)+ 1;  
		return node;
	}
	
	public void deleteMax()
	{
		_root = deleteMax_r(_root);
	}
	
	private Node deleteMax_r(Node node)
	{
		if (node.right == null)
			return node.left;
		node.right = deleteMax_r(node.right);
		node.size = 1 + size(node.left)+ size(node.right);
		return node;
	}
	
	public void delete(int key)
	{
		_root = delete_r(_root, key);
	}
	
	private Node delete_r(Node node, int key)
	{
		if (node == null)
			return null;
		if (key < node.value)
			node.left = delete_r(node.left, key);
		else if (key > node.value)
			node.right = delete_r(node.right, key);
		else
		{
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;
			Node x = min_r(node.right);
			x.right = deleteMin_r(node.right);
			x.left = node.left;
		}
		// update sizes
		node.size = 1+ size(node.left)+ size(node.right);
		return node;
	}
	
	public void findRange(Queue<Integer> q, int lo, int hi)
	{
		 findRange_r(_root, q, lo, hi);
	}
	
	/**
	 * Do an inorder type of range query to get values.
	 * 1. Visit left subtree first if node.value > lo
	 * 2. Visit current node if node.value >= lo and node.value <= hi
	 * 3. Visit the right subtree if node.value < hi
	 * @param node
	 * @param q
	 * @param lo
	 * @param hi
	 */
	private void findRange_r(Node node, Queue<Integer> q, int lo, int hi)
	{
		if (node == null)
			return;
		if (lo < node.value)
			findRange_r(node.left, q, lo, hi);
		if (lo <= node.value && hi >= node.value)
			q.offer(node.value);
		if (hi > node.value)
			findRange_r(node.right, q, lo, hi);

	}
	public void eulerTour(int[] tour, int[] levels)
	{
		s_counter = 0;
		eulerTour_r(_root, tour, levels, 0);
	}
	/**
	 * In an euler tour, process a node 3 times.
	 * 1. before visiting any child.
	 * 2. after visiting left child.
	 * 3. after visiting right child.
	 * 
	 * PREORDER = #1 only (N, L, R)
	 * INORDER  = #2 only (L N R)
	 * POSTORDER = #3 only (L R N)
	 * EULERTOUR = #1, #2, #3, i.e. N L N R N....
	 * @param node
	 * @param tour
	 * @param levels
	 * @param level
	 */
	private void eulerTour_r(Node node, int[] tour, int [] levels, int level)
	{
		if (node == null)
			return;
		/** process node before visiting left child */
		processNode(node, level,tour,levels);
		eulerTour_r(node.left,tour,levels,level+1);
		
		if (node.left != null)
		{
			/** process node after visiting left child */
			processNode(node, level,tour,levels);
		}
		
		eulerTour_r(node.right,tour,levels,level+1);
		if (node.right != null)
		{	
			/** process node after visiting right child */
			processNode(node, level,tour,levels);
		}
	}
	private void processNode(Node node, int level, int[] tour, int[] levels)
	{
		tour[s_counter] = node.id;
		levels[s_counter] = level;
		Print.array("tour ", tour);
		Print.array("levels", levels);
		++s_counter;
	}
	
	/**
	 * Count number of nodes in a tree using an euler tour.
	 * @param node
	 */
	public void eulerCount()
	{
		s_counter = 0;
		eulerCount(_root);
	}
	private void eulerCount(Node node)
	{
		int currcounter = s_counter;
		++s_counter;
		if (node.left != null)
			eulerCount(node.left);
		if (node.right != null)
			eulerCount(node.right);
		int nodesize = s_counter - currcounter ;
		if (nodesize != node.size)
		{
			System.out.println("abc");
		}
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
		
		tree.eulerCount();
		/**
		 * An euler tour will require 2N-1 nodes, each node is visited 3 times at the mostl
		 */
		int[] tour = new int[2*tree.size()-1];
		int[] levels = new int[2*tree.size()-1];
		tree.eulerTour(tour, levels);
		Queue<Integer> rangevals = new LinkedList<Integer>();
		tree.findRange(rangevals, 20, 65);
		int rank = tree.rank(3);
		tree.inorder();
		tree.deleteMax();
		tree.deleteMin();
		tree.inorder();

		int value = tree.select(5);
		
		int flval = tree.floor(17);
		int cval = tree.ceil(90);
		int minval = tree.min();
		Node test = tree.find(23);
		tree.inorder();
		
	}
	 

}
