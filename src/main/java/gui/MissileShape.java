package gui;

import java.util.ArrayList;
import java.util.List;

public class MissileShape {
    private List<GUIPosition> myShape;

    public MissileShape() {
        final int length = 25;
        final int width = 10;

        myShape = new ArrayList<>();
        myShape.add(new GUIPosition(-(length * 1 / 2), +0));
        myShape.add(new GUIPosition(-(length * 3 / 8), -(width * 1 / 2)));
        myShape.add(new GUIPosition(-(length * 1 / 4), -(width * 1 / 4)));
        myShape.add(new GUIPosition(+(length * 3 / 8), -(width * 1 / 4)));
        myShape.add(new GUIPosition(+(length * 1 / 2), -(width * 1 / 2)));
        myShape.add(new GUIPosition(+(length * 1 / 2), (width * 1 / 2)));
        myShape.add(new GUIPosition(+(length * 3 / 8), (width * 1 / 4)));
        myShape.add(new GUIPosition(-(length * 1 / 4), (width * 1 / 4)));
        myShape.add(new GUIPosition(-(length / 2 * 3 / 4), (width * 1 / 2)));
    }

    public List<GUIPosition> getMyShape() {
        return myShape;
    }
}
