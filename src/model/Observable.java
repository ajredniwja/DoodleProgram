package model;

import java.util.HashSet;
import java.util.Set;

/**
 * This is observable class which is extended by shape class.
 * Used to make a class observable to see its changes
 *
 * @author ajwinder
 * @version 1.0
 */
public abstract class Observable
{
    private Set<IObserver> observers;

    /**
     * Constructor creates a hashset to store observers
     */
    public Observable()
    {
        observers = new HashSet<>();
    }

    /**
     * adds observer to the list
     * @param observer observer to be observed
     */
    public void addObserver(IObserver observer)
    {
        observers.add(observer);
    }

    //this method should be called in the child class
    //when something interesting happens

    /**
     * Notify is there is a change and runs update method then
     * @param args args
     */
    public void notifyObservers(Object... args)
    {
        for (IObserver observer : observers)
        {
            observer.update(this, args);
        }
    }

    @Override
    public String toString()
    {
        return "Observable{" +
                "observers=" + observers +
                '}';
    }
}
