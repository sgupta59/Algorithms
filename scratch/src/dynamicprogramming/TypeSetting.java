package dynamicprogramming;

/**
 * Source: https://www.cs.virginia.edu/~shelat/4102/wp-content/uploads/2009/08/typesetting.pdf
 * 
 * @author kg
 *
 */
public class TypeSetting {

	public static void main(String[] args)
	{
		String input = "It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair, we had everything before us, we had nothing before us, we were all going direct to heaven, we were all going direct the other way - in short, the period was so far like the present period, that some of its noisiest authorities insisted on its being received, for good or for evil, in the superlative degree of comparison only.";
		int M = 50;
		String[] words = input.split(" ");
		int n = words.length;
		int[] lens = new int[n+1];
		for (int i = 1; i <= n; ++i)
		{
			lens[i] = words[i-1].length();
		}
		int[][] S = new int[n+1][n+1];
		int infty = M*M*2;
		for (int i = 1; i <= n; ++i)
		{
			S[i][i] = M - lens[i];
			for (int j = i+1; j <= n; ++j) {
				S[i][j] = S[i][j-1] - lens[j] - 1;
				if (S[i][j] < 0) {
					while (j <= n) { S[i][j++] = infty; }
				}
			}
		}
		int best[] = new int[n+1];
		int choices[] = new int[n+1];
		best[0] = 0;
		for (int i = 1; i <= n; ++i)
		{
			int min = infty;
			int ch = 0;
			for (int j = 0; j < i; ++j)
			{
				int t = best[j] + S[j+1][i]*S[j+1][i];
				if (t < min)
				{
					min = t;
					ch =j;
				}
			}
			best[i] = min;
			choices[i] = ch;
		}
		for (int i = 0; i <= n; ++i)
			System.out.println("best[" + i + "]: " + best[i] + ", choices: " + choices[i]);
		
		printReverse(choices, words, n);
		int end = n;
		int start = choices[end]+1;
		String lines[] = new String[n];
		int cnt = 0;
		while (end > 0) {
			StringBuffer buf = new StringBuffer();
			for (int j = start; j <= end; ++j) {
				buf.append(words[j-1] + " ");
			}
			lines[cnt++] = buf.toString();
			end = start-1;
			start = choices[end]+1;
		}
		for (int j = cnt-1; j >= 0; --j)
			System.out.println(lines[j]);
		 
	}
	/**
	 * This prints the words in a reverse manner.
	 * @param choices
	 * @param words
	 * @param n
	 */
	public static void printReverse(int[] choices, String[] words, int n)
	{
		// end is the last word in the word list.
		int end = n;
		// start is one after line break, line break is at choices[i]
		int start = choices[n]+1;
		while (end > 0)
		{
			// print from strat to end 
			for (int i = start; i <= n; ++i)
			{
				System.out.print(words[i-1]+" ");
			}
			System.out.println("");
			// new end is one before start which is also choices[end];
			end = choices[end];
			start = choices[end]+1;
		}
	}
}
