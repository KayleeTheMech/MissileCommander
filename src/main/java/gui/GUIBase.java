package gui;

import core.Base;

public class GUIBase extends AbstractGUIObject {
    static final long serialVersionUID = 2001;
    private static final int xquer = 30;
    private static final int yhoch = 20;

    GUIBase(Base base) {
        super(base);
        initialize();
    }

    int[] getXShape() {
        int[] xShape = {
                -xquer,
                -xquer,
                +xquer,
                +xquer,
                +xquer / 2,
                -xquer / 2
        };
        return xShape;
    }

    int[] getYShape() {
        int[] yShape = {
                -yhoch,
                +0,
                +0,
                -yhoch,
                -yhoch / 2,
                -yhoch / 2
        };
        return yShape;
    }
}
