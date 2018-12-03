//Ajwinder Singh
//IShape.java
//11/29/2018
package model;

import javafx.scene.canvas.GraphicsContext;

/**
 * This is an interface to be implemented by all the different shapes classes
 *
 * @author ajwinder
 * @version 1.0
 */
public interface IShape
{
    /**
     * Draws a shape using on the canvas
     * @param graphicsContext the graphic context
     */
    void drawShape(GraphicsContext graphicsContext);

    /**
     * Stores the shape to use later on
     */
    void addShape();

    /**
     * Sets the points while drawing
     * @param points coordinates
     */
    void setPoints(Double... points);
}
