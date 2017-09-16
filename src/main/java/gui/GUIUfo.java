package gui;

import Util.ArrayUtil;
import core.UFO;

import java.util.List;

/**
 * This class manually defines the shape of an enemy UFO.
 * It further rotates and moves this values to fit the object the shape shall represent.
 */
public class GUIUfo extends AbstractGUIObject {
    static final long serialVersionUID = 2001;

    /**
     * This constructor takes an UFO Object and re-calculates the hard coded array into the position on screen.
     *
     * @param ufo
     */
    GUIUfo(UFO ufo) {
        super(ufo);
        rotateShapeArrays(Math.PI / 2);
        initialize();
    }

    protected List<GUIPosition> getShape() {
        final int breite = 16;
        final int hoehe = 16;

        int[] xShape = {
                +(int) ((double) 0 * breite),
                +(int) ((double) 2 / 16 * breite),
                +(int) ((double) 4 / 16 * breite),
                +(int) ((double) 14 / 16 * breite),
                +(int) ((double) 14 / 16 * breite),
                +(int) ((double) 15 / 16 * breite),
                +(int) ((double) 16 / 16 * breite),
                +(int) ((double) 8 / 16 * breite),
                +(int) ((double) 4 / 16 * breite),
                +(int) ((double) 2 / 16 * breite),
                -(int) ((double) 2 / 16 * breite),
                -(int) ((double) 4 / 16 * breite),
                -(int) ((double) 8 / 16 * breite),
                -(int) ((double) 16 / 16 * breite),
                -(int) ((double) 15 / 16 * breite),
                -(int) ((double) 14 / 16 * breite),
                -(int) ((double) 14 / 16 * breite),
                -(int) ((double) 4 / 16 * breite),
                -(int) ((double) 2 / 16 * breite)
        };
        int[] yShape = {
                +(int) ((double) 6 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe),
                +(int) ((double) 4 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 1 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                -(int) ((double) 5 / 16 * hoehe),
                -(int) ((double) 5 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                +(int) ((double) 1 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 4 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe)
        };
        return ArrayUtil.getListFromArrays(xShape, yShape);
    }
}
