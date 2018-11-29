package model;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class StoreShapes
{
    static List<Line> lines = new ArrayList<>();
    static List<Ellipse> ovals = new ArrayList<>();

    public List<Line> getLines()
    {
        return lines;
    }

    public static List<Ellipse> getOvals()
    {
        return ovals;
    }
}
