/*
@author  j.n.magee 14/11/96 revised 8.8.04
*/
package concurrency.buffer;

import concurrency.display.*;

/********************CONSUMER*******************************/

class Consumer implements Runnable {

    Buffer<Character> buf;

    Consumer(Buffer<Character> b) {buf = b;}

    public void run() {
      try {
        while(true) {
            ThreadPanel.rotate(180);
            Character c = buf.get();
            ThreadPanel.rotate(180);
        }
      } catch(InterruptedException e ){}
    }
}
