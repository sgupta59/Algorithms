package general;

public class HashTest
{
    public static String letters = "acdegilmnoprstuw";
    public static long target =680131659347l;// 956446786872726l;
    public static long hash (String s) {
        long h = 7;
        String letters = "acdegilmnoprstuw";
        for(int i = 0; i < s.length(); i++) {
            h = (h * 37 + letters.indexOf(s.charAt(i)));
        }
        return h;
    }
    
    public static boolean hashRecurse(String sofar, int idx)
    {
        String newString = sofar+ letters.charAt(idx);
        long hashcode = hash(newString);
        System.out.println("Candidate: " + newString + ", hash Code: " + hashcode);
        if (hashcode > target)
            return false;
        if (hashcode == target)
            return true;
       
        if (newString.length()  == 6 && (hashcode + letters.length() < target))
            return false;
        if (newString.length() >= 7)
            return false;
        int rem = 7 - newString.length();
        System.out.println("Candidate1:   hash Code: " + (target-(long)(Math.pow(hashcode,rem)+32)));
        if ((long)(Math.pow(hashcode,rem)+32) < target)
            return false;
        for (int idx1 = 0; idx1 < letters.length();++idx1)
        {
            if (   hashRecurse(newString,idx1))
                return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        // Find a 9 letter string of characters that contains only letters from acdegilmnoprstuw such that the hash is 956446786872726
        //if we were trying to find the 7 letter string where hash(the_string) was 680131659347, the answer would be "leepadg".) 
        String letters = "acdegilmnoprstuw";
        System.out.println("Hash: " + hash("leepadg"));
        System.out.println("Hash: " + hash("leepagd"));
        for (int idx = 0; idx < letters.length(); ++idx)
        {
            if (hashRecurse("",idx))
                System.out.println("Starts with : " + letters.charAt(idx));
        }
    }
}
