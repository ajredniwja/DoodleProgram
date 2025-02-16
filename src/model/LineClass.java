//Ajwinder Singh
//LineClass.java
//11/28/2018
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * This is the line class to draw a line.
 *
 * @author ajwinder
 * @version 1.0
 */
public class LineClass implements IShape
{

    private Double pointX;
    private Double pointY;
    private Double width;
    private Double height;
    private Color strokeColor;
    private Color fillColor;
    private double strokeWidth;
    private double fillMarker;

    /**
     * This is a contructor
     * @param points coordinates
     * @param strokeAndFill the stroke and fill color
     */
    public LineClass(Pair<Color,Color> strokeAndFill, Double... points)
    {
        this.pointX = points[0];
        this.pointY = points[1];
        this.width = points[2];
        this.height = points[3];
        this.strokeWidth = points[4];
        this.fillMarker = points[5];

        this.strokeColor = strokeAndFill.getKey();
        this.fillColor = strokeAndFill.getValue();
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext)
    {
        if (fillMarker == 1.0)
        {
            graphicsContext.setFill(fillColor);
        }
        graphicsContext.setStroke(strokeColor);
        graphicsContext.setLineWidth(strokeWidth);

        graphicsContext.strokeLine(pointX, pointY, width, height);
    }

    @Override
    public void addShape()
    {
        StoreShapes.getShapes().add(new LineClass(new Pair<>(strokeColor,fillColor),pointX,pointY,width,height,strokeWidth,fillMarker));
    }

    @Override
    public void setPoints(Double... points)
    {
        this.width = points[0];
        this.height = points[1];
    }

    @Override
    public String toString()
    {
        return "LineClass{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
