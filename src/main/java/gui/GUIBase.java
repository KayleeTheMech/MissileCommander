package gui;

import Util.ArrayUtil;
import core.Base;

import java.awt.*;
import java.util.List;

public class GUIBase extends GUIObject {
    static final long serialVersionUID = 2001;

    GUIBase(Base base) {
        super(base);
        initialize();
        fillColor= Color.white;
        borderColor=Color.white;
    }

    protected List<GUIPosition> getShape() {
        final int xquer = 30;
        final int yhoch = 20;

        int[] xShape = {
                -xquer,
                -xquer,
                +xquer,
                +xquer,
                +xquer / 2,
                -xquer / 2
        };
        int[] yShape = {
                -yhoch,
                +0,
                +0,
                -yhoch,
                -yhoch / 2,
                -yhoch / 2
        };
        return ArrayUtil.getListFromArrays(xShape, yShape);
    }
}
