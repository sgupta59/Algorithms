/*
 * AtomicReferenceTest.java
 *
 * Created on: Jul 1, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package threading;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class AtomicReferenceTest
{
    public static void main(String[] args)
    {
        String initialReference = "initial value referenced";

        AtomicReference<String> atomicStringReference =
            new AtomicReference<String>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);
    }
}
