package recursion;

public class RegexMatcher {

	public static boolean isMatch(char[] str, char[] pattern, int idx, int jdx)
	{
		if (idx == str.length && jdx == pattern.length)
			return true;
		if (idx == str.length && jdx == pattern.length-1 && pattern[jdx] == '*')
		{
			return true;
		}
		if (idx == str.length)
			return false;
		if (str[idx] == pattern[jdx] || pattern[jdx] == '+')
			return isMatch(str, pattern, ++idx, ++jdx);
		if (pattern[jdx] != '*')
			return false;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String one = "aaab";
		String pat = "a*b";
		System.out.println(isMatch(one.toCharArray(), pat.toCharArray(), one.length(), pat.length()));
	}

}
