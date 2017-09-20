package gui.shapes;

import gui.GUIPosition;

import java.util.List;

public abstract class AbstractShape {
    protected List<GUIPosition> myShape;


    public List<GUIPosition> getMyShape() {
        return myShape;
    }
}
