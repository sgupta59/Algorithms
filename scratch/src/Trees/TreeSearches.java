/*
 * BreadthFirstSearch.java
 *
 * Created on: Mar 3, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package scratch.Trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * http://11011110.livejournal.com/279880.html
 *
 * @author Sanjeev Gupta
 *
 */
public class TreeSearches
{
    public static boolean DFS_isPromising(MwayTreeNode node)
    {
        return true;
    }
    public static void DFS_recTest(MwayTreeNode root)
    {
        if (DFS_isPromising(root))
        {
            visit(root);
            for (int idx = 0; idx < root.nodes.size(); ++idx)
                DFS_rec(root.nodes.get(idx));
        }
    }
    public static void DFS_rec(MwayTreeNode root)
    {
        visit(root);
        for (int idx = 0; idx < root.nodes.size(); ++idx)
            DFS_rec(root.nodes.get(idx));

    }
    //DFS_stack1 turns into BFS_queue1 when the stack is replaced by the queue.
    // A node is visited, and its children added. The children are checked for visited status when they are processed.
    // This makes the queue and the stack interchangable
    public static void DFS_stack1(MwayTreeNode root)
    {
        Deque<MwayTreeNode> stack = new ArrayDeque<MwayTreeNode>();
        stack.push(root);
        while (stack.isEmpty()==false)
        {
            MwayTreeNode node = stack.pop();
            if (node.visited==false)
            {
                visit(node);
                node.visited = true;
                for (int idx = 0; idx < node.nodes.size(); ++idx)
                {
                    stack.push(node.nodes.get(idx));
                }
            }
        }
    }
    // DFS_stack2 turns into BFS_queue2 when stack is replaced by the queue.
    // however, this is not a dfs search as the node is printed and then added to the stack.
    // this makes visiting all siblings first and then processing them sort of like level order.
    // this is wrong.
    // This is only for BFS travel when stack is repalced by a queue
    public static void DFS_stack2(MwayTreeNode root)
    {
        Deque<MwayTreeNode> stack = new ArrayDeque<MwayTreeNode>();
        stack.push(root);
        visit(root);
        while (stack.isEmpty()==false)
        {
            MwayTreeNode node = stack.pop();
            if (node.visited==false)
            {
                node.visited = true;
                for (int idx = 0; idx < node.nodes.size(); ++idx)
                {
                    visit(node.nodes.get(idx));
                    stack.push(node.nodes.get(idx));
                }
            }
        }
    }
    // BFS Search
    // Visit a node, and then add its children to the queue
    // BFS_queue1 turns into DFS_stack1 when the queue is replaced by the stack.
    public static void BFS_queue1(MwayTreeNode root)
    {
        Queue<MwayTreeNode> queue = new LinkedList<MwayTreeNode>();
        queue.offer(root);
        while (queue.isEmpty() == false)
        {
            MwayTreeNode m = queue.poll();
            visit(m);
            for (int idx = 0; idx < m.nodes.size()  ; ++idx)
            {
                queue.offer(m.nodes.get(idx));
            }
        }
    }
    // BFS Search: Visit a node and then add it to the queue
    // Replacing queue with stack will not give DFS as the node is visited first and then queued.
    public static void BFS_Queue2(MwayTreeNode root)
    {
        Queue<MwayTreeNode> queue = new LinkedList<MwayTreeNode>();
        // add the root node to the queue.
        queue.offer(root);
        // visit the root node.
        visit(root);
        // while queue is not empty
        while (!queue.isEmpty())
        {
            // get the top of the queue
            MwayTreeNode m = queue.poll();
            // get children of this node
            for (int idx = 0; idx < m.nodes.size(); ++idx)
            {
                // visit the node
                visit(m.nodes.get(idx));
                // add the node back to the queue.
                queue.offer(m.nodes.get(idx));
            }
        }
    }
    public static void visit(MwayTreeNode node)
    {
        System.out.println("Node Data: " + node.data);
    }
    public static void main(String[] args)
    {
        MwayTree tree = new MwayTree();
        MwayTreeNode n1 = new MwayTreeNode();
        n1.data = 1;
        tree.root = n1;

        // Node 1 children
        MwayTreeNode n2 = new MwayTreeNode();
        n2.data = 2;
        MwayTreeNode n3 = new MwayTreeNode();
        n3.data = 3;
        MwayTreeNode n4 = new MwayTreeNode();
        n4.data = 4;
        n1.nodes.add(n2);
        n1.nodes.add(n3);
        n1.nodes.add(n4);

        // Node 2 children
        MwayTreeNode n5 = new MwayTreeNode();
        n5.data = 5;
        MwayTreeNode n6 = new MwayTreeNode();
        n6.data = 6;
        MwayTreeNode n7 = new MwayTreeNode();
        n7.data = 7;
        n2.nodes.add(n5);
        n2.nodes.add(n6);
        n2.nodes.add(n7);

        // Node 3 children.
        MwayTreeNode n8 = new MwayTreeNode();
        n8.data = 8;
        MwayTreeNode n9 = new MwayTreeNode();
        n9.data = 9;
        n3.nodes.add(n8);
        n3.nodes.add(n9);

        // Ndoe 4 children.
        MwayTreeNode n10 = new MwayTreeNode();
        n10.data = 10;
        MwayTreeNode n11 = new MwayTreeNode();
        n11.data = 11;
        MwayTreeNode n12 = new MwayTreeNode();
        n12.data = 12;
        n4.nodes.add(n10);
        n4.nodes.add(n11);
        n4.nodes.add(n12);

        // Node 6 children.
        MwayTreeNode n13 = new MwayTreeNode();
        n13.data = 13;
        MwayTreeNode n14 = new MwayTreeNode();
        n14.data = 14;
        n6.nodes.add(n13);
        n6.nodes.add(n14);

        // Node 11 children.
        MwayTreeNode n15 = new MwayTreeNode();
        n15.data = 15;
        MwayTreeNode n16 = new MwayTreeNode();
        n16.data = 16;
        n11.nodes.add(n15);
        n11.nodes.add(n16);

        DFS_rec(tree.root);
        System.out.println("DFS recrusive ends");
        DFS_stack2(tree.root);
        DFS_stack1(tree.root);
        System.out.println("DFS non recursive ends");
        BFS_Queue2(tree.root);
        System.out.println("End BFS1");
        BFS_queue1(tree.root);
        System.out.println("End BFS2");
    }
}
