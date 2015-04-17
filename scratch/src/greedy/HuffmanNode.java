package greedy;

public class HuffmanNode implements Comparable<HuffmanNode> {

	private int _frequency;
	private char _data;
	private HuffmanNode _left;
	private HuffmanNode _right;
	 
	
	public HuffmanNode(int frequency, char data)
	{
		_frequency = frequency;
		_data = data;
	}

	public void setLeft(HuffmanNode left)
	{
		_left = left;
	}
	
	public void setRight(HuffmanNode right)
	{
		_right = right;
	}
	
	public HuffmanNode left()
	{
		return _left;
	}
	
	public HuffmanNode right()
	{
		return _right;
	}
	
	public int frequency()
	{
		return _frequency;
	}
	
	public char data()
	{
		return _data;
	}

	@Override
	public int compareTo(HuffmanNode other) {
		// TODO Auto-generated method stub
		if (this == other)
			return 0;
		return this._frequency < other._frequency ? -1 : 1;
	}
}
