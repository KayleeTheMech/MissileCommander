package gui;

import controller.Controller;
import core.Core;
import core.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Kampfschirm extends JPanel implements Observer {

    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;
    static final long serialVersionUID = 2001;
    private GUIObjectFactory factory;

    private Core core;

    public Kampfschirm(Core core, Controller controller) {
        super();
        factory = new GUIObjectFactory();
        this.setBackground(Color.black);
        this.setSize(WindowWidth, WindowHeight);
        this.core = core;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Spielobjekte bekommen
        for (GameObject element : core.getGameObjects()) {
            GUIObject graphicalObject = factory.getGUIObject(element);
            g.setColor(graphicalObject.getFillColor());
            g.fillPolygon(graphicalObject);
            g.setColor(graphicalObject.getBorderColor());
            g.drawPolygon(graphicalObject);
        }
    }
}
