package model;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class OvalClass implements IShape
{
    Pair<Double, Double> start;
    Pair<Double, Double> end;
    List<Ellipse> ovalsList = new ArrayList<>();


    @Override
    public void drawShape(Pair<Double, Double> startPoint, Pair<Double, Double> endPoint, GraphicsContext graphicsContext, Canvas canvas)
    {
        graphicsContext.strokeOval(startPoint.getKey(), startPoint.getValue(),
                endPoint.getKey()-startPoint.getKey(), endPoint.getValue()-startPoint.getValue());
        start = startPoint;
        end = endPoint;
    }

    @Override
    public void addShape()
    {
        StoreShapes.ovals.add(new Ellipse(start.getKey(), start.getValue(), end.getKey()-start.getKey(), end.getValue()-start.getValue()));
    }

    @Override
    public void addAllShapesToCanvas(Pair<Double, Double> startPoint, Pair<Double, Double> endPoint)
    {

    }
}
