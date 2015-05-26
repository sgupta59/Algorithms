package general;

import java.util.Vector;

public class HeapUser
{
    public static void main(String[] args)
    {
        int total = 0;
        while (true)
        {
            Vector<Byte[]> bytevector = new Vector<Byte[]>();
            while (true)
            {
                Byte[] barray = new Byte[1048576];
                total = total + barray.length;
                System.out.println("Allocating: " + barray.length/1024 + " kbytes, " + ", total: "+ total);
                bytevector.add(barray);
            }
        }
        
    }
}
