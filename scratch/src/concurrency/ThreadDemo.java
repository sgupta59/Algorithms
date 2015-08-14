/* j.n.magee 11/11/96
// updated 20/10/97
//updated: daniel.sykes 2013
 */

package concurrency;

import java.awt.*;
import java.applet.*;
import concurrency.display.*;


class Rotator implements Runnable {

  public void run() {
    try {
      while(true) ThreadPanel.rotate();
    } catch(InterruptedException e) {}
  }
}


public class ThreadDemo extends Applet {
  ThreadPanel A;
  ThreadPanel B;

  public void init() {
    A = new ThreadPanel("Thread A",Color.blue);
    B = new ThreadPanel("Thread B",Color.blue);
    add(A);
    add(B);
	setBackground(Color.lightGray);
  }

  public void start() {
    A.start(new Rotator());
    B.start(new Rotator());
  }

  public void stop() {
    A.stop();
    B.stop();
  }
}

