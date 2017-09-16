package gui;

import controller.Controller;
import core.*;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

public class Kampfschirm extends JPanel implements Observer {
    static final long serialVersionUID = 2001;
    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;

    private GUIObjectFactory factory;
    private Core spielKern;


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
