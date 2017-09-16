package gui;

import Util.ArrayUtil;
import core.Missile;

import java.util.ArrayList;
import java.util.List;

public class GUIMissile extends AbstractGUIObject {
    static final long serialVersionUID = 2001;

    private static final int laenge = 25;
    private static final int breite = 10;


    GUIMissile(Missile missile) {
        super(missile);
        rotateShapeArrays(0);
        initialize();
    }

    protected List<GUIPosition> getShape() {
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
        return ArrayUtil.getListFromArrays(xShape, yShape);
    }


}
