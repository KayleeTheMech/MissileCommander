package gui;

import core.FlightObject;
import core.GameObject;

import java.awt.*;
import java.util.List;

public abstract class GUIObject extends Polygon {

    protected Color fillColor;
    protected Color borderColor;

    public Color getFillColor() {
        return fillColor;
    }


    public Color getBorderColor() {
        return borderColor;
    }


    protected int[] x;
    protected int[] y;
    protected GUIPosition direction;
    protected GUIPosition centerOfMass;
    protected GameObject gameObject;

    GUIObject(GameObject gameObject) {
        super();
        this.gameObject = gameObject;
        if (gameObject instanceof FlightObject) {
            this.direction = new GUIPosition(((FlightObject) gameObject).getTargetVector());
        }
        this.centerOfMass = new GUIPosition(gameObject.getPosition());
        writePolygon();
    }

    /**
     * This method needs to be overwritten by any GameObject.
     * An ordered List of GUIPositions is expected in order to construct the edges of the polygon.
     *
     * @return
     */
    protected abstract List<GUIPosition> getShape();

    /**
     * Writes the points into the polygon array.
     */
    private void writePolygon() {
        List<GUIPosition> shape = getShape();
        x = new int[shape.size()];
        y = new int[shape.size()];
        for (int i = 0; i < shape.size(); i++) {
            x[i] = shape.get(i).getX();
            y[i] = shape.get(i).getY();
        }
    }

    /**
     * Turning the polygon array
     */
    protected void rotateShapeArrays(double extraAngle) {
        if (direction == null || centerOfMass == null) {
            throw new RuntimeException("Class not correctly extended. To rotate there needs to be a center of mass and a direction");
        }

        double alpha = centerOfMass.getDrehWinkel(direction) + extraAngle;
        for (int i = 0; i < (x.length + y.length) / 2; i++) {
            int xOld = x[i];
            int yOld = y[i];
            x[i] = (int) (xOld * Math.cos(alpha) - yOld * Math.sin(alpha));
            y[i] = -(int) (xOld * Math.sin(alpha) + yOld * Math.cos(alpha));
        }
    }

    /**
     * Moving the polygon array to the position on screen
     */
    protected void moveShapeArrays() {
        if (centerOfMass == null) {
            throw new RuntimeException("Class not correctly extended. To move the array there needs to be a center of mass.");

        }
        for (int i = 0; i < (x.length + y.length) / 2; i++) {
            x[i] = centerOfMass.getX() + x[i];
            if (x[i] < 0) x[i] = 0;
            y[i] = centerOfMass.getY() + y[i];
            if (y[i] < 0) y[i] = 0;
        }
    }

    protected void initialize() {
        moveShapeArrays();
        this.npoints = (x.length + y.length) / 2;
        this.xpoints = x;
        this.ypoints = y;
    }


}