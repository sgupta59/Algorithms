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
 * 1. Type parameters appear in the header that declares the class, not the constructor.
 *
 */
public class Pair<T,U>
{
    private final T first;
    private final U second;
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
}