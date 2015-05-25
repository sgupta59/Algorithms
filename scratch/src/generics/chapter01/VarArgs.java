package generics.chapter01;

import java.util.ArrayList;
import java.util.List;

public class VarArgs {

	/**
	 * Generic method that converts an array into a list.
	 * 
	 * 1. The generic method declares a type T
	 * 2. The arra is in form of varrags
	 * 3. At run time, the vararg is converted into an array.
	 * 4. As the vararg is converted into an array, there is an unchecked warning as
	 *    creating an array of generic types gives an unchecked warning.
	 * @param args
	 * @return
	 */
	public static <T> List<T> asList(T...args)
	{
		List<T> list = new ArrayList<T>();
		for (T item : args)
			list.add(item);
		return list;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
