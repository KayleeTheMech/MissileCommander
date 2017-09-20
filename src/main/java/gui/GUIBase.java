package gui;

import core.Base;
import gui.shapes.BaseShape;

import java.awt.*;
import java.util.List;

public class GUIBase extends GUIObject {

    static final long serialVersionUID = 2001;
    private static final BaseShape baseShape = new BaseShape();


    GUIBase(Base base) {
        super(base);

        initialize();
        fillColor = Color.white;
        borderColor = Color.white;
    }

    @Override
    protected List<GUIPosition> getShape() {
        return baseShape.getMyShape();
    }
}
