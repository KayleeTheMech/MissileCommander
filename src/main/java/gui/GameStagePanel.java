package gui;

import controller.Controller;
import core.Core;
import core.SceneDirector;
import core.gameObjects.GameObject;
import gui.gameElements.GuiObject;
import gui.gameElements.GuiObjectFactory;

import java.awt.*;
import java.util.Observable;

public class GameStagePanel extends GamePanel {

    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;
    static final long serialVersionUID = 2001;
    private GuiObjectFactory factory;

    private SceneDirector director;

    public GameStagePanel(SceneDirector director) {
        super();
        factory = new GuiObjectFactory();
        this.setBackground(Color.black);
        this.setSize(WindowWidth, WindowHeight);
        this.director = director;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Spielobjekte bekommen
        for (GameObject element : director.getGameObjects()) {
            GuiObject graphicalObject = factory.getGUIObject(element);
            g.setColor(graphicalObject.getFillColor());
            g.fillPolygon(graphicalObject);
            g.setColor(graphicalObject.getBorderColor());
            g.drawPolygon(graphicalObject);
        }
    }
}
