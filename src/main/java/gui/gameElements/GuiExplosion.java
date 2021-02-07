package gui.gameElements;

import core.gameObjects.Explosion;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiExplosion extends GuiObject {

  static final long serialVersionUID = 2001;

  GuiExplosion(Explosion explosion) {
    super(explosion);
    initialize();
    fillColor = Color.yellow;
    borderColor = Color.red;
  }

  @Override
  public List<GuiPosition> getShape() {
    // fixme SCALE radius to resolution
    int radius = this.gameObject.getDetonationRadius();
    int numberOfPoints = (int) (radius * 2 * Math.PI) / 4;
    double winkelabschnitt = 2 * Math.PI / numberOfPoints;

    List<GuiPosition> points = new ArrayList<>();
    for (int i = 0; i < numberOfPoints; i++) {
      double r = 1;
      if (i % 2 != 0) {
        r = r * 1.2;
      }
      int x = (int) (radius * r * Math.cos(i * winkelabschnitt));
      int y = (int) (radius * r * Math.sin(i * winkelabschnitt));
      points.add(new GuiPosition(x, y));
    }
    return points;
  }
}
