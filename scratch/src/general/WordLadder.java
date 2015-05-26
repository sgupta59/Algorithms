package general;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
/** http://jqmichelle.blogspot.com/2013/05/leetcode-word-ladder-given-two-words.html */
public class WordLadder
{
    public static int wordLadderNaive(String start, String end, HashSet<String> dict) 
    {
        if (start.compareToIgnoreCase(end) == 0)
            return 0;
        int count = 0;
        HashSet<String> visited = new HashSet<String>();
        // for teh start string, change each character once, then look for it in the dictionary, 
        // if the change is found, then continue that path otherwise 
        for  (int idx = 0; idx < start.length(); ++idx)
        {
            char[] strarry = start.toCharArray();
            for (char c = 'a'; c < 'z'; ++c)
            {
                if (start.charAt(idx) == c)
                    continue;
                strarry[idx] = c;
                String tmp = new String(strarry);
                if (dict.contains(tmp)) 
                {
                    if (visited.contains(tmp))
                        continue;
                    ++count;
                    visited.add(tmp);
                    wordLadderNaive(tmp,end,dict);
                    start = tmp;
                    break;
                }
            }
        }
        return count;
    }
    public static int ladderLength(String start, String end, HashSet<String> dict) {
        // Start typing your Java solution below
        // DO NOT write main() function
         String st = start.toLowerCase();
         String en = end.toLowerCase();
         if (st.equals(en) || !dict.contains(en))
            return 0;
          
         int depth = 0;
         Map<String, Integer> seen = new HashMap<String, Integer>();         
         Queue<String> myQ=new LinkedList<String>();
         myQ.offer(st);
         seen.put(st, 1);

         while(!myQ.isEmpty()) {
             String s = myQ.remove();                                                
             for (int i = 0; i < s.length(); ++i) {
                 for (int j = 0; j < 26; ++j) {
                    StringBuilder sb = new StringBuilder(s);
                    sb.setCharAt(i, (char)('a' + j));
                    String cur = sb.toString();
                    if (cur.equals(en))
                        return seen.get(s) + 1;
                    if (!seen.keySet().contains(cur) && dict.contains(cur)) {
                        myQ.offer(cur);                   
                        seen.put(cur, seen.get(s) + 1);
                    }
                     
                 }
             }
            
         }
         return 0;
    }
    
    public static void main(String[] args)
    {
        //dict = ["hot","dot","dog","lot","log"]
        HashSet<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        wordLadderNaive("hit", "cog",dict);
        System.out.println("");
    }
}
