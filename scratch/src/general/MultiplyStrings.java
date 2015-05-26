package general;

/**
 * http://jqmichelle.blogspot.com/2013/05/leetcode-divide-two-integers-divide-two.html
 * 
 *
 * @author Sanjeev Gupta
 *
 */
public class MultiplyStrings
{
    public static int divide(int dividend, int divisor) {
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long flaga = dividend > 0 ? 1 : -1;
        long flagb = divisor > 0 ? 1 : -1;
        while ((b << 1) <= a)
            b <<= 1;
        long c = 0;
        long absDivisor = Math.abs((long) divisor);

        while (b >= absDivisor) {
            c <<= 1;
            while (a >= b) {
                a -= b;
                c += 1;
            }
            b >>= 1;
        }
        return (int) (c * flaga * flagb);
    }
    public static String multiply(String num1, String num2) {
        if (num1==null||num1.length()==0||num2==null||num2.length()==0){
            return null;
        }
        
        int[] nums=new int[num1.length()+num2.length()];
        
        for (int i=0; i<num2.length(); i++){
            int carry=0;
            int a=num2.charAt(num2.length()-i-1)-'0';
            for (int j=0; j<num1.length(); j++){
                int b=num1.charAt(num1.length()-1-j)-'0';
                nums[i+j]+=a*b+carry;
                carry=nums[i+j]/10;
                nums[i+j]%=10;
                
            }
            nums[i+num1.length()]+=carry;
        }
        
        StringBuffer result=new StringBuffer();
        int i=nums.length-1;
        while(i>0&&nums[i]==0){
            i--;
        }
        
        while(i>=0){
            result.append(nums[i]);
            i--;
        }
        
        return result.toString();
        
    }
    public static String multiply1(String num1, String num2) {
        // Start typing your Java solution below
            // DO NOT write main() function
            if (num1 == null || num2 == null)
                return null;
            if (num1.equals("0") || num2.equals("0"))
                return new String("0");
            StringBuilder sb = new StringBuilder();
            for (int i = num1.length() - 1; i >= 0; --i) {
                int d1 = num1.charAt(i) - '0';
                int carry = 0;
                for (int j = num2.length() - 1; j >= 0; --j) {
                    int position = num1.length() - 1 - i + num2.length() - 1 - j;
                    int d2 = num2.charAt(j) - '0';
                    int d3 = sb.length() <= position ? 0 : sb.charAt(position) - '0';
                    int sum = d1 * d2 + d3 + carry;
                    carry = sum / 10;
                    sum %= 10;
                    if (sb.length() <= position)
                                sb.append((char)(sum + '0'));
                            else
                                sb.setCharAt(position, (char)(sum + '0'));
                }
                if (carry != 0)
                        sb.append((char)(carry + '0'));                        
            }
            sb.reverse();
            return sb.toString();
    }
    // does a*b
    public static String multiply3(String a, String b)
    {
        // checks
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
    public static void main(String[] args)
    {
        multiply1("234","123");
        divide(12,3);
        
        String str1 = "234";
        String str2 = "123";
        String result = multiply(str1, str2);
       
        System.out.println(result);
    }
}
