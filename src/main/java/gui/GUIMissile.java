package gui;

import Util.ArrayUtil;
import core.Missile;

import java.awt.*;
import java.util.List;

public class GUIMissile extends AbstractGUIObject {
    static final long serialVersionUID = 2001;

    GUIMissile(Missile missile) {
        super(missile);
        rotateShapeArrays(0);
        initialize();
        fillColor = Color.gray;
        borderColor = Color.white;
    }

    protected List<GUIPosition> getShape() {
        final int laenge = 25;
        final int breite = 10;

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
