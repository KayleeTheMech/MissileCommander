package gui;

import gui.gameElements.GuiPosition;
import gui.menuElements.MenuButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class GameMenuPanel extends GamePanel {

    private MenuButton newGameButton;
    private MenuButton exitButton;

    GameMenuPanel(GameFrame parent) {
        super(parent);
        GuiPosition leftUpperCorner = new GuiPosition((WindowWidth - MenuButton.width) / 2, (WindowHeight - MenuButton.height) / 2);
        newGameButton = new MenuButton("New Game", leftUpperCorner);
        leftUpperCorner = new GuiPosition(leftUpperCorner.getX(), leftUpperCorner.getY() + (int) (MenuButton.height * 1.05));
        exitButton = new MenuButton("Exit Game", leftUpperCorner);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        newGameButton.paint(g);
        exitButton.paint(g);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        GuiPosition clickedHere = new GuiPosition(event.getX(), event.getY());
        if (newGameButton.isLocatedWithinShape(clickedHere)) {
            parent.newGame();
        } else if (exitButton.isLocatedWithinShape(clickedHere)) {
            parent.exitGame();
        }
    }
}

