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
    byte[] bs1 = new byte[4];
    bs1[0] = 111;
    bs1[1] = 3;
    bs1[2] = 2;
    bs1[3] = 127;
    int i1 = bs1[3] << 24;
    int i2 = (bs1[3] & 0xFF) << 24;
    int id =  ((bs1[3] & 0xFF) << 24)
    	      | ((bs1[2] & 0xFF) << 16)
    	      | ((bs1[1] & 0xFF) << 8)
    	      | ((bs1[0] & 0xFF));
    return ((bs[3] & 0xFF) << 24)
      | ((bs[2] & 0xFF) << 16)
      | ((bs[1] & 0xFF) << 8)
      | ((bs[0] & 0xFF));
  }

}
