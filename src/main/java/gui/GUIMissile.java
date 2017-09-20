package gui;

import core.Missile;
import gui.shapes.MissileShape;

import java.awt.*;
import java.util.List;

public class GUIMissile extends GUIObject {

    static final long serialVersionUID = 2001;
    private static final MissileShape shape = new MissileShape();

    GUIMissile(Missile missile) {
        super(missile);
        rotateShapeArrays(0);
        initialize();
        fillColor = Color.gray;
        borderColor = Color.white;
    }

    @Override
    protected List<GUIPosition> getShape() {
        return shape.getMyShape();
    }

}
