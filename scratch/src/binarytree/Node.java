package binarytree;

public class Node {

	 
	public int value;
	public int size;
	public Node left;
	public Node right;
	public Node(   int value)
	{
		size = 1;
		this.value = value;
	}
	
	public void setLeft(Node left)
	{
		this.left = left;
	}
	
	public void setRight(Node right)
	{
		this.right = right;
	}
	
	public Node left()
	{
		return left;
	}
	
	public Node right()
	{
		return right;
	}
	
	public int value()
	{
		return value;
	}
	public String toString()
	{
		return  value + " , " + left + ", " + right ;
	}

}
