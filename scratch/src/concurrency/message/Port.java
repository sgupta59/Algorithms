/*
@author  j.n.magee 20/11/96
*/
package concurrency.message;

import java.awt.*;
import java.util.*;
import java.applet.*;

/* ********************Port**************************** */
// The definition of channel assumes that there can be many sender
// but only one receiver

class Port<T> extends Selectable{

    Queue<T> queue = new LinkedList<T>();

    public synchronized void send(T v) {
        queue.add(v);
        System.out.println("Queue size: " + queue.size());
        signal();
    }

    public synchronized T receive() throws InterruptedException {
        block();
        clearReady();
        return queue.remove();
    }
}

