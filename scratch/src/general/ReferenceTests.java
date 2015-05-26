/*
 * ReferenceTests.java
 *
 * Created on: Jan 21, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package general;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 *
 *
 * @author Sebastian Cardenas
 *
 */
public class ReferenceTests
{
    public static void main(String args[])
    {
        phantomReferenceTest();
        strongReferenceExample();
        weakReferenceExample();
        weakReferenceMapExample();

     }

    public static void strongReferenceExample()
    {
        HashMap<Employee,EmployeeVal> aMap = new HashMap<Employee,EmployeeVal>();
         ReferenceTests r1 = new ReferenceTests();
        EmployeeVal val = r1.new EmployeeVal("Developer");
        Employee emp = r1.new Employee("SARAL");

        EmployeeVal val1 = r1.new EmployeeVal("Developer");
        Employee emp1 = r1.new Employee("SARAL");

        aMap.put(emp, val);

        emp1=null;
        emp = null;

        System.gc();
        System.out.println("Size of strong map: " + aMap.size());
        System.gc();
        System.out.println("Size of strong map: " + aMap.size());
    }

    public static void weakReferenceMapExample()
    {

        HashMap<Employee,EmployeeVal> aMap = new HashMap<Employee,EmployeeVal>();
        WeakHashMap<Employee,EmployeeVal> weakMap = new WeakHashMap<Employee,EmployeeVal>();


        ReferenceTests r1 = new ReferenceTests();
        EmployeeVal val = r1.new EmployeeVal("Developer");
        Employee emp = r1.new Employee("SARAL");

        EmployeeVal val1 = r1.new EmployeeVal("Developer");
        Employee emp1 = r1.new Employee("SARAL");

        aMap.put(emp, val);
        weakMap.put(emp1, val1);
        emp1=null;
        emp = null;

        System.gc();

        System.out.println("Size of weak map:  " + weakMap.size());
        System.out.println("Size of strong map: " + aMap.size());
        System.gc();

        System.out.println("Size of weak map:  " + weakMap.size());
        System.out.println("Size of strong map: " + aMap.size());
    }
    public static void weakReferenceExample()
    {
        ReferenceTests r1 = new ReferenceTests();
        ReferenceQueue rq1=new ReferenceQueue();
        /** Weak Reference Example 1 */
        EmployeeVal val2 = r1.new EmployeeVal("Developer");
        EmployeeVal v2_1 = null;

        WeakReference<EmployeeVal> empref = new WeakReference<EmployeeVal>(val2 /**r1.new EmployeeVal("Developer1")*/,rq1);
        for (int idx = 0; idx < 100; ++idx)
        {
            System.gc();
            EmployeeVal v2_2 = empref.get();
            Reference emp1 = rq1.poll();
            if (emp1 != null)
                System.out.println("Object not null");
            else
                break;
            if (idx == 10)
                val2 = null;
            v2_2 = null;
        }
        System.gc();
        val2 = null;
        System.gc();
        v2_1 = empref.get();
        System.gc();
        HashMap<Employee,EmployeeVal> aMap = new HashMap<Employee,EmployeeVal>();
        ReferenceQueue rq=new ReferenceQueue();
        WeakReference<HashMap<Employee, EmployeeVal>> weakRefMap = new WeakReference<HashMap<Employee, EmployeeVal>> ( aMap,rq);

        EmployeeVal val = r1.new EmployeeVal("Developer");
        Employee emp = r1.new Employee("SARAL");

        EmployeeVal val1 = r1.new EmployeeVal("Developer");
        Employee emp1 = r1.new Employee("SARAL");

        aMap.put(emp, val);
        aMap.put(emp1, val1);
        aMap=null;
        System.gc();
        HashMap<Employee, EmployeeVal> testmap = weakRefMap.get();
        Reference ref = rq.poll();
        if (aMap != null)
            System.out.println("Size of strong map: " + aMap.size());
    }
    public static void phantomReferenceTest()
    {
        ReferenceTests r1 = new ReferenceTests();
        EmployeeVal emp = r1.new EmployeeVal("Test");
        ReferenceQueue q=new ReferenceQueue();
        PhantomReference<ReferenceTests.EmployeeVal> empref = new PhantomReference<ReferenceTests.EmployeeVal>(emp, q);
        EmployeeVal emp1 = empref.get();
        System.gc();
        EmployeeVal emp2 = empref.get();
        emp = null;
        System.gc();
        EmployeeVal emp3 = empref.get();
        System.gc();

    }
    class EmployeeVal {
        String _value;
        EmployeeVal(String val)
        {
            _value = val;
        }
        @Override
        protected void finalize() throws IOException {
            try{
                System.out.println("Finalize of Sub Class: " + _value);
                //release resources, perform cleanup ;
            }catch(Throwable t){
                throw t;
            }finally{
                System.out.println("Calling finalize of Super Class");
                try
                {
                    super.finalize();
                }
                catch (Throwable e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        }
    }
    public class Employee {
        String _name;
        public Employee(String s)
        {
            _name = s;
        }
    }

}
