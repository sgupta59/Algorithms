/*
 * Example1.java
 *
 * Created on: May 5, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package generics.chapter4;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */

/*
 * 1. Type parameters appear in the header that declares the class, not the constructor (as is done in C++)
 * 2. The actual parameters are passed to the constructor when it is invoked.
 * 3. static methods can not be parametrized and/or accessed using a type parameter, because of type erasure.
 *    Only Object can be used as a parametrized type.
 *
 */
public class Pair<T,U>
{
    public final T first;
    public final U second;
    public Pair(T first, U second)
    {
        this.first = first;
        this.second = second;
    }

    public T getFirst() { return first;}

    public U getSecond() { return second;}

    public Pair<String,String> createStringStringPair()
    {
        //2. Actual type parameters are passed to the constructor when it is invoked.
        return new Pair<String,String>("a", "b");
    }
    
    public String toString() { return "[" + this.first + "," + this.second + "]";}
    public static void main(String[] args)
    {
    	Pair<String,String> stringPair = new Pair<String,String>("first","second");
    }
}
