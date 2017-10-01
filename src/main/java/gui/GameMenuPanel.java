package gui;

import gui.gameElements.GuiPosition;
import gui.menuElements.MenuButton;

import java.awt.*;
import java.util.Observable;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class GameMenuPanel extends GamePanel {

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        GuiPosition leftUpperCorner = new GuiPosition((WindowWidth - MenuButton.width) / 2, (WindowHeight - MenuButton.height) / 2);
        MenuButton button = new MenuButton("New Game", leftUpperCorner);
        button.paint(g);
    }
}
