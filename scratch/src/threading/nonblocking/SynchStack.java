package threading.nonblocking;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * https://www.cs.umd.edu/class/fall2010/cmsc433/lectures.html
 *
 *
 *
 */
public class SynchStack<E> {
    private static Long total = 0L;

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    Node<E> top;

    public synchronized void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        oldHead = top;
        newHead.next = oldHead;
    }

    public synchronized E pop() {
        E oldHead;
        if (top == null)
            return null;
        oldHead = top.item;
        top = top.next;
        return oldHead;
    }

    public static void main(String[] args) throws InterruptedException {
        final SynchStack<Integer> cs = new SynchStack<Integer>();
        final ExecutorService executor = Executors.newCachedThreadPool();
        final CountDownLatch startSignal = new CountDownLatch(1);
        final int size = args.length >= 1 ? new Integer(args[0]) : 10;
        final CountDownLatch stopSignal = new CountDownLatch(size);

        // push threads

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
                            cs.push(j);
                            ++j;
                        }
                        synchronized (total) {
                            total += j;
                            stopSignal.countDown();
                        }
                    }
                });
        }

        // pop threads

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
                            cs.pop();
                            ++j;
                        }
                        synchronized (total) {
                            total += j;
                            stopSignal.countDown();
                        }
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
