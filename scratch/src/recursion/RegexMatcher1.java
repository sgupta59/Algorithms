/*
 * RegexMatcher1.java
 *
 * Created on: May 26, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package recursion;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class RegexMatcher1
{
    public static boolean match(String input, String pattern)
    {
        if (input.length() == 0 && pattern.length() == 0)
            return true;
        if (input.length() == 0 && pattern.length() == 1 && pattern.charAt(0) == '*')
            return true;
        if (input.length() == 0 && pattern.length() != 1)
            return false;
        if (input.length() > 0 && pattern.length() == 0)
            return false;

        if (input.charAt(0) == pattern.charAt(0) && pattern.length() > 1 && pattern.charAt(1) == '*')
        {
            boolean bmatch = false;
            if (input.charAt(0) != input.charAt(1))
                bmatch = match(input.substring(1), pattern.substring(1));
            else
            {
                int count = 0;
                char cat = input.charAt(0);
                while (cat == input.charAt(count)) {
                    ++count;
                }
                bmatch = match(input.substring(count), pattern.substring(2));
            }
            return bmatch;
        }
        if (input.charAt(0) != pattern.charAt(0) && pattern.length() > 1 && pattern.charAt(1) == '*')
        {
            return match(input, pattern.substring(2));
        }
        /** If one character matches or is a . return true */
        if (input.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.')
            return match(input.substring(1), pattern.substring(1));
        // if there is a star


        return false;
    }
    public static void main(String[] args)
    {
        String pattern = "c*a*b";
        String input = "aab";
        System.out.println(match1(input, pattern));
    }

    public static boolean match1(String input, String pattern)
    {
        if (input.length() == 0)
        {
            if (pattern.length() == 0)
                return true;
            if (pattern.length() == 1 && pattern.charAt(0) == '*')
                return true;
            return false;
        }
        if (input.length() == 1)
        {
            if (pattern.length() == 1 && pattern.charAt(0) == '.')
                return true;
            if (pattern.length() == 1 && pattern.charAt(0) == '*')
                return true;
            if (pattern.length() == 1 && pattern.charAt(0) == input.charAt(0))
                return true;
            if (pattern.length() == 1 && pattern.charAt(0) != input.charAt(0))
                return false;
        }
        if (input.length() > 0 && pattern.length() == 0)
            return false;
        // do a look ahead, no * found
        if (pattern.length() == 1)
        {
            if (pattern.charAt(0) == input.charAt(0))
                return match1(input.substring(1), pattern.substring(1));
            return false;
        }
        if (pattern.charAt(1) != '*')
        {
            if (input.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.')
                return match1(input.substring(1), pattern.substring(1));
            return false;
        }
        int counter = 0;
        // pattern has a *
        for (int i = 0; i < input.length(); ++i)
        {
            if (input.charAt(i) == pattern.charAt(0))
            {
                ++counter;
                continue;
            }
            break;
        }
        if (input.charAt(0) == pattern.charAt(0))
        {
            return match1(input.substring(1), pattern.substring(2)) || match1(input.substring(counter), pattern.substring(2));
        }
        return match1(input, pattern.substring(2));
    }
}
