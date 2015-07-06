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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * http://www.tutorialspoint.com/junit/junit_plug_with_eclipse.htm
 *
 * @author Sanjeev Gupta
 *
 */
public class MissingPositiveTest
{
	int[] arr;
	@Before
	public void setup() throws Exception
	{
		int[] a  = {2, 3, -7, 6, 8, 1, -10, 15};
		arr = a;
	}
	
	@After
	public void tearDown() throws Exception
	{
		
	}
    @Test
    public void testMissingPositive()
    {
        
        int missing = FindMissingPositive.missingNumberConstantSpace(arr);
        assertTrue("Not teh same", missing==4);
    }
}
