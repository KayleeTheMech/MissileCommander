package gui;

import controller.Controller;
import core.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

public class Kampfschirm extends KampfschirmSuper {
    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;

    private GUIObjectFactory factory;
    private Core spielKern;

    static final long serialVersionUID = 2001;

    public Kampfschirm(Core spielKern, Controller controller) {
        super();
        factory = new GUIObjectFactory();
        this.setBackground(Color.black);
        this.setSize(WindowWidth, WindowHeight);
        this.spielKern = spielKern;
    }

    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        // Spielobjekte bekommen
        for (GameObject element : spielKern.getGameObjects()) {
            GUIObject graphicalObject = factory.getGUIObject(element);
            g.setColor(graphicalObject.getFillColor());
            g.fillPolygon(graphicalObject);
            g.setColor(graphicalObject.getBorderColor());
            g.drawPolygon(graphicalObject);
        }

    }

}
