/*
 * MwayTreeNode.java
 *
 * Created on: Mar 3, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package Trees;

import java.util.ArrayList;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class MwayTreeNode
{
    int data;
    boolean visited = false;
    ArrayList<MwayTreeNode> nodes = new ArrayList<MwayTreeNode>();
    public MwayTreeNode()
    {

    }
    @Override
    public String toString()
    {
        return Integer.toString(data);
    }

}
