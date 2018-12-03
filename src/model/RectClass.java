//Ajwinder Singh
//RectClass.java
//11/28/2018
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * Class to draw a rectangle
 * @author ajwinder
 * @version 1.0
 */
public class RectClass implements IShape
{
    //fields
    private Double pointX;
    private Double pointY;
    private Double width;
    private Double height;
    private Color strokeColor;
    private Color fillColor;
    private double strokeWidth;
    private double fillMarker;


    /**
     * Constructor to set the coordinates
     * @param points coordinates
     * @param strokeAndFill the stroke and fill color
     */
    public RectClass(Pair<Color,Color> strokeAndFill, Double... points)
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
    public String toString()
    {
        return "RectClass{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext)
    {
        //minimum of x1,width makes the x point
        //minimum of y1,height makes the y point
        //max of x1,width - x makes the width
        //max of x2,height - y makes the width
        double xCord = Math.min(pointX,width);
        double yCord = Math.min(pointY,height);
        double newHeight = Math.max(pointY,height)-Math.min(pointY,height);
        double newWidth = Math.max(pointX,width)-(Math.min(pointX,width));

        if (fillMarker == 1.0)
        {
            graphicsContext.setFill(fillColor);
            graphicsContext.fillRect(xCord, yCord, newWidth, newHeight);
        }
        graphicsContext.setStroke(strokeColor);
        graphicsContext.setLineWidth(strokeWidth);

        graphicsContext.strokeRect(xCord, yCord, newWidth, newHeight);
    }

    @Override
    public void addShape()
    {
        StoreShapes.getShapes().add(new RectClass(new Pair<>(strokeColor,fillColor),
                pointX,pointY,width,height,strokeWidth, fillMarker));
    }

    @Override
    public void setPoints(Double... points)
    {
        this.width = points[0];
        this.height = points[1];
    }
}
