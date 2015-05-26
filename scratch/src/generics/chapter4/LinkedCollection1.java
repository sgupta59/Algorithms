package generics.chapter4;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A non static nested class gets the type parameters of the surrounding class.
 * A static nested class does not get the type parameters of the enclosing class (LinkedCollectionStatic<E> example)
 * @author kg
 *
 * @param <E>
 */
public class LinkedCollection1<E> extends AbstractCollection<E> {

	private class Node {
		private E element = null;
		private Node next = null;
		private Node(E elt) { element = elt;}
	}
	
	private Node first = new Node(null);
	private Node last = first;
	private int sz = 0;
	
	public LinkedCollection1() { }
	
	public LinkedCollection1(Collection<? extends E> col)
	{
		addAll(col);
	}
	
	public int size() { return sz;}
	
	public Iterator<E> iterator() {
		return new Iterator<E> () {
			private Node current = first;
			
			public boolean hasNext() {
				return current.next != null;
			}
			
			public E next() {
				if (current.next != null) {
					current = current.next;
					return current.element;
				} else { 
					throw new NoSuchElementException();
				}
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
