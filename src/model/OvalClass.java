//Ajwinder Singh
//OvalClass.java
//11/28/2018

package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * Class to draw ovals
 * @author ajwinder
 * @version 1.0
 */
public class OvalClass implements IShape
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
     * COntructor to set the coordinates
     * @param points coordinates
     * @param strokeAndFill the stroke and fill color
     */
    public OvalClass(Pair<Color,Color> strokeAndFill, Double... points)
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
        double xCord = Math.min(pointX,width);
        double yCord = Math.min(pointY,height);
        double newHeight = Math.max(pointY,height)-Math.min(pointY,height);
        double newWidth = Math.max(pointX,width)-(Math.min(pointX,width));

        if (fillMarker == 1.0)
        {
            graphicsContext.setFill(fillColor);
            graphicsContext.fillOval(xCord, yCord, newWidth, newHeight);
        }

        graphicsContext.setStroke(strokeColor);
        graphicsContext.setLineWidth(strokeWidth);

        graphicsContext.strokeOval(xCord, yCord, newWidth, newHeight);
    }

    @Override
    public void addShape()
    {
        StoreShapes.getShapes().add(new OvalClass(new Pair<>(strokeColor,fillColor),pointX,pointY,width,height,strokeWidth, fillMarker));
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
        return "OvalClass{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
