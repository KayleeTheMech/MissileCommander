package gui;

import java.awt.Polygon;

import core.UFO;

public class GUIUfo extends Polygon {
    static final long serialVersionUID = 2001;
    GUIPosition direction;
    GUIPosition centerofmass;

    GUIUfo(UFO ufo) {
        direction = new GUIPosition(ufo.getTargetVector());
        centerofmass = new GUIPosition(ufo.getPosition());
        int breite = 32 / 2;
        int hoehe = 16;
        int[] x = {
                +(int) ((double) 0 * breite),
                +(int) ((double) 2 / 16 * breite),
                +(int) ((double) 4 / 16 * breite),
                +(int) ((double) 14 / 16 * breite),
                +(int) ((double) 14 / 16 * breite),
                +(int) ((double) 15 / 16 * breite),
                +(int) ((double) 16 / 16 * breite),
                +(int) ((double) 8 / 16 * breite),
                +(int) ((double) 4 / 16 * breite),
                +(int) ((double) 2 / 16 * breite),
                -(int) ((double) 2 / 16 * breite),
                -(int) ((double) 4 / 16 * breite),
                -(int) ((double) 8 / 16 * breite),
                -(int) ((double) 16 / 16 * breite),
                -(int) ((double) 15 / 16 * breite),
                -(int) ((double) 14 / 16 * breite),
                -(int) ((double) 14 / 16 * breite),
                -(int) ((double) 4 / 16 * breite),
                -(int) ((double) 2 / 16 * breite)
        };
        int[] y = {
                +(int) ((double) 6 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe),
                +(int) ((double) 4 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 1 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                -(int) ((double) 5 / 16 * hoehe),
                -(int) ((double) 5 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                +(int) ((double) 0 / 16 * hoehe),
                +(int) ((double) 1 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 10 / 16 * hoehe),
                +(int) ((double) 4 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe),
                +(int) ((double) 2 / 16 * hoehe)
        };
        this.npoints = (y.length + x.length) / 2;
        // drehen

        double alpha = centerofmass.getDrehWinkel(direction) + Math.PI / 2;
        for (int i = 0; i < npoints; i++) {
            int xold = x[i];
            int yold = y[i];
            x[i] = (int) (xold * Math.cos(alpha) - yold * Math.sin(alpha));
            y[i] = -(int) (xold * Math.sin(alpha) + yold * Math.cos(alpha));
        }

        // relativ verschieben
        for (int i = 0; i < npoints; i++) {
            x[i] = centerofmass.getX() + x[i];
            y[i] = centerofmass.getY() + y[i];
        }
        this.xpoints = x;
        this.ypoints = y;

    }
}
