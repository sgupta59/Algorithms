package StringAlgorithms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
 * strings.java
 *
 * Created on: Oct 28, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class strings
{
    public static void searchPattern(String text, String pattern)
    {
        int txtLen = text.length();
        int plen = pattern.length();
        for (int i = 0; i < txtLen; ++i)
        {
            if (txtLen-i < plen)
            {
                boolean found = true;
                for (int j = 0; j < plen; ++j)
                {
                    if (text.charAt(i) != pattern.charAt(j))
                    {
                        found = false;
                        break;
                    }
                }
                if (found)
                {
                    System.out.println("Match starting at index: " + i);
                }
            }
        }
    }
    public static void findRange()
    {
        int[] val = { 1, 2, 3, 4, 5, 5, 5, 5, 6,7,8};
        int lo = 0;
        int hi = val.length-1;
        int x = 2;
        while (lo < hi)
        {
            int mid = lo + (hi-lo)/2;
            int v = val[mid];
            if (x <= val[mid])
                hi = mid;
            else
                lo = mid+1;
        }
        int lower_bound = lo;
        lo = lower_bound;hi = val.length-1;
        while (lo < hi)
        {
            int mid = lo + (hi-lo)/2;
            int v = val[mid];
            if (x < v)
                hi = mid;
            else
                lo = mid+1;
        }
        System.out.println("");
    }
    public static void main(String[] args) throws IOException
    {
        findRange();
        float[] f1 = {1.0f};
        ByteArrayOutputStream o1 = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(o1);
        out.writeObject(f1);
        out.flush();
        String str = out.toString();
        Object o2  = o1.toByteArray();
        float[] f2 = (float[])o2;
        byte[] b = {-84, -19, 0, 5, 117, 114, 0, 2, 91, 70, 11, -100, -127, -119, 34, -32, 12, 66, 2, 0, 0, 120, 112, 0, 0, 0, 1, 63, -128, 0, 0};
        Object o = b;
        float[] f = (float[])o;
        findRange();
        searchPattern("thisisatest", "is");
    }
}
