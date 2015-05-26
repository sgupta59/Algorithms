package general;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class TwoSum
{
    public static int getIndex(HashMap<Integer,Double> dmap, double value)
    {
        int idx = 0;
        Set<Integer> keys = dmap.keySet();
        for (Integer values : keys)
        {
            if (dmap.get(values) == value)
            {
                idx = values;
                break;
            }
        }
        Set<Entry<Integer, Double>> entryset = dmap.entrySet();
        for (Entry<Integer,Double> entry : entryset)
        {
            System.out.println("<"+entry.getKey() + "," + entry.getValue() + ">");
        }
        return idx;
    }
    public static void find2SumUnsorted(double[] L)
    {
        HashMap<Integer, Double> dset = new HashMap<Integer, Double>();
        
        for (int idx = 0; idx < L.length; ++idx)
        {
            if (dset.containsValue(-1*L[idx]))
            {
                int idx1 = getIndex(dset,-1*L[idx]);
                System.out.println("Values:  " + L[idx] + ", " + -1*L[idx] + ", " + idx + ", " +idx1);
            }
            dset.put(idx, L[idx]);
        }
    }
    public static void find2SumSorted(double[] L)
    {
        int start = 0, end = L.length-1;
        for (int idx = 0; idx < end; ++idx)
        {
            if (start == end)
                break;
            if (L[start] + L[end] > 0.0)
                --end;
            else if (L[start] + L[end] < 0.0)
                ++start;
            else
                break;
        }
        if (start != end)
            System.out.println("Result: " + L[start] + ", " + L[end]);
    }
    public static void main (String... args)
    {
        double L[]  = {-7, -4, -2, 0, 1, 1.5, 2, 5, 9};
        
        find2SumUnsorted(L);
        Arrays.sort(L);
        Arrays.binarySearch(L, 1);
        find2SumSorted(L);
    }
}
