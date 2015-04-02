/*
 * MissingPositiveTest.java
 *
 * Created on: Apr 2, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package arrays.unitTests;

import static org.junit.Assert.assertTrue;
import arrays.FindMissingPositive;

import org.junit.Test;
/**
 * http://www.tutorialspoint.com/junit/junit_plug_with_eclipse.htm
 *
 * @author Sanjeev Gupta
 *
 */
public class MissingPositiveTest
{
    @Test
    public void testMissingPositive()
    {
        int[] arr = {2, 3, -7, 6, 8, 1, -10, 15};
        int missing = FindMissingPositive.missingNumberConstantSpace(arr);
        assertTrue("Not teh same", missing==4);
    }
}
