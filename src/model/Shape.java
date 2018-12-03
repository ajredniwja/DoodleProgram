//Ajwinder Singh
//Shape.java
//11/28/2018

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a shape class that extends from the Observable class and it is used for the observers pattern.
 * @author ajwinder
 * @version 1.0
 */
public class Shape extends Observable
{
    private List<IShape> shapes;

    /**
     * Constructor
     */
    public Shape()
    {
        shapes = new ArrayList<>();
    }

    /**
     * Add shapes to the list
     * and notifies that the whenever a change happened
     * @param shape the shape added
     */
    public void addShape(IShape shape)
    {
        shapes.add(shape);
        notifyObservers(Change.ADD);
    }

    /**
     * Notifies the observers.
     */
    public void notifyShapes()
    {
        notifyObservers(Change.ADD);
    }

    @Override
    public String toString()
    {
        return "Shape{" +
                "shapes=" + shapes +
                '}';
    }

    //enum
    private enum Change
    {
        ADD
    }
}
