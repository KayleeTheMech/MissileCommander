package gui;

import controller.Controller;
import core.*;

import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

public class Kampfschirm extends KampfschirmSuper {
    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;

    private GUIBase base;
    private Core spielkern;

    static final long serialVersionUID = 2001;

    public Kampfschirm(Core spielkern, Controller controller) {
        super();
        this.setBackground(Color.black);
        this.setSize(WindowWidth, WindowHeight);
        this.base = new GUIBase(spielkern.getBase());
        this.spielkern = spielkern;
    }

    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillPolygon(base);
        // Spielobjekte bekommen
        List<GameObject> gameElements = spielkern.getGameObjects();
        for (GameObject element : gameElements) {
            AbstractGUIObject graphicalObject;
            if (element instanceof UFO) {
                graphicalObject = new GUIUfo((UFO) element);
            } else if (element instanceof Missile) {
                graphicalObject = new GUIMissile((Missile) element);
            } else if (element instanceof Explosion) {
                graphicalObject = new GUIExplosion((Explosion) element);
            } else {
                throw new RuntimeException("GameObject is of a not supported Class");
            }
            g.setColor(graphicalObject.getFillColor());
            g.fillPolygon(graphicalObject);
            g.setColor(graphicalObject.getBorderColor());
            g.drawPolygon(graphicalObject);
        }

    }

}
