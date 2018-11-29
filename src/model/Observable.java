package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable
{
    private Set<IObserver> observers;

    public Observable()
    {
        observers = new HashSet<>();
    }

    public void addObserver(IObserver observer)
    {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer)
    {
        observers.remove(observer);
    }

    //this method should be called in the child class
    //when something interesting happens
    public void notifyObservers(Object... args)
    {
        for (IObserver observer : observers)
        {
            observer.update(this, args);
        }
    }
}
