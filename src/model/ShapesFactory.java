//Ajwinder Singh
//ShapesFactory.java
//11/28/2018
package model;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * This is my shapes factory class which return the instance of a particular class for a given shape.
 *
 * @author ajwinder
 * @version 1.0
 */
public class ShapesFactory
{
    /**
     * Constructor
     * @param shape Name of shape
     * @param colors Colorof strike and fill
     * @param pointsList all the coordinates and the last point is whether the fill is selected or not
     * @return the instance of specific class
     */
    public IShape getInstance(String shape,Pair<Color, Color> colors, Double... pointsList)
    {
        switch (shape)
        {
            case "Line":
                return new LineClass(colors, pointsList);
            case ("Oval"):
                return new OvalClass(colors, pointsList);
            case ("Rectangle"):
                return new RectClass(colors, pointsList);
        }

        //else
        List<Pair<Double,Double>> listPoly = new ArrayList<>();
        return new PolylineClass(colors,listPoly,pointsList[4],pointsList[5]);
    }
}
