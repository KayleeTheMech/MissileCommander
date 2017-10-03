package gui.gameElements;

import core.gameObjects.Missile;
import gui.gameElements.shapes.MissileShape;

import java.awt.*;
import java.util.List;

public class GuiMissile extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final MissileShape shape = new MissileShape();

    GuiMissile(Missile missile) {
        super(missile);
        rotateShapeArrays(0);
        initialize();
        fillColor = Color.gray;
        borderColor = Color.white;
    }

    @Override
    protected List<GuiPosition> getShape() {
        return shape.getMyShape();
    }

}
