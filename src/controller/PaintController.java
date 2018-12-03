//Ajwinder Singh
//PaintController.java
//11/28/2018

package controller;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import model.*;
import view.DoodleView;
import java.util.List;

/**
 * This is the controller class it only interacts with Facade class (Use of facade pattern)
 * @author ajwinder
 * @version 1.0
 */
public class PaintController
{
    private Facade facade;

    /**
     * Constructor
     * @param view doodle view
     */
    public PaintController(DoodleView view)
    {
        facade = new Facade(view);
    }

    /**
     * Add to observe the new shape
     * @param drawing the drawn shape
     */
    public void addShape(IShape drawing)
    {
        facade.addShape(drawing);
    }

    /**
     * Notify that a change is made so update
     */
    public void notifyShapes()
    {
        facade.notifyShapes();
    }

    /**
     * Gets the instance of the selected shape
     * @param shapeName name of the shape
     * @param colors stroke and fill color
     * @param points all cooridnates and whether fill was selected or not
     * @return instance of the shape selected
     */
    public IShape getInstance(String shapeName, Pair<Color, Color> colors, Double... points)
    {
        return facade.getInstance(shapeName,colors,points);
    }

    /**
     * Gets all the shapes already drawn.
     * @return list of all the shapes already drawn
     */
    public List<IShape> getShapes()
    {
        return facade.getShapes();
    }

    /**
     * Undo the last drawn shape
     */
    public void undo()
    {
        facade.undo();
    }

    /**
     * Redo the undone shape
     */
    public void redo()
    {
        facade.redo();
    }

    /**
     * Clear all the shapes drawn
     */
    public void clearShapes()
    {
        facade.clearShapes();
    }

    @Override
    public String toString()
    {
        return "PaintController{" +
                "facade=" + facade +
                '}';
    }
}
