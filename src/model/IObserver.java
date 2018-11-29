package model;

public interface IObserver
{
    //observable is the object we are observing
    void update(Observable observable, Object... args);
}
