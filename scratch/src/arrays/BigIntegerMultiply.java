package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigIntegerMultiply {

	/**
	 * Multiply two numbers represented as strings 
	 * Takeaways
	 * 1. Store the sign, make both strings as positive integers.
	 * 2. The result will have either n+m digits or n+m-1 digits (depending on carry)
	 * 3. reverse both strings for easier multiplication, go from 0 to n
	 * 4. multiply both strings now and update the result[i+j] array with carry remainder/divisor.
	 * 5. after one loop, update result[i+j+1] with carry.
	 * 6. reverse the result and remove the leading 0 character
	 * 7. insert the sign if needed
	 * 8. char to int --> 'c' - '0';
	 * 9. int to char --> (char)(i + '0') or Character.forDigit(int, 10)
	 * 
	 * 
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static String multiplyAsStrings(String in1, String in2)
	{
		int sign = 0;

		String num1 = in1;
		if (in1.charAt(0) == '-')
		{
			num1 = in1.substring(1);
			++sign;
		}
		String num2 = in2;
		if (in2.charAt(0) == '-')
		{
			num2 = in2.substring(1);
			++sign;
		}
		/**
		 * The size of the result will be n+m digits or n+m-1 digits depending on the carry value.
		 */
		StringBuilder result = new StringBuilder(num1.length()+num2.length());

		/**
		 * Create a string with 0's as characters
		 */
		for (int i = 0; i < num1.length()+num2.length(); ++i)
		{
			result.append(Character.forDigit(0, 10));
		}
		
		// reverse the two strings for easier multiplication.
		String n1  = new StringBuilder().append(num1).reverse().toString();
		String n2 = new StringBuilder().append(num2).reverse().toString();
		
		for (int i = 0; i < n1.length(); ++i)
		{
			int carry = 0;
			for (int j = 0; j < n2.length(); ++j)
			{
				int res = (n1.charAt(i)-'0')*(n2.charAt(j)-'0')+carry+(result.charAt(i+j)-'0');
				int div = res%10;
				int rem = res/10;
				result.setCharAt(i+j, (char)(div+'0'));//Character.forDigit(div, 10));
				carry = rem;
			}
			int last = (result.charAt(i+n2.length()) -'0') + carry;
			result.setCharAt(i+n2.length(),Character.forDigit(last, 10));
		}
 
		String res = new StringBuilder().append(result).reverse().toString();
		if (res.charAt(0) == '0')
			res = res.substring(1);
		if (sign != 2)
			res = new StringBuilder().append('-').append(res).toString();
		return res;
	}
	
	public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
		boolean isNegative = (num1.get(0) < 0 && num2.get(0) >= 0)
				|| (num1.get(0) >= 0 && num2.get(0) < 0);
		num1.set(0, Math.abs(num1.get(0)));
		num2.set(0, Math.abs(num2.get(0)));

		// Reverses num1 and num2 to make multiplication easier.
		Collections.reverse(num1);
		Collections.reverse(num2);
		List<Integer> result = new ArrayList<>(num1.size() + num2.size());
		for (int i = 0; i < num1.size() + num2.size(); ++i) {
			result.add(0);
		}
		for (int i = 0; i < num1.size(); ++i) {
			for (int j = 0; j < num2.size(); ++j) {
				result.set(i + j, result.get(i + j) + num1.get(i) * num2.get(j));
				result.set(i + j + 1, result.get(i + j + 1) + result.get(i + j) / 10);
				result.set(i + j, result.get(i + j) % 10);
			}
		}

		// Skips the leading 0s and keeps one 0 if all are 0s.
		while (result.size() != 1 && result.get(result.size() - 1) == 0) {
			result.remove(result.size() - 1);
		}
		// Reverses result to get the most significant digit as the start of array.
		Collections.reverse(result);
		if (isNegative) {
			result.set(0, result.get(0) * -1);
		}
		return result;
	}
	public static void main(String[] args)
	{
		multiplyAsStrings("-123", "456");
		List<Integer> num1 = new ArrayList<Integer>();
		num1.add(1);num1.add(2);num1.add(3);
		List<Integer> num2 = new ArrayList<Integer>();
		num2.add(4);num2.add(5);num2.add(6);
		multiply(num1, num2);
	}
}
