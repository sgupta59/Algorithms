package generics.chapter4;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedCollectionStatic<E> extends AbstractCollection<E> {

	private static class Node<T> {
		private T element;
		private Node<T> next = new Node<T>(null);
		private Node(T elt) { element = elt;}
	}
	
	private Node<E> first = new Node<E>(null);
	private Node<E> last = first;
	private int sz = 0;
	
	public LinkedCollectionStatic() { }
	
	public LinkedCollectionStatic(Collection<? extends E> col) 
	{
		addAll(col);
	}
	
	public int size() { return sz;}
	
	public boolean add(E elt)
	{
		last.next = new Node<E>(elt);
		last = last.next;
		++sz;
		return true;
	}
	
	private static class LinkedIterator<E> implements Iterator<E> 
	{
		private Node<E> current = null;
		
		private LinkedIterator(Node<E> elt)
		{
			current = elt;
		}
		
		public boolean hasNext() 
		{
			return current.next != null;
		}
		
		public E next() 
		{
			if (current.next != null)
			{
				current = current.next;
				return current.element;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public Iterator<E> iterator() {
		return new LinkedIterator<E>(first);
	}
	/*public Iterator<E> iterator() {
		return new Iterator<E>() {
			private Node<E> current = first;
			
			public boolean hasNext() 
			{
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
	}*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
