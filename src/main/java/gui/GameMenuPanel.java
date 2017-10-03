package gui;

import gui.gameElements.GuiPosition;
import gui.menuElements.MenuButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class GameMenuPanel extends GamePanel {
    MenuButton button;

    GameMenuPanel(GameFrame parent) {
        super(parent);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        GuiPosition leftUpperCorner = new GuiPosition((WindowWidth - MenuButton.width) / 2, (WindowHeight - MenuButton.height) / 2);
        button = new MenuButton("New Game", leftUpperCorner);
        button.paint(g);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        GuiPosition clickedHere = new GuiPosition(event.getX(), event.getY());
        if (button != null && button.isLocatedWithinShape(clickedHere)) {
            parent.newGame();
        }
    }
}
