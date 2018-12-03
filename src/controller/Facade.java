//Ajwinder Singh
//Facade.java
//11/28/2018

package controller;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import model.IShape;
import model.Shape;
import model.ShapesFactory;
import model.StoreShapes;
import view.DoodleView;

import java.util.List;

/**
 * This is the class to use the Facade pattern.
 * This interacts with al the classes and gives a controller the liberty to only interact with one single class.
 *
 * @author ajwinder
 * @version 1.0
 */
public class Facade
{
    private Shape shape;
    private StoreShapes storeShapes;
    private ShapesFactory shapesFactory;

    /**
     * Constructor
     * @param view the doodle view class
     */
    public Facade(DoodleView view)
    {
        shape = new Shape();
        shape.addObserver(view);

        shapesFactory = new ShapesFactory();
        storeShapes = new StoreShapes();
    }

    /**
     * Add the newly drawn shape to observe
     * @param drawing the shape drawn
     */
    public void addShape(IShape drawing)
    {
        shape.addShape(drawing);
    }

    /**
     * Notify the shapes that a new shape has been added, t=run the update
     */
    public void notifyShapes()
    {
        shape.notifyShapes();
    }

    /**
     * Gets the instance using factory pattern from the storeFactory class
     * @param shapeName shape name
     * @param colors color of stroke and fill
     * @param points all the cooridnates and whether fill is true or not
     * @return the instance of class selected
     */
    public IShape getInstance(String shapeName, Pair<Color,Color> colors, Double... points)
    {
        return shapesFactory.getInstance(shapeName,colors,points);
    }

    /**
     * Gets the all previously drawn shapes
     * @return all the shapes
     */
    public List<IShape> getShapes()
    {
        return storeShapes.getShapes();
    }

    /**
     * Perfors the undo operation
     */
    public void undo()
    {
        storeShapes.undo();
    }

    /**
     * Perform redo operation
     */
    public void redo()
    {
        storeShapes.redo();
    }

    /**
     * Clear all shapes
     */
    public void clearShapes()
    {
        storeShapes.clearShapes();
    }

    @Override
    public String toString()
    {
        return "Facade{" +
                "shape=" + shape +
                ", storeShapes=" + storeShapes +
                ", shapesFactory=" + shapesFactory +
                '}';
    }
}
