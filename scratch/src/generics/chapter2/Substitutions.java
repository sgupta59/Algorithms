/*
 * SubstitutionTests.java
 *
 * Created on: Apr 13, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package generics.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Substitution examples:
 * List<Number> is a subtype of Collection<Number>
 * ArrayList<Number> is a subtype of List<Number>
 *
 * Can a List<Integer> be subtype of List<Number> as Integer is a subtype of Number?
 * NO. example
 * List<Integer> ln = new ArrayList<Integer>();
 * ln.add(1);
 * List<Number> ln1 = ln (compile time error)
 * ln1.add(2.14) as Double is a subtype of Number
 * This is the reason List<Integer> is not a subtype of List<Number>
 * List<Number> ln = new ArrayList<Integer>() --> Compile time error.
 *
 *
 * Can a List<Number> be a subtype of List<Integer>? Same problem
 * List<Number> ln = new ArrayList<Number>();
 * ln.add(2.14)
 * List<Integer> lna = ln --> Compile error as assigning a list of double to integers.
 *
 * Integer[] is a subtype of Number[];
 *
 * @author Sanjeev Gupta
 *
 */
public class Substitutions
{

    public static void substitutionTests()
    {
        // Substitution example 1
        // ArrayList<Number> is a subtype of List<Number>
        // List<Number> which is a subtype of Collection<Number>
        List<Number> nums = new ArrayList<Number>();

        // Substitution example 2
        // 1 is an Integer (autoboxing) and Integer is a subtype of Number.
        nums.add(1);
        nums.add(2);
        nums.add(3.14);
        assert nums.toString().compareTo("[1 2 3.14]") == 0;

        // Question: Can a List<Integer> be a subtype of List<Number>?
        // no. because List<Number> can be List<Double> and List<Integer> can not be assigned to List<Double>
        // The Liskopf substitution princple fails as a double can be added to a list of integers.

        // Question: Can a List<Number> be a subtype of List<Integer>?
        // same reason as above, a List<Number> can contain doubles but a List<Double> can not be assigned to List<Integer>
        // The Liskof substitution principle fails as a list of doubles can be assigned to a list of integers.

        /// Array Examples.
        Number[] numarr = new Number[1];
        numarr[0] = 2.14;
        // casting allowed but this will cause an exception.
        Integer[] intarr = (Integer[])numarr;
    }

    public static void extendsTest()
    {
        List<Number> nums = new ArrayList<Number>();
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(1);
        ints.add(2);
        // Allowed only because List<Number> is a subtype of Collection<NUmber> and Collection.addAll has type <? extends T>
        // otherwise this will not be allowed. Integer is a subtype of Number, so anything that extends T can be added to the nums list.
        nums.addAll(ints);

        // Can get items from a type that extends another type, i.e. <? extends T> but not put items into it as the types of the list
        // might be different than the type being added to the list.
        // Different than sibstitutiontest where List<Number> num = new ArrayList<Integer>() gave compile error
        List<? extends Number> nums1 = new ArrayList<Integer>();
        // gives a compile time error.nums1.add(1);
    }

    public static <T> void copyAll(List<? super T> dest, List<? extends T> source)
    {
        for (int i = 0; i < source.size(); ++i)
            dest.add(source.get(i));
    }
    public static void main(String[] args)
    {

    }
}
