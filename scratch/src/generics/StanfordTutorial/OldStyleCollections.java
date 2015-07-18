package generics.StanfordTutorial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class OldStyleCollections {

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
	public static void main(String[] args)  
	{ 
		// create 3 workers and 3 persons and add them to collection.
		/*ArrayList personlist = new ArrayList(); 
	    personlist.add(new Person("one"));
	    personlist.add(new Person("two"));
	    personlist.add(new Person("three"));
	    personlist.add(new Worker("oneA", "job1"));
	    personlist.add(new Worker("oneA", "job2"));
	    PrintCollection(personlist);*/
	}
}