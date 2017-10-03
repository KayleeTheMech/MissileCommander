package gui;

import core.SceneDirector;
import core.gameObjects.GameObject;
import gui.gameElements.GuiObject;
import gui.gameElements.GuiObjectFactory;
import gui.gameElements.GuiPosition;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class GameStagePanel extends GamePanel {
    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;
    
    private GuiObjectFactory factory;

    private SceneDirector director;

    public GameStagePanel(GameFrame parent, SceneDirector director) {
        super(parent);
        factory = new GuiObjectFactory();
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

        if (!director.isGameOngoing()) {
            paintGameOver(g);
        }
    }

    private void paintGameOver(Graphics g) {
        Font bigHeading = new Font("sans", Font.BOLD, 40);
        Font normalText = new Font("sans", Font.PLAIN, 12);
        String gameOver = "GAME OVER";
        String exitToMenu = "Press ESC to return to menu";

        paintStringCentered(g, gameOver, Color.red, bigHeading, getHeight() / 3);

        paintStringCentered(g, exitToMenu, Color.red, normalText, getHeight() * 2 / 3);

    }

    private void paintStringCentered(Graphics g, String string, Color color, Font font, int height) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(string, (getWidth() - g.getFontMetrics(font).stringWidth(string)) / 2, height);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        GuiPosition pos = new GuiPosition(event.getX() - getWidthOffset(), event.getY() - getHeightOffset());
        director.mouseClick(pos.getBoardPosition());
    }


}
