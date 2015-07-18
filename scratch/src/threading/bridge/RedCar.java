package threading.bridge;

import java.awt.*;

class RedCar implements Runnable {

    BridgeCanvas display;
    Bridge control;
    int id;

    RedCar(Bridge b, BridgeCanvas d, int id) {
        display = d;
        this.id = id;
        control = b;
    }

    public void run() {
      try {
        while(true) {
            while (!display.moveRed(id));  // not on bridge
            //System.out.println("REd Enter Bridge 0");
            control.redEnter();
            //System.out.println("REd Exiting Bridge 1");
            while (display.moveRed(id))
            {
            	//System.out.println("Red moving bridge");
            }
            //System.out.println("REd Exiting Bridge 0");
            control.redExit();
            //System.out.println("REd Exiting Bridge 1");
        }
      } catch (InterruptedException e){}
    }

}

