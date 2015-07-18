package threading.bridge;

class BlueCar implements Runnable {

    BridgeCanvas display;
    Bridge control;
    int id;

    BlueCar(Bridge b, BridgeCanvas d, int id) {
        display = d;
        this.id = id;
        control = b;
    }

    public void run() {
      try {
        while (true) {
            while (!display.moveBlue(id));  // not on bridge
            //System.out.println("Blue Entering Bridge 0");
            control.blueEnter();
            //System.out.println("Blue Entering Bridge 1");
            while (display.moveBlue(id))
            {
            	//System.out.println("Blue moving bridge");
            }
            //System.out.println("Blue Exiting Bridge 0");
            control.blueExit();
            //System.out.println("Blue Exiting Bridge 1");
         }
      } catch (InterruptedException e){}
    }

}
