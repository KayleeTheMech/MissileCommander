package gui;

import java.awt.Polygon;

public class GUIBase extends AbstractGUIObject {
    static final long serialVersionUID = 2001;
    private int xquer = 30;
    private int yhoch = 20;
    private int xstd;
    private int ystd;

    GUIBase(GUIPosition p) {
        super();
        this.xstd = p.getX();
        this.ystd = p.getY();
        int[] x = {
                xstd - xquer,
                xstd - xquer,
                xstd + xquer,
                xstd + xquer,
                xstd + xquer / 2,
                xstd - xquer / 2
        };
        int[] y = {
                ystd - yhoch,
                ystd + 0,
                ystd + 0,
                ystd - yhoch,
                ystd - yhoch / 2,
                ystd - yhoch / 2
        };

        this.npoints = 6;
        this.xpoints = x;
        this.ypoints = y;
    }
}
