package gui;

import core.UFO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manually defines the shape of an enemy UFO. It further rotates and moves this values to fit the object the shape
 * shall represent.
 */
public class GuiUfo extends GUIObject {

    static final long serialVersionUID = 2001;

    /**
     * This constructor takes an UFO Object and re-calculates the hard coded array into the position on screen.
     *
     * @param ufo
     */
    GuiUfo(UFO ufo) {
        super(ufo);
        rotateShapeArrays(Math.PI / 2);
        initialize();
        fillColor = Color.green;
        borderColor = Color.yellow;

    }

    @Override
    protected List<GUIPosition> getShape() {
        final int breite = 16;
        final int hoehe = 16;

        List<GUIPosition> points = new ArrayList<>();
        points.add(new GUIPosition(+(int) ((double) 0 * breite), +(int) ((double) 6 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 2 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 4 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 14 / 16 * breite), +(int) ((double) 4 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 14 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 15 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 16 / 16 * breite), +(int) ((double) 1 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 8 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 4 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        points.add(new GUIPosition(+(int) ((double) 2 / 16 * breite), -(int) ((double) 5 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 2 / 16 * breite), -(int) ((double) 5 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 4 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 8 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 16 / 16 * breite), +(int) ((double) 1 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 15 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 14 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 14 / 16 * breite), +(int) ((double) 4 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 4 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        points.add(new GUIPosition(-(int) ((double) 2 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));

        return points;
    }
}
