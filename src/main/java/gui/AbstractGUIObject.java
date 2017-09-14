package gui;

import core.GameObject;

import java.awt.*;

public abstract class AbstractGUIObject extends Polygon {

    protected int[] x;
    protected int[] y;
    protected GUIPosition direction;
    protected GUIPosition centerOfMass;
    protected GameObject gameObject;

    AbstractGUIObject(GameObject gameObject) {
        super();
        this.gameObject=gameObject;
        this.centerOfMass=new GUIPosition(gameObject.getPosition());
        this.x = getXShape();
        this.y = getYShape();
    }

    abstract int[] getXShape();
    abstract int[] getYShape();

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

    protected void initialize(){
        moveShapeArrays();
        this.npoints = (x.length + y.length) / 2;
        this.xpoints = x;
        this.ypoints = y;
    }
}
