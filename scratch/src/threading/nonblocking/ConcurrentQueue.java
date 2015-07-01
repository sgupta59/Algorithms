package threading.nonblocking;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
/**
 * https://www.cs.umd.edu/class/fall2010/cmsc433/lectures.html
 *
 *
 *
 */
public class ConcurrentQueue<E> {
    private static Long total = 0L;

    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) { // did tail change?
                if (tailNext != null) {
                    // Queue in intermediate state, advance tail
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // In quiescent state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode); // will fail if
                        // tail already moved
                        return true;
                    }
                }
            }
        }
    }

    public E take() {
        for (;;) { // # Keep trying until take is done
            Node<E> oldHead = head.get(); // get current head
            Node<E> oldTail = tail.get(); // get current tail
            Node<E> oldHeadNext = oldHead.next.get(); // get current head.next
            if (oldHead == head.get()) { // Are head, tail, and next consistent?
                if (oldHead == oldTail) { // Is queue empty or is tail being
                    // updated?
                    if (oldHeadNext == null) { // Is queue empty?
                        return null; // # Queue is empty, can't take
                    }
                    tail.compareAndSet(oldTail, oldHeadNext); // # Tail being
                    // updated
                    // try to advance it
                } else { // No need to deal with tail
                    // Read value before CAS, otherwise another take might free
                    // the next node
                    // retval = oldHeadNext;
                    if (head.compareAndSet(oldHead, oldHeadNext)) {
                        return oldHeadNext.item;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ConcurrentQueue<Integer> cs = new ConcurrentQueue<Integer>();
        final ExecutorService executor = Executors.newCachedThreadPool();
        final CountDownLatch startSignal = new CountDownLatch(1);
        final int size = args.length >= 1 ? new Integer(args[0]) : 10;
        final CountDownLatch stopSignal = new CountDownLatch(size);

        // put threads

        for (int i = 0; i < size / 2; i++) {
            executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        int j = 0;
                        try {
                            startSignal.await();
                        } catch (InterruptedException e) {
                        }
                        while (!Thread.interrupted()) {
                            cs.put(j);
                            //System.out.println("in:" + j);
                            ++j;
                        }
                        synchronized (total) {
                            total += j;
                        }
                        stopSignal.countDown();
                    }
                });
        }

        // take threads

        for (int i = 0; i < size / 2; i++) {
            executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        int j = 0;
                        try {
                            startSignal.await();
                        } catch (InterruptedException e) {
                        }
                        while (!Thread.interrupted()) {
                            //System.out.println("out:" + cs.take());
                            cs.take();
                            ++j;
                        }
                        synchronized (total) {
                            total += j;
                        }
                        stopSignal.countDown();
                    }
                });
        }

        startSignal.countDown();
        Thread.sleep(100);
        executor.shutdownNow();
        stopSignal.await();
        System.out.println(total);
    }
}
