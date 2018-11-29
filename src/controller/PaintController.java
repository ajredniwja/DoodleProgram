package controller;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import model.*;
import view.DoodleView;

import java.util.List;

public class PaintController
{
    private Shape shape;
//    private LineClass lineClass = new LineClass();
    StoreShapes storeShapes = new StoreShapes();
    private OvalClass ovalClass;
    private ShapesFactory shapesFactory = new ShapesFactory();


    public PaintController(DoodleView view)
    {
        shape = new Shape();
        shape.addObserver(view);
    }

    public PaintController()
    {

    }

    public void addShape(IShape drawing)
    {
        shape.addShape(drawing);
    }

    public IShape getInstance(String shapeName)
    {
        return shapesFactory.getInstance(shapeName);
    }

    public List<Line> getLines()
    {
        return storeShapes.getLines();
    }
    public List<Ellipse> getOvals()
    {
        return storeShapes.getOvals();
    }

}
