package backtracking;

public class Substrings {

	public static void substring(String in, String out, int start)
	{
		for (int idx = start; idx < in.length(); ++idx)
		{
			char c = in.charAt(idx);
			String s1 = out + c;
			System.out.println(s1);
			substring(in, s1, idx+1);
		}
			
	}
	
	public static void substring_rec(String in, String rest)
	{
		// If there are no more solutions, print current subset
		if (rest.length()==0)
			System.out.println(in);
		else
		{
			// recurse on with the first character
			substring_rec(in+rest.charAt(0), rest.substring(1));
			// recurse on without the first character
			substring_rec(in,rest.substring(1));
		}
	}
	public static void main(String[] args)
	{
		substring_rec("", "abcd");
		substring("abcd", "", 0);
	}
}
