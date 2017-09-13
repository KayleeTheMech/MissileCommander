package gui;

import core.Missile;

public class GUIMissile extends AbstractGUIObject {
    static final long serialVersionUID = 2001;

    int laenge = 25;
    int breite = 10;


    GUIMissile(Missile missile) {

        direction = new GUIPosition(missile.getTargetVector());
        centerOfMass = new GUIPosition(missile.getPosition());
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
        this.x=xShape;
        this.y=yShape;

        rotateShapeArrays(0);
        moveShapeArrays();
        this.xpoints = x;
        this.ypoints = y;
        this.npoints = 9;
    }
}
