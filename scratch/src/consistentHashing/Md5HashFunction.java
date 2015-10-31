package consistentHashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashFunction implements HashFunction {

  private final MessageDigest md5;
  
  public Md5HashFunction() throws NoSuchAlgorithmException {
      md5 = MessageDigest.getInstance("MD5");
  }
  
  public int hash(Object o) {
    byte[] bs = md5.digest(o.toString().getBytes());
    
    /** Bit shifting by 0xFF is done to take care of int's 2 complement representation. */
    /** -122 is = 1111 1111 1111 1111 1111 1111 1001 0001 */
    byte b1 = (byte)0x86;
    /** Left shifting by 16 and an implict cast to an int will result in 
     * 1111 1111 1001 0001 0000 0000 0000 0000 
     */
    int I2 = b1 << 16;
    /**
     * Left shifting with an and with 0xFF will shift only those bytes and other bits are set to 0 as
     * -122  = 1111 1111 1111 1111 1111 1111 1001 0001 
     * 0xFF  = 0000 0000 0000 0000 0000 0000 1111 1111
     * &     = 0000 0000 0000 0000 0000 0000 1001 0001
     * <<16  = 0000 0000 1001 0001 0000 0000 0000 0000
     */
    int I2_2 = (b1 & 0xFF) << 16;

    return ((bs[3] & 0xFF) << 24)
      | ((bs[2] & 0xFF) << 16)
      | ((bs[1] & 0xFF) << 8)
      | ((bs[0] & 0xFF));
  }

}
