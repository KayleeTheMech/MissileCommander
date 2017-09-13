package gui;

import core.Explosion;

public class GUIExplosion extends AbstractGUIObject {
    final static long serialVersionUID = 2001;
    private int radius;

    GUIExplosion(Explosion explosion) {
        radius = explosion.getRange();
        centerOfMass = new GUIPosition(explosion.getPosition());

        x = getXShape();
        y = getYShape();

        moveShapeArrays();

        this.npoints = (x.length + y.length) / 2;
        this.xpoints = x;
        this.ypoints = y;
    }

    private void calculateShape() {
        // form erzeugen
        int anzahlpunkte = (int) (radius * 2 * Math.PI) / 4;
        double winkelabschnitt = 2 * Math.PI / anzahlpunkte;
        x = new int[anzahlpunkte];
        y = new int[anzahlpunkte];
        for (int i = 0; i < anzahlpunkte; i++) {
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
