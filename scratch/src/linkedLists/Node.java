/*
 * Node.java
 *
 * Created on: Mar 24, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package linkedLists;

class Node
{
    int _data;
    public Node _next;
    Node(int data)
    {
        _data = data;
    }
    @Override
    public String toString()
    {
        return Integer.toString(_data);
    }

    public void print()
    {
        System.out.print(_data + "->");
        Node next = _next;
        while (next != null)
        {
            System.out.print(next._data + "->");
            next = next._next;
        }
        System.out.println("");
    }
}