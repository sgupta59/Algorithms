/*
 * ReverseAlternate.java
 *
 * Created on: Mar 24, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package linkedLists;

/**
 *    Reverse alternate nodes in a linked list and append them to the end.
 *    Space Allowed is O(1)
 *
 *      Input List:  1->2->3->4->5->6
 *      Output List: 1->3->5->6->4->2
 *
 *      Input List:  12->14->16->18->20
 *      Output List: 12->16->20->18->14
 *
 * @author Sanjeev Gupta
 *
 */
public class ReverseAlternate
{
    /**
     * The list is updated as followd:
     *
     *  First find the end of the linked list (tail)
     *  For every node in the linked list, move the node->next in front of the tail.
     * @param head
     */
    public static void reverseAlternateAppend(Node head)
    {
        Node tail = null;
        Node tmp = head;
        while (tmp._next != null)
        {
            tail = tmp._next;
            tmp = tmp._next;
        }

        // now go through the list again, for every item, insert the next one in front of the tail.
        Node end = tail;
        tmp = head;
        while (tmp != tail)
        {
            Node next = tmp._next;
            if (next == tail)
                break;
            tmp._next = next._next;
            Node tailnext = tail._next;
            tail._next = next;
            next._next = tailnext;
            tmp = tmp._next;
        }
        printLinkedList(head);
    }
    public static void main(String[] args)
    {
        Node head = new Node(1);
        head._next = new Node(2);
        head._next._next = new Node(3);
        head._next._next._next = new Node(4);
        head._next._next._next._next = new Node(5);
        head._next._next._next._next._next = new Node(6);
        reverseAlternateAppend(head);
    }

    public static void printLinkedList(Node head)
    {
        while (head != null)
        {
            System.out.print("->" + head._data);
            head = head._next;
        }
        System.out.println("");
    }
}
