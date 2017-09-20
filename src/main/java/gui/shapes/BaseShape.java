package gui.shapes;

import gui.GUIPosition;

import java.util.ArrayList;

public class BaseShape extends AbstractShape {
    public BaseShape() {
        myShape = new ArrayList<>();
        final int width = 30;
        final int height = 20;
        myShape.add(new GUIPosition(-width, -height));
        myShape.add(new GUIPosition(-width, +0));
        myShape.add(new GUIPosition(+width, +0));
        myShape.add(new GUIPosition(+width, -height));
        myShape.add(new GUIPosition(+width / 2, -height / 2));
        myShape.add(new GUIPosition(-width / 2, -height / 2));

    }
}
