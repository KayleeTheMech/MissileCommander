package gui;

import core.UFO;

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
        this.direction = new GUIPosition(ufo.getTargetVector());
        this.centerOfMass = new GUIPosition(ufo.getPosition());
        int breite = 32 / 2;
        int hoehe = 16;
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
        this.x = xShape;
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
        this.y = yShape;

        rotateShapeArrays(Math.PI/2);

        moveShapeArrays();

        this.npoints = (y.length + x.length) / 2;
        this.xpoints = this.x;
        this.ypoints = this.y;

    }
}
