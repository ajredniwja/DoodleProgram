package model;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Shape extends Observable
{
    private List<IShape> shapes;

    public Shape()
    {
        shapes = new ArrayList<>();
    }

    public void addShape(IShape shape)
    {
        shapes.add(shape);
        notifyObservers(Change.ADD);
    }

    public void notifyShapes()
    {
        notifyObservers(Change.ADD);
    }

    public enum Change
    {
        ADD,
        REMOVE,
        UPDATE,
        RETRIEVE
    }
}
