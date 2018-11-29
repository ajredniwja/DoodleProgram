package model;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

public interface IShape
{
    void drawShape(Pair<Double, Double> startPoint, Pair<Double, Double> endPoint, GraphicsContext graphicsContext, Canvas canvas);
    void addShape();
    void addAllShapesToCanvas(Pair<Double, Double> startPoint, Pair<Double, Double> endPoint);
}
