/*
 * StateOwner.java
 *
 * Created on: May 15, 2015
 *
 * Copyright (c) 2001-2015 Gamma Technologies, Inc. All Rights Reserved.
 */
package lambdas;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author Sanjeev Gupta
 *
 */
public class StateOwner
{
    StateChangeListener _listener;
    NoParameterInterface _noparameter;
    public StateOwner()
    {

    }

    void addStateChangeListener(StateChangeListener listener)
    {
        _listener = listener;
    }

    void addNoParameterObject(NoParameterInterface noparam)
    {
        _noparameter = noparam;
    }

    public void updatestate(int oldstate, int newstate)
    {
        _listener.stateChanged(oldstate, newstate);
        _noparameter.execute();
    }

    public static void main(String[] args)
    {
        StateOwner owner = new StateOwner();

        /// Java 7
        owner.addStateChangeListener(new StateChangeListener() {
            @Override
            public void stateChanged(int oldstate, int newstate)
            {
                System.out.println("Old state: " + oldstate + ", new state:" + newstate);
            }
        }
        );

        owner.addNoParameterObject(
            new NoParameterInterface() {
                @Override
                public void execute() {
                    System.out.println("Executed");
                }
            }
        );
        owner.updatestate(2, 3);

        // Java 8
        owner.addStateChangeListener((a,b) -> {System.out.println("old: " + a + ", new: " + b);});
        owner.addNoParameterObject(() -> {System.out.println("executed");});
        owner.updatestate(2, 3);

        NoParameterInterface noparam = () -> {System.out.println("executed");};
        owner.addNoParameterObject(noparam);

        // Thread example.
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread ran");
            }
        }).start();

        // Lambda expression
        new Thread( () -> {System.out.println("Thread started lamda");}).start();

        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach((n) -> {System.out.println(n);});

    }
}
