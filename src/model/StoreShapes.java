//Ajwinder Singh
//StoreSHapes.java
//11/28/2018
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class has a list which is used to store shaoes. This class also helps with redo and undo function
 * @author ajwinder
 * @version 1.0
 *
 */
public class StoreShapes
{
    private static List<IShape> shapes = new Stack<>();

    private List<IShape> redo = new ArrayList<>();

    /**
     * Returns the list of the shapes
     * @return shapes list
     */
    public static List<IShape> getShapes()
    {
        return shapes;
    }

    /**
     * For undo action : removes the last drawn shape
     */
    public void undo()
    {
        if ((!getShapes().isEmpty()))
        {
            IShape shape = getShapes().remove(getShapes().size()-1);
            redo.add(shape);
        }
    }

    /**
     * Redoes the whatwver was deleted in the undo
     *
     */
    public void redo()
    {
        if (!redo.isEmpty())
        {
            IShape shape = redo.remove(redo.size()-1);
            getShapes().add(shape);
        }
    }

    /**
     * Clears all the shapes
     */
    public void clearShapes()
    {
        getShapes().clear();
    }

    @Override
    public String toString()
    {
        return "StoreShapes{" +
                "redo=" + redo +
                '}';
    }
}
