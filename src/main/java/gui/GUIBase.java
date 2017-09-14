package gui;

import core.Base;

public class GUIBase extends AbstractGUIObject {
    static final long serialVersionUID = 2001;
    private static final int xquer = 30;
    private static final int yhoch = 20;

    GUIBase(Base base) {
        super(base);

        this.npoints = (x.length+y.length)/2;
        this.xpoints = x;
        this.ypoints = y;
    }

    int[] getXShape() {
        int center = this.centerOfMass.getX();
        int[] xShape = {
                center - xquer,
                center - xquer,
                center + xquer,
                center + xquer,
                center + xquer / 2,
                center - xquer / 2
        };
        return xShape;
    }

    int[] getYShape() {
        int center = this.centerOfMass.getY();
        int[] yShape = {
                center - yhoch,
                center + 0,
                center + 0,
                center - yhoch,
                center - yhoch / 2,
                center - yhoch / 2
        };
        return yShape;
    }
}
