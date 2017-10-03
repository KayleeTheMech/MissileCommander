package gui.gameElements;

import core.gameObjects.Base;
import gui.gameElements.shapes.BaseShape;

import java.awt.*;
import java.util.List;

public class GuiBase extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final BaseShape baseShape = new BaseShape();


    GuiBase(Base base) {
        super(base);

        initialize();
        fillColor = Color.white;
        borderColor = Color.white;
    }

    @Override
    protected List<GuiPosition> getShape() {
        return baseShape.getMyShape();
    }
}
