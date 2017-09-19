package gui;

import core.Base;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIBase extends GUIObject {

    static final long serialVersionUID = 2001;

    GUIBase(Base base) {
        super(base);

        initialize();
        fillColor = Color.white;
        borderColor = Color.white;
    }

    @Override
    protected List<GUIPosition> getShape() {
        final int width = 30;
        final int height = 20;

        List<GUIPosition> points = new ArrayList<>();

        points.add(new GUIPosition(-width, -height));
        points.add(new GUIPosition(-width, +0));
        points.add(new GUIPosition(+width, +0));
        points.add(new GUIPosition(+width, -height));
        points.add(new GUIPosition(+width / 2, -height / 2));
        points.add(new GUIPosition(-width / 2, -height / 2));

        return points;
    }
}
