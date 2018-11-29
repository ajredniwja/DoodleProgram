package model;

public class ShapesFactory
{
    public IShape getInstance(String shape)
    {
        if (shape.equals("Line"))
        {
            return new LineClass();
        }
        else if (shape.equals(("Oval")))
        {
            return new OvalClass();
        }
        return new LineClass();
    }
}
