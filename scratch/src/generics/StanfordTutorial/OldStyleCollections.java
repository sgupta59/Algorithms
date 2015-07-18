package generics.StanfordTutorial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class OldStyleCollections {

	class Foo implements Comparable<Object> {

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}}
	public static <T extends Comparable<? super T>> T max (Collection<T> coll)
	{
		return null;
	}
	public static <T> T writeAll(Collection<? extends T> c, Sink<? super T> s)
	{
		Collection<Foo> coll = new ArrayList<Foo>();
		Foo m = max(coll);
		T last=null;
		for ( T item : c)
		{
			last = item;
			s.flush(last);
		}
		return last;
	}
	
	public static void testWriteAll()
	{
		Collection<String> c = null;
		Sink<Object> s = null;
		String str = writeAll(c, s);
		Comparator<Object> cmp = new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return 0;
			} 
	    };
	    Test1<String> strtree = new Test1<String>(cmp);
	}
	public static void test()
	{
		List<?>[] lsa = new List<?>[10];
		 
		//Object o = lsa;
		
		Object[] objs = (Object[ ])lsa;
		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(3));
		lsa[1] = li;
		Object obj = lsa[1].get(0);
		String s = (String)lsa[1].get(0);
	}
	/**
	 * Type erasure example.
	 * List<String> ys has erasure of List.
	 * List xs has no erasure.
	 * xs.add(integer) works.
	 * last line is translated in the code as (String)ys.iterator().next(). 
	 * This gives a ClassCastExample
	 * @param x
	 * @return
	 */
	public String loophole(Integer x)
	{
		List<String> ys = new LinkedList<String>();
		List xs = ys;
		xs.add(x);
		Collection cs = new ArrayList<String>();
		
		return ys.iterator().next();
	}
	/**
	 * generic method that takes an array of T objects and adds them to a Collection of T objects.
	 * note: if it is Collection<? extends T> you still can not add to the Collection
	 * @param array
	 * @param c
	 */
	public static <T> void addToCollection(T[] array, Collection<T> c)
	{
		for (T item : array)
			c.add(item);
	}
	
	public static void TestGenericMethod_addToCollection()
	{
		// create an array of objects
		Collection<Object> objc = new ArrayList<Object>();
		Collection<Integer> intc = new ArrayList<Integer>();
		Object[] objs = new Object[2];
		addToCollection(objs, objc);
		String[] strings = new String[2];
		for (int i = 0; i < strings.length; ++i)
			strings[i] = Integer.toString(i);
		// Object argument inferred.
		addToCollection(strings, objc);
		// now you can print the array of objects ONLY.
		Collection<String> stringc = new ArrayList<String>();
		addToCollection(strings, stringc);
		int[] ints = new int[2];
		for (int i = 0; i < ints.length; ++i)
			ints[i] = i;
		// FAILS: is it an int or string?
		//addToCollection(ints, stringc);
		// FAILS: No autoboxing.
		//addToCollection<Integer>(ints,intc);
	}
	/**
	 * The collection is of unknown subtypes of Person
	 * Since the type is unknown, can not add a person/worker to it.
	 * @param c
	 */
	public static void AddToCollection(Collection<? extends Person> c)
	{
		//c.add(new Person("abc"));
		//c.add(new Worker("a", "b"));
		//c.add(new Object());
		//Collection<? extends Object> c1 = new ArrayList<Person>();
		//c1.add(new Object());
	}
	/**
	 * Old style collection.
	 * @param c
	 */
	public static void PrintCollection(Collection c)
	{
		Iterator iter = c.iterator();
		while (iter.hasNext()) {
			System.out.println("->" + iter.next());
		}
	}
	/**
	 * Method name has to be changed as otherwise it has the same erasure as PrintColletion(Collection c)
	 * @param c
	 */
	public static void PrintCollection1(Collection<Object> c)
	{
		for (Object obj : c)
			System.out.println(obj);
	}
	
	/**
	 * 1. Method name can not be the same as others because of erasure.
	 * 2. This is a collection of Unknown types. However, the type is an object so objects can be extracted.
	 * 3. Extracting an Object here still. 
	 * @param c
	 */
	public static void PrintCollection2(Collection<?> c)
	{
		for (Object obj : c)
			System.out.println(obj);
	}
	
	/**
	 * Erasure again!! name has to be different.
	 * This is a bounded wildcard. There is an unknown type that extends a person.
	 * Takes in a collection of anythign that extends persons. e.g. Person, Worker
	 * @param c
	 */
	public static void PrintCollection3(Collection<? extends Person> c)
	{
		for (Person p : c)
			System.out.println(p.toString());
	}
	
	
	public static void GenericWildcardTest()
	{
		Collection<?> collection = new ArrayList<Person>();
		
		
		// FAILS
		//    Collection has type ? so can not add person.
		//collection.add(new Person("abc"));
		
		// works as every type can be null
		collection.add(null);
	}
	/**
	 * Generics test. An ArrayList<Person> is not a subtype of ArrayList<Object>
	 * FAILS: PrintCollection1(persons)
	 * PASSES: PrintCollection(persons) --> erasure/bridging
	 */
	public static void arrayListSubtypeTest()
	{
		ArrayList<Person> persons = new ArrayList<Person>();
	    persons.add(new Person("twoa"));
	    persons.add(new Person("threea"));
	    PrintCollection(persons);
	    // PrintCollection1(persons);
	    PrintCollection2(persons);
	}
	public static void oldTest1()
	{
		// create 3 workers and 3 persons and add them to collection.
		ArrayList personlist = new ArrayList(); 
		personlist.add(new Person("one"));
		personlist.add(new Person("two"));
		personlist.add(new Person("three"));
		personlist.add(new Worker("oneA", "job1"));
		personlist.add(new Worker("oneA", "job2"));
		PrintCollection1(personlist);
		PrintCollection(personlist);
	}
	
	public static void main(String[] args)  
	{ 
		
		test();
	    
	}
}

class Test1<E> {
	Test1() {}
	Test1(Comparator<? super E> cmp){}
	}
interface Sink<T> {
	void flush(T t);
}
