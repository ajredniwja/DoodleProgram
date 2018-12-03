//Ajwinder Singh
//PolylineClass.java
//11/28/2018

package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Used to draw a polyline
 * @author ajwinder
 * @version 1.0
 */
public class PolylineClass implements IShape
{
    private double[] xPoints;
    private double[] yPoints;
    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;
    private List<Pair<Double,Double>> coordinates;
    private double fillMarker;

    /**
     * Constructor to set the array of x and y coordinates
     * @param polyPoints the array of pairs x and y
     * @param strokeAndFill the stroke and fill color
     */
    public PolylineClass(Pair<Color,Color> strokeAndFill, List<Pair<Double, Double>> polyPoints, double... strokeAndFillData)
    {
        this.coordinates = polyPoints;
        this.strokeColor = strokeAndFill.getKey();
        this.fillColor = strokeAndFill.getValue();
        this.strokeWidth = strokeAndFillData[0];
        this.fillMarker = strokeAndFillData[1];
    }

    @Override
    public void drawShape(GraphicsContext graphicsContext)
    {
        //create two arrays of the size of the cordinates array
        xPoints = new double[coordinates.size()];
        yPoints = new double[coordinates.size()];

        //copy drom arraylist to normal double[]
        for (int i = 0; i < coordinates.size(); i++)
        {
            xPoints[i] = coordinates.get(i).getKey();
            yPoints[i] = coordinates.get(i).getValue();
        }

        graphicsContext.setStroke(strokeColor);
        graphicsContext.setLineWidth(strokeWidth);
        if (fillMarker == 1.0)
        {
            graphicsContext.setFill(fillColor);
            graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
        }

        //draw using the array
        graphicsContext.strokePolyline(xPoints,yPoints, xPoints.length);
    }

    @Override
    public void addShape()
    {
        StoreShapes.getShapes().add(new PolylineClass(new Pair<>(strokeColor,fillColor), coordinates, strokeWidth,fillMarker));
    }

    @Override
    public void setPoints(Double... points)
    {
        this.coordinates.add(new Pair<>(points[0],points[1]));
    }

    @Override
    public String toString()
    {
        return "PolylineClass{" +
                "xPoints=" + Arrays.toString(xPoints) +
                ", yPoints=" + Arrays.toString(yPoints) +
                ", polyPoints=" + coordinates +
                '}';
    }
}
