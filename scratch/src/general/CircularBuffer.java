/*
 * RingBuffer.java
 *
 * Created on: May 19, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package general;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class CircularBuffer<T>
{
    private final T [] items;

    private int start;

    private int end;

    private int count;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int length)
    {
        items = (T[])new Object[length];
        start = 0;
        end = -1;
        count = 0;
    }

    public int getCount()
    {
        return count;
    }

    public boolean isFull()
    {
        return count == items.length;
    }
    public boolean addAtEnd(T item)
    {
        if (isFull())
            return false;
        // if it is not full, get the index to insert.
        int newend = ++end;
        int idx = newend%items.length;
        items[idx] = item;
        // increment start and end
        ++count;
        end = idx;
        return true;
    }

    public T getAtStart()
    {
        if (end == -1)
            return null;
        T item = items[start];
        start = (++start)%items.length;
        --count;
        if (count < 0)
        {
            end = -1;
            start = 0;
        }
        return item;
    }
}
