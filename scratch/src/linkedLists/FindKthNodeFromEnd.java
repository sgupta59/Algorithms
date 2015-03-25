/*
 * FindKthNodeFromEnd.java
 *
 * Created on: Mar 24, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package linkedLists;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class FindKthNodeFromEnd
{
    public static Node findKthFromEnd(Node head, int kth)
    {
        // create anohter head node and move it k items.
        Node head1 = head;
        while (kth > 1)
        {
            head1 = head1._next;
            --kth;
        }
        // list is to small.
        if (head1 == null)
        {
            return null;
        }
        // now there are two heads, move them one after another.
        while (head1._next != null)
        {
            head1 = head1._next;
            head = head._next;
        }
        return head;
    }
    public static void main(String[] args)
    {
        // 1 2 3 4 5 6 7 8
        Node head = new Node(1);
        head._next = new Node(2);
        head._next._next = new Node(3);
        head._next._next._next = new Node(4);
        head._next._next._next._next = new Node(5);
        head._next._next._next._next._next = new Node(6);
        head._next._next._next._next._next._next = new Node(7);
        head._next._next._next._next._next._next._next = new Node(8);
        head.print();
        Node kth = findKthFromEnd(head, 2);
        System.out.println("" + kth._data);
    }
}
