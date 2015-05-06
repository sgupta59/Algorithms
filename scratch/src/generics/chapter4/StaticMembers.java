/*
 * StaticMembers.java
 *
 * Created on: May 6, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package generics.chapter4;


import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class StaticMembers
{
    /**
     * Generics are compiled by erasure, so at run time, List<Integer> and List<String> are implemented
     * by a single class List
     */
    public static void erasure_test()
    {
        List<Integer> ints = Arrays.asList(1,2,3);
        List<String> strings = Arrays.asList("one", "two", "three");
        System.out.println("ints type: " + ints.getClass().toString());
        System.out.println("strings type: " + strings.getClass().toString());
    }

    /**
     * Static members of a class can not refer to the type parameters of the generic class as they are shared
     * across all instantiations of different type.
     */
    public static void staticErasureTest()
    {
        Cell<String> a = new Cell<String>("abc");
        Cell<Integer> b = new Cell<Integer> (2);
        // Fails as static members of a class can not refer to the type parameters as static methods/members
        // are shared across instantians of of different types
        //System.out.println("Current Count: " + Cell<Integer>.getCount());
        System.out.println("Current Count: " + Cell.getCount());
    }
    public static void main(String[] args)
    {
        erasure_test();
    }
}

class Cell<T> {
    private final int id;
    private final T value;
    private static int count = 0;
    private static synchronized int nextId()
    {
        return count++;
    }

    public Cell(T value)
    {
        this.value = value;
        id = nextId();
    }

    public T getValue()
    {
        return value;
    }

    public int getId()
    {
        return id;
    }

    public static synchronized int getCount()
    {
        return count;
    }

}
