package bitwise;


/**
 * Bitwise operations.
 *
 * The bit operations are:
 *
 * &   -    AND
 * |   -    OR
 * ^   -    XOR
 * ~   -    NOT
 * <<  -    LEFT SHIFT
 * >>  -    RIGHT SHIFT
 *
 *
 * 1. (X^Y)^Y is X
 *
 * References:
 *
 * 1. http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-172-performance-engineering-of-software-systems-fall-2010/video-lectures/lecture-2-bit-hacks/MIT6_172F10_lec02.pdf
 * 2. http://www.catonmat.net/blog/low-level-bit-hacks-you-absolutely-must-know/
 * @author Sanjeev Gupta
 *
 */

public class BitTests
{
    static int BITMASK = 1;

    public static String getBinaryString(int c)
    {
        String binstring = String.format("%32s", Integer.toBinaryString(c)).replace(" ", "0");
        return binstring;
    }

    public static void main(String[] args)
    {
        leastSignificantOne(10);

    }

    public static int leastSignificantOne(int x)
    {
        System.out.println(" x: " + getBinaryString(x));
        System.out.println("-x: " + getBinaryString(-x));
        int r = x & -x;
        System.out.println("r: " + getBinaryString(r));
        return r;
    }
    /**
     * Find minimum of two integers.
     *
     *
     */
    public static int findMin(int x, int y)
    {
        //return y ^ ((x^y) & -(x<y));
        return x;
    }
    /*
     * Swap two integers using XOR operator.
     *
     * THis is because XOR is its own inverse.
     *
     * Poor at exploiting Instruction level parallelism
     *
     * (x^y)^y = x
     */
    public static void noTempSwap()
    {
        int x = 10; int y = 2;
        System.out.println("Before: " + x + ", " + y);
        x = x ^ y;
        // (x^y)^y = x, this assigns x to y
        y = x ^ y;
        // (x^y)^x = y. This assigns y to x
        x = x ^ y;
        System.out.println("After: " + x + ", " + y);
    }

    /**
     * Swap the ith and the jth bit in x. This works as follows
     * if the ith and the jth bits are different, then do a xor with 10001 (e.g.) wehre the ith and jth bits are on
     * since ith and jth bits are different, they will be effectively switched.
     * return this value.
     * @param x
     * @param i
     * @param j
     * @return
     */
    public static int swapBits(int x, int i, int j)
    {
        // First get the ith and the jth bits and see if they are the 0 or 1
        // get the ith bit in x.
        int lo = ((x >> i) & 1);
        // Get the jth bit in x
        int hi = ((x >> j) & 1);
        // Only if the ith bit and the jth bits are different, this logic works, e.g.
        // 10010
        // at i = 0, bit = 0. At i = 4, bit = 1 so this logic will work. This is because we do an or between the unmatched bits and 1's
        // which flips the bits, here
        // 10010
        // 10001
        // 00011 --> 0th bit got flipped, other bits remain the same.
        if ((lo ^ hi) != 0) {

            // First create a number where ith and jth bits are turned on. Then xor it with x.
            // since the ith and jth bits in x are different, xor will flip the ith and jth bits in x.
            x ^= ((1 << i) | (1 << j));
        }
        // Now return x
        return x;
    }
    public static int reverseValue(int x)
    {
        int n = 32;
        for (int idx = 0; idx < n/2; ++idx) {
            x = swapBits(x, idx, n-idx-1);
        }
        return x;
    }

    public static void shiftTypes()
    {
        int c = -153;
        System.out.println(getBinaryString(c));
        System.out.println(getBinaryString(c));
        // Arithmatic shift
        System.out.println(getBinaryString(c >> 2));
        System.out.println(getBinaryString(c >> 2));
        System.out.println(getBinaryString(c << 2));
        System.out.println(getBinaryString(c << 2));
        // Logical shift
        System.out.println(getBinaryString(c >>> 2));
        System.out.println(getBinaryString(c >>> 2));
        System.out.println( getBinaryString(c >>> 2));
        System.out.println(Integer.toBinaryString(c << 2));
        System.out.println(getBinaryString(c << 2));
        System.out.println();

        c = 153;
        System.out.println(getBinaryString(c));
        System.out.println(getBinaryString(c >>= 2));
        System.out.println(getBinaryString(c <<= 2));
        System.out.println(getBinaryString(c >>>= 2));
        System.out.println(getBinaryString(c <<= 2));
    }
    public static void turnOffRightMost()
    {
        int value = 8;
        System.out.println("Initial number:  " +  value     + " " + Integer.toBinaryString(value));
        System.out.println("Temp number:     " +  (value-1) + " " + Integer.toBinaryString(value-1));
        value = value & value -1;
        System.out.println("Initial number:  " +  value + " " + Integer.toBinaryString(value));
    }
    public static void countBits()
    {
        System.out.println("Number of bits in 100: " + countBitsSimple(100));
        System.out.println("Number of bits in 100: " + countBitsOptimized(5));
    }
    public static void printBits()
    {
        System.out.println("Number: " + 10 + ", Binary String["+Integer.toBinaryString(10)+"]");
        System.out.println("Number: " + -10 + ", Binary String["+Integer.toBinaryString(-10)+"]");
        System.out.println("Number: " + -10 +  "Right Shift:" + (-10>>1) + " Binary String["+Integer.toBinaryString(-10>>1)+"]");

        System.out.println("Number: " + -10 +  "Unsigned Right Shift:" + (-10>>>1) + " Binary String["+Integer.toBinaryString(-10>>>1)+"]");
        System.out.println("Number: " + 8 +  " Unsigned Right Shift:" + (8>>33) + " Binary String["+Integer.toBinaryString(8>>33)+"]");
        System.out.println("Number: " + 8 +  " Unsigned Right Shift:" + (8>>>33) + " Binary String["+Integer.toBinaryString(8>>>33)+"]");
        int maxval = Integer.MAX_VALUE;
        System.out.println("Max Value Bit Pattern:  " + maxval +            ",  " + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("Min Value Bit Pattern: " + Integer.MIN_VALUE + ", " + Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println("Negative bit pattern for -5: " + Integer.toBinaryString(-5));
        System.out.println("Positive bit pattern for  5: " + Integer.toBinaryString( 5));
        System.out.println("Positive bit pattern for -5: " + Integer.toBinaryString((  ~5)+1));
        System.out.println("Right Shift 64          : " + (64 >> 1));
        System.out.println("Logical  Right Shift 64 : " + (64 >>> 1)+   ",                   " + Integer.toBinaryString((64 >>> 1)));
        System.out.println("Right Shift -64         : " + (-64 >> 1)+   ",                  " + Integer.toBinaryString((-64 >> 1)));
        System.out.println("Logical  Right Shift -64: " + (-64 >>> 1) + ",            " + Integer.toBinaryString((-64 >>> 1)));
    }

    public static int countBitsSimple(int n)
    {
        int count = 0;
        for (int idx = 0; idx < 32; ++idx)
        {
            if ((n & 1) > 0)
                ++count;
            n = n >>> 1;
        }
        return count;
    }

    public static int countBitsOptimized(int n)
    {
        int count = 0;
        while (n > 0)
        {
            ++count;
            n = n & (n-1);
        }
        return count;
    }

    public static int subtract(int a, int b)
    {
        int c = a & ~b + 1;
        int a1 = a;
        int b1 = ~b;
        int c1 = a1 + b1;
        int c2 = c1 + 1;
        System.out.println("Number: " + a + ", Binary String["+Integer.toBinaryString(a)+"]");
        System.out.println("Number: " + b + ", Binary String["+Integer.toBinaryString(b)+"]");
        System.out.println("Number: " + ~b + ", Binary String["+Integer.toBinaryString(~b)+"]");
        System.out.println("Number: " + (a & ~b) + ", Binary String["+Integer.toBinaryString((a & ~b))+"]");
        System.out.println("Number: " + (a & ~b +1) + ", Binary String["+Integer.toBinaryString(a & ~b +1)+"]");
        return a & ~b + 1;
    }
    public static int add(int a, int b){
        do {
            a = a & b; //carry
            b = a ^ b;  //addition
            a = a << 1; //carry shift to one bit left
        }while(a != 0);  //exit
        return b;     //addition result
    }

    public static int sub(int a, int b){
        return add(a, add(~b, 1));

    }

    public static boolean isOdd(int x)
    {
        if ( (x & 1) == 0)
            return false;
        return true;
    }
}
