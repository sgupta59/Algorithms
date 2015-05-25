package generics.chapter01;

import java.util.ArrayList;
import java.util.List;

public class GenericMethods {

	/**
	 * Generic method that converts an array into a list.
	 * 
	 * The generic mthod declares a type variable T and the input is an array of this type.
	 * @param arr
	 * @return
	 */
	public static <T> List<T> toList(T[] arr)
	{
		List<T> list = new ArrayList<T>();
		for (T item : arr)
			list.add(item);
		return list;
	}
	
	/**
	 * Generic method that converts an array into a list.
	 * 
	 * 1. The generic method declares a type T
	 * 2. The arra is in form of varrags
	 * 3. At run time, the vararg is converted into an array.
	 * 4. As the vararg is converted into an array, there is an unchecked warning as
	 *    creating an array of generic types gives an unchecked warning.
	 * 5. if there is a addAll method that takes in an array, this is a compile error.
	 * @param args
	 * @return
	 */
	public static <T> List<T> addAll(T...args)
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
