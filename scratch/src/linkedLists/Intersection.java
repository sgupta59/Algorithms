/*
 * Intersection.java
 *
 * Created on: Mar 23, 2015
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
public class Intersection
{
    // get thesize of the first list.
    // get the size of the second list.
    // get the difference
    // move the bigger list difference items
    // how move them in lockstep till an intersection point is found.
    public static void printIntersectionPoint(Node list1, Node list2)
    {
        int count1 = countListItems(list1);
        int count2 = countListItems(list2);
        Node smaller = count1 > count2 ? list2 : list1;
        Node bigger = count1 > count2 ? list1 : list2;
        for (int idx = 0; idx < Math.abs(count1-count2); ++idx)
        {
            bigger = bigger._next;
        }
        boolean intersectionFound = false;
        while (bigger != null)
        {
            if (bigger == smaller)
            {
                System.out.println("Intersection point: " + bigger._data);
                intersectionFound = true;
                break;
            }
            bigger = bigger._next;
            smaller = smaller._next;
        }
        if (!intersectionFound)
            System.out.println("No intersection found");
    }

    public static void findCircularPoint(Node head1, Node head2)
    {

        int counter = 0;
        Node temp = head1;
        while (temp != null)
        {
            ++counter;
            if (temp._next == null)
                temp._next = head1;
        }
        for (int idx = 0; idx < counter; ++idx)
        {
            head1 = head1._next;
        }
        while (head1 != null)
        {
            if (head1 == head2)
            {
                System.out.println("Intersection point: " + head1._data);
                break;
            }
            head1= head1._next;
            head2 = head2._next;
        }
    }
    static class Node
    {
        Node _next;
        int _data;

        Node(int data)
        {
            _data = data;
        }

    }

    public static void printLinkedList(Node list)
    {
        while (list != null)
        {
            System.out.print(list._data + "->");
            list = list._next;
        }
        System.out.println("");
    }

    public static int countListItems(Node head)
    {
        int count = 0;
        while (head != null)
        {
            ++count;
            head = head._next;
        }
        return count;
    }
    public static void main(String[] args)
    {
        Node list1 = new Node(3);
        list1._next = new Node(6);
        list1._next._next = new Node(9);
        list1._next._next._next = new Node(15);
        list1._next._next._next._next = new Node(30);

        Node list2 = new Node(10);
        list2._next = new Node(12);
        list2._next._next =  list1._next._next._next;

        printIntersectionPoint(list1, list2);

        // make first list circular.

        findCircularPoint(list1, list2);

    }
}


