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
public class ConcurrentStack <E> {
    private static Long total = 0L;

    private static class Node <E> {
        public final E item;   public Node<E> next;
        public Node(E item) {
            this.item = item;
        }
    }

   AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

   public void push(E item) {
       Node<E> newHead = new Node<E>(item);
       Node<E> oldHead;
       do {
           oldHead = top.get();
           newHead.next = oldHead;
       } while (!top.compareAndSet(oldHead, newHead));
   }

   public E pop() {
       Node<E> oldHead;  Node<E> newHead;
       do {
           oldHead = top.get();
           if (oldHead == null)
               return null;
           newHead = oldHead.next;
       } while (!top.compareAndSet(oldHead, newHead));
       return oldHead.item;
   }

   public static void main (String [] args) throws InterruptedException {
       final ConcurrentStack<Integer> cs = new ConcurrentStack<Integer>();

       final ExecutorService executor = Executors.newCachedThreadPool();
       final CountDownLatch startSignal = new CountDownLatch(1);
       final int size = args.length >= 1 ? new Integer(args[0]) : 10;
       final CountDownLatch stopSignal = new CountDownLatch(size);

       // push threads

       for (int i = 0; i < size/2; i++) {
           executor.execute(new Runnable() {
                   @Override
                public void run() {
                       int j = 0;
                       try {
                           startSignal.await(); // wait for the 'go' signal
                       } catch (InterruptedException e) {
                       }
                       while (!Thread.interrupted()) {
                           cs.push(j);
                           ++j;
                       }
                       synchronized (total) {
                           total += j;
                           stopSignal.countDown(); // indicate completion
                       }
                   }
               });
       }

       // pop threads

       for (int i = 0; i < size/2; i++) {
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
