package generics.Reification;

import java.util.AbstractList;
import java.util.Collection;
import java.util.RandomAccess;

/**
 * Arrays refy their component types, i.e. they carry the run time type information about the type of their components.
 * The refied information is used in instance tests and casts.
 * The type variable E is not a reifiable type (turns into an Object after erasure)
 */
public class mArrayList<E> extends AbstractList<E> implements RandomAccess {
	
	private int size = 0;
	private E[] arr = null;
	
	public mArrayList(int cap)
	{
		if (cap < 0)
			throw new IllegalArgumentException("Capacity can not be negative: " + cap);
		arr = (E[])new Object[cap];
	}
	
	public mArrayList()
	{
		this(10);
	}
	
	public mArrayList(Collection<? extends E> col)
	{
		this(col.size());
		addAll(col);
	}
	
	public void ensurecapacity(int cap)
	{
		int oldcap = arr.length;
		if (cap > oldcap)
		{
			int newcap = Math.max(cap, oldcap*3/2+1);
			E[] oldarr = arr;
			arr = (E[])new Object[newcap];
			System.arraycopy(oldarr, 0, arr, 0, oldarr.length);
		}
	}
	
	private void checkbounds(int i, int size)
	{
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("index i: " + i + ", size: " + size);
	}
	
	public int size() 
	{
		return size;
	}
	
	public E get(int i)
	{
		checkbounds(i,size);
		return arr[i];
	}
	
	public E set(int i, E elt)
	{
		checkbounds(i, size);
		E old = arr[i];
		arr[i] = elt;
		return old;
	}
	
	public void add(int i, E elt)
	{
		checkbounds(i, size+1);
		ensurecapacity(size+1);
		System.arraycopy(arr, i, arr, i+1, size-i);
		++size;
		arr[i] = elt;
	}
	
	public E remove(int i)
	{
		checkbounds(i, size);
		E old = arr[i];
		System.arraycopy(arr, i+1, arr, i, size-i);
		--size;
		return old;
	}
	
	public <T> T[] toArray(T[] a)
	{
		if (a.length < size)
		{
			a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		System.arraycopy(arr, 0, a, 0, size);
		if (size < a.length)
			a[size] = null;
		return a;
	}
	
	public Object[] toArray()
	{
		return toArray(new Object[0]);
	}
}
