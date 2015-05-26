package general;

import java.util.HashSet;
import java.util.LinkedList;

public class WordLadderDFS {
    
    boolean verifyStep(String s1, String s2){
        boolean diff = false;
        for(int i = 0; i < s1.length(); i ++){
            if(s1.charAt(i) != s2.charAt(i)){
                if(!diff){
                    diff = true;
                } else{
                    return false;
                }
            }
        }
        return diff;
    }
    
    int shortest = Integer.MAX_VALUE;
    public LinkedList<String> shortestPath;
    
    void dfs(String start, String previous, String end, int steps, 
                    HashSet<String> dict, HashSet<String> visited, LinkedList<String> path){
        
        if(verifyStep(previous, end) && !previous.equals(start)){
            if(steps + 1 < shortest){
                shortest = steps + 1;
                shortestPath = (LinkedList<String>)path.clone();
                return;
            }   
        }
        
        for(String s : dict){
            if( (!visited.contains(s)) && verifyStep(s, previous)){
                visited.add(s);
                path.add(s);
                dfs(start, s, end, steps + 1, dict, visited, path);
                visited.remove(s);
                path.remove(path.size() - 1);
            }
        }
        return;
    }
    
    public int ladderLength(String start, String end, HashSet<String> dict) {
        // Start typing your Java solution below
        // DO NOT write main() function
        HashSet<String> visited = new HashSet<String>();
        LinkedList<String> path = new LinkedList<String>();
        
        visited.add(start);
        dfs(start, start, end, 0, dict, visited, path);
        
        if(shortest != Integer.MAX_VALUE){
            return shortest;
        } else return 0;
    }
    
    public static void main(String[] args){
      HashSet<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        WordLadderDFS s = new WordLadderDFS();
        int res = s.ladderLength("hit","cog",dict);
        System.out.println(res);
        System.out.println(s.shortestPath);
    }
}