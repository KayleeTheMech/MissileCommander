package gui;

import core.Explosion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIExplosion extends GUIObject {
    final static long serialVersionUID = 2001;

    GUIExplosion(Explosion explosion) {
        super(explosion);
        initialize();
        fillColor = Color.yellow;
        borderColor = Color.red;
    }

    public List<GUIPosition> getShape() {
        int radius = ((Explosion) this.gameObject).getRange();
        int numberOfPoints = (int) (radius * 2 * Math.PI) / 4;
        double winkelabschnitt = 2 * Math.PI / numberOfPoints;
        List<GUIPosition> points = new ArrayList<GUIPosition>();
        for (int i = 0; i < numberOfPoints; i++) {
            double r = 1;
            if (i % 2 == 1) r = r * 1.2;
            int x = (int) (radius * r * Math.cos(i * winkelabschnitt));
            int y = (int) (radius * r * Math.sin(i * winkelabschnitt));
            points.add(new GUIPosition(x, y));
        }
        return points;
    }
}
