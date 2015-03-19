package divideandconquer;

public class IntegerMultiply {

	public static void main(String[] args)
	{
		int first =  500;
		int ALL_ONES = ~0;
		int hiint = first >> 16;
		int lowint = (first << 16) & ALL_ONES;
	}
}
