/*
 * Test.java
 *
 * Created on: May 4, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package generics.chapter3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 1. Type variables are always bounded by extends, never by super.
 * 2. Type Variable: <T extends Comparable<T>>
 * 3. Bound: 'extends Comparable<T>'. This is a recursive bound, i.e. the bound depends on T
 * 4. Type: T
 * 5. <T extends Comparable<T>>  : The bound is recursive, i.e. bound on T depends on T
 * 6. <T extends Comparable<? super T>> : A type which is bounded by anything that is a supertype of T.
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class Test
{
    /**
     * A static method that returns any type T with the bound that T is a subtype of Comparable<T>
     *
     * @param col
     * @return
     */
    /**           Type signature declaration, where the type is bounded by Comparable<T> */
    public static <T extends Comparable<T>> T max(Collection<T> col)
    {
        T candidate = col.iterator().next();
        for (T ele : col)
        {
            if (candidate.compareTo(ele) < 0)
                candidate = ele;
        }
        return candidate;
    }

    /**
     * Enhancement of the above method, making it more generic.
     *
     * using the get/put principle
     *
     * col: Collection<? extends T> as we get items from the collection
     * <T extends Comparable<? super T>> as we 'put' items of type T in compareTo method.
     *
     * @param col
     * @return
     */
    public static <T extends Comparable<? super T>> T max_1(Collection<? extends T> col)
    {
        Iterator<? extends T> iter = col.iterator();
        T candidate = iter.next();
        while (iter.hasNext())
        {
            T ele = iter.next();
            if (candidate.compareTo(ele) < 0)
                candidate = ele;
        }
        return candidate;
    }
    public static void main(String[] args)
    {
        List<Integer> intlist = Arrays.asList(new Integer(0), new Integer(10), new Integer(4));
        Integer maxint = max(intlist);
        List<Apple> apples = Arrays.asList(new Apple(5), new Apple(6));
        List<Bannana> bannanas = Arrays.asList(new Bannana(2), new Bannana(5), new Bannana(7));
        List<Fruit> fruits = Arrays.asList(new Apple(1), new Bannana(10));
    }
}
