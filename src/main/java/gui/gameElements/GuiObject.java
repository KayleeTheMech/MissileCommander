package gui.gameElements;

import core.gameObjects.FlightObject;
import core.gameObjects.GameObject;
import java.awt.*;
import java.util.List;

public abstract class GuiObject extends Polygon {

  private static final long serialVersionUID = -6454463921677482148L;

  Color fillColor = null;
  Color borderColor = null;
  final GameObject gameObject;

  private int[] x;
  private int[] y;
  private GuiPosition direction;
  private final GuiPosition centerOfMass;

  GuiObject(GameObject gameObject) {
    super();
    this.gameObject = gameObject;
    if (gameObject instanceof FlightObject) {
      this.direction = new GuiPosition(((FlightObject) gameObject).getTargetVector());
    }
    this.centerOfMass = new GuiPosition(gameObject.getPosition());
    writePolygon();
  }

  private Color getFillColor() {
    return fillColor;
  }

  private Color getBorderColor() {
    return borderColor;
  }

  /**
   * This method needs to be overwritten by any GameObject. An ordered List of GUIPositions is
   * expected in order to construct the edges of the polygon.
   *
   * @return a List of GuiPositions that define the objects shape
   */
  protected abstract List<GuiPosition> getShape();

  /** Writes the points into the polygon array. */
  private void writePolygon() {
    List<GuiPosition> shape = getShape();
    x = new int[shape.size()];
    y = new int[shape.size()];
    for (int i = 0; i < shape.size(); i++) {
      x[i] = shape.get(i).getX();
      y[i] = shape.get(i).getY();
    }
  }

  /** Turning the polygon array */
  void rotateShapeArrays(double extraAngle) {
    if (direction == null || centerOfMass == null) {
      throw new RuntimeException(
          "Class not correctly extended. To rotate there needs to be a center of mass and a direction");
    }

    double alpha = centerOfMass.getDrehWinkel(direction) + extraAngle;
    for (int i = 0; i < (x.length + y.length) / 2; i++) {
      int xOld = x[i];
      int yOld = y[i];
      x[i] = (int) (xOld * Math.cos(alpha) - yOld * Math.sin(alpha));
      y[i] = -(int) (xOld * Math.sin(alpha) + yOld * Math.cos(alpha));
    }
  }

  /** Moving the polygon array to the position on screen */
  private void moveShapeArrays() {
    if (centerOfMass == null) {
      throw new RuntimeException(
          "Class not correctly extended. To move the array there needs to be a center of mass.");
    }
    for (int i = 0; i < (x.length + y.length) / 2; i++) {
      x[i] = centerOfMass.getX() + x[i];
      if (x[i] < 0) {
        x[i] = 0;
      }
      y[i] = centerOfMass.getY() + y[i];
      if (y[i] < 0) {
        y[i] = 0;
      }
    }
  }

  void initialize() {
    moveShapeArrays();
    this.npoints = (x.length + y.length) / 2;
    this.xpoints = x;
    this.ypoints = y;
  }

  public void paint(Graphics g) {
    g.setColor(this.getFillColor());
    g.fillPolygon(this);
    g.setColor(this.getBorderColor());
    g.drawPolygon(this);
  }
}
