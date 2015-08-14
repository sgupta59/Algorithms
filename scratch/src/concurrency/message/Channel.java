/*
@author  j.n.magee 20/11/96
*/
package concurrency.message;

import java.awt.*;
import java.util.*;
import java.applet.*;

/* ********************CHANNEL**************************** */
// The definition of channel assumes that there is exactly one
// sender and one receiver.

public class Channel<T> extends Selectable{

    T chan_ = null;

    public synchronized void send(T v) throws InterruptedException {
        chan_ = v;
        signal();
        while (chan_ != null) wait();
    }

    public synchronized T receive() throws InterruptedException {
        block();
        clearReady();
        T tmp = chan_; chan_ = null;
        notifyAll(); //should be notify()
        return(tmp);
    }
}

