/*
 * Fruit.java
 *
 * Created on: May 4, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package generics.ComparisonsAndBounds;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class Fruit implements Comparable<Fruit>
{
    private final String _name;
    private final int _size;

    public Fruit(String name, int size)
    {
        _name = name;
        _size = size;
    }

    @Override
    public int compareTo(Fruit other)
    {
        return _size < other._size ? -1 : _size == other._size ? 0 : 1;
    }

    public String name()
    {
        return _name;
    }
}
