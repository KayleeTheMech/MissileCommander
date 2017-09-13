package gui;

import core.Missile;

public class GUIMissile extends AbstractGUIObject {
    static final long serialVersionUID = 2001;

    private static final int laenge = 25;
    private static final int breite = 10;


    GUIMissile(Missile missile) {

        direction = new GUIPosition(missile.getTargetVector());
        centerOfMass = new GUIPosition(missile.getPosition());
        this.x=getXShape();
        this.y=getYShape();
        rotateShapeArrays(0);
        moveShapeArrays();
        this.xpoints = x;
        this.ypoints = y;
        this.npoints = 9;
    }

    int[] getXShape() {
        int[] xShape = {
                -(laenge * 1 / 2),
                -(laenge * 3 / 8),
                -(laenge * 1 / 4),
                +(laenge * 3 / 8),
                +(laenge * 1 / 2),
                +(laenge * 1 / 2),
                +(laenge * 3 / 8),
                -(laenge * 1 / 4),
                -(laenge / 2 * 3 / 4)
        };
        return xShape;
    }

    int[] getYShape() {
        int[] yShape = {
                +0,
                -(breite * 1 / 2),
                -(breite * 1 / 4),
                -(breite * 1 / 4),
                -(breite * 1 / 2),
                (breite * 1 / 2),
                (breite * 1 / 4),
                (breite * 1 / 4),
                (breite * 1 / 2)
        };
        return yShape;
    }
}
