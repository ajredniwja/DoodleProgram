//Ajwinder Singh
//IObserver.java
//11/28/2018

package model;

/**
 * This is an interface that has update method to update when a change happend
 *
 * @author ajwinder
 * @version 1.0
 */
public interface IObserver
{
    /**
     * observable is the object we are observing.
     * Updates when a change has happend
     * @param observable the object we are observing
     * @param args args
     */
    void update(Observable observable, Object... args);
}
