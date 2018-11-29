package model;

import controller.PaintController;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class LineClass implements IShape
{
    Pair<Double, Double> start;
    Pair<Double, Double> end;

    @Override
    public void drawShape(Pair<Double, Double> startPoint, Pair<Double, Double> endPoint, GraphicsContext graphicsContext, Canvas canvas)
    {
        graphicsContext.strokeLine(startPoint.getKey(), startPoint.getValue(), endPoint.getKey(), endPoint.getValue());
        start = startPoint;
        end = endPoint;
    }

    @Override
    public void addShape()
    {
        StoreShapes.lines.add(new Line(start.getKey(), start.getValue(), end.getKey(), end.getValue()));
    }

    @Override
    public void addAllShapesToCanvas(Pair<Double, Double> startPoint, Pair<Double, Double> endPoint)
    {
//        for (Line lines : linesList)
//        {
//            gContext.strokeLine(lines.getStartX(),lines.getStartY(),lines.getEndX(),lines.getEndY());
//        }
    }
}
