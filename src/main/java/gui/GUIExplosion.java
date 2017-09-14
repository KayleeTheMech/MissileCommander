package gui;

import core.Explosion;

public class GUIExplosion extends AbstractGUIObject {
    final static long serialVersionUID = 2001;

    private double winkelabschnitt;

    GUIExplosion(Explosion explosion) {
        super(explosion);
        initialize();
    }

    private void calculateShape() {
        int radius = ((Explosion) this.gameObject).getRange();
        int numberOfPoints = (int) (radius * 2 * Math.PI) / 4;
        double winkelabschnitt = 2 * Math.PI / numberOfPoints;
        x = new int[numberOfPoints];
        y = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            double r = 1;
            if (i % 2 == 1) r = r * 1.2;
            x[i] = (int) (radius * r * Math.cos(i * winkelabschnitt));
            y[i] = (int) (radius * r * Math.sin(i * winkelabschnitt));
        }
    }

    protected int[] getXShape() {
        if (x == null) {
            calculateShape();
        }
        return x;
    }

    protected int[] getYShape() {
        if (y == null) {
            calculateShape();
        }
        return y;
    }
}
