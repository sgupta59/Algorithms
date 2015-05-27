package recursion;

public class RegexMatcher {



	public static boolean match1(String input, String pattern)
	{
	    /** If both strings are empty, then return true */
	    if (input.length() == 0 )
	    {
	        if (pattern.length() == 0)
	            return true;
	        if (pattern.length() == 1 && pattern.charAt(0) == '*')
	            return true;
	        return false;
	    }

	    if (input.length() == 1)
	    {
	        if (pattern.length() == 1 && pattern.charAt(0) == '.' )
	            return true;
	        if (pattern.length() == 1 && pattern.charAt(0) == input.charAt(0))
	            return true;

	    }

	    if ((input.charAt(0) == pattern.charAt(0)) || pattern.charAt(0) == '.')
	    {
	        return match1(input.substring(1), pattern.substring(1));
	    }
	    if (pattern.charAt(0) == '*')
	    {
	        return match1(input.substring(1), pattern) || match1(input,pattern.substring(1));
	    }

	    return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String one = "aaab";
		String pat = "a*b";
		String substr = one.substring(one.length());
		System.out.println(match1(one, pat));
	}

}
