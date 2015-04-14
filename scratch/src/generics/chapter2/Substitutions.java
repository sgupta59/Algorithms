/*
 * SubstitutionTests.java
 *
 * Created on: Apr 13, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package generics.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
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
        // This is because even though nums1 references an array of Integers, if the above was legal, nums1.add(3.14) could be performed.
    }

    public static <T> void copyAll(List<? super T> dest, List<? extends T> source)
    {
        for (int i = 0; i < source.size(); ++i)
            dest.add(source.get(i));
    }

    /**
     * Get principle example, can get from a list that extends numbers as all objects will be treated as numbers.
     * @param numbers
     * @return
     */
    public static double getPrinciple(List<? extends Number> numbers)
    {
        double val = 0.0;
        for (Number num : numbers)
            val += num.doubleValue();
        return val;

    }

    public static void getPrincipleTest()
    {
        List<Integer> ints = Arrays.asList(1,2,3);
        assert getPrinciple(ints) == 6.0;

        List<Double> doubles = Arrays.asList(2.78,3.14);
        assert getPrinciple(doubles) == 5.92;
    }

    /**
     * Example of put principle, adds a value to any list that has items of super of Integer.
     * @param list
     * @param count
     */
    public static void putPrincipleExample(List<? super Integer> list, int count)
    {
        for (int i = 0; i < count; ++i)
            list.add(i);
    }

    public static void putPrincipleTest()
    {
        // create a list of integers
        List<Integer> ints = new ArrayList<Integer>();
        putPrincipleExample(ints,5);
        assert ints.toString() == "[0, 1, 2, 3, 4]";

        List<Number> nums = new ArrayList<Number>();
        putPrincipleExample(nums,5);
        nums.add(3.14);
        assert nums.toString() == "[0, 1, 2, 3, 4, 3.14]";

        List<Object> objects = new ArrayList<Object>();
        putPrincipleExample(objects, 5);
        objects.add("number");
        assert objects.toString().equals("[0, 1, 2, 3, 4, numbera]");
    }

    /**
     * When doing both get and put, do not use wildcards.
     */
    public static double GetPutExample(List<Number> nums, int n)
    {
        putPrincipleExample(nums, n);
        return getPrinciple(nums);

    }
    public static void main(String[] args)
    {
        putPrincipleTest();
    }
}
