package gui;

import java.awt.Polygon;

public class GUIBase extends AbstractGUIObject {
    static final long serialVersionUID = 2001;
    private static final int xquer = 30;
    private static final int yhoch = 20;
    private int xstd;
    private int ystd;

    GUIBase(GUIPosition p) {
        super();
        this.xstd = p.getX();
        this.ystd = p.getY();
        this.x = getXShape();
        this.y = getYShape();
        this.npoints = 6;
        this.xpoints = x;
        this.ypoints = y;
    }

    int[] getXShape() {
        int[] xShape = {
                xstd - xquer,
                xstd - xquer,
                xstd + xquer,
                xstd + xquer,
                xstd + xquer / 2,
                xstd - xquer / 2
        };
        return xShape;
    }

    int[] getYShape() {
        int[] yShape = {
                ystd - yhoch,
                ystd + 0,
                ystd + 0,
                ystd - yhoch,
                ystd - yhoch / 2,
                ystd - yhoch / 2
        };
        return yShape;
    }
}
