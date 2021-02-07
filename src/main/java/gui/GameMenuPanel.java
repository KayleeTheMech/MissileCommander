package gui;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

import gui.gameElements.GuiPosition;
import gui.menuElements.MenuButton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class GameMenuPanel extends GamePanel {

  private final MenuButton newGameButton;
  private final MenuButton exitButton;

  GameMenuPanel(GameFrame parent) {
    super(parent);

    GuiPosition leftUpperCorner =
        new GuiPosition(
            (WindowWidth - MenuButton.width) / 2, (WindowHeight - MenuButton.height) / 2);
    newGameButton = new MenuButton("New Game", leftUpperCorner);
    leftUpperCorner =
        new GuiPosition(
            leftUpperCorner.getX(), leftUpperCorner.getY() + (int) (MenuButton.height * 1.5));
    exitButton = new MenuButton("Exit Game", leftUpperCorner);
  }

  @Override
  public void update(Observable o, Object arg) {
    this.repaint();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    newGameButton.paint(g);
    exitButton.paint(g);
  }

  public void mousePressed(MouseEvent event) {
    GuiPosition clickedHere =
        new GuiPosition(event.getX() - getWidthOffset(), event.getY() - getHeightOffset());
    if (newGameButton.isLocatedWithinShape(clickedHere)) {
      parent.newGame();
    } else if (exitButton.isLocatedWithinShape(clickedHere)) {
      parent.exitGame();
    }
  }
}
