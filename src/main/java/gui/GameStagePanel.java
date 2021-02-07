package gui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import core.SceneDirector;
import core.gameObjects.GameObject;
import events.GameEvent;
import events.GameEventType;
import gui.gameElements.GuiObject;
import gui.gameElements.GuiObjectFactory;
import gui.gameElements.GuiPosition;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GameStagePanel extends GamePanel {
  public static final int WindowHeight = 750;
  public static final int WindowWidth = 400;
  private final Font bigHeading = new Font("sans", Font.BOLD, 40);
  private final Font normalText = new Font("sans", Font.PLAIN, 12);
  private final List<DelayedInfoString> console;
  private final GuiObjectFactory factory;
  private final SceneDirector director;

  GameStagePanel(EventBus eventBus, GameFrame parent, SceneDirector director) {
    super(parent);
    factory = new GuiObjectFactory();
    this.director = director;
    console = new ArrayList<>();
    eventBus.register(this);
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
      graphicalObject.paint(g);
    }

    if (director.isGameOver()) {
      paintGameOver(g);
    }

    paintConsole(g, Color.green, normalText);
  }

  private void paintGameOver(Graphics g) {
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

  private void paintConsole(Graphics g, Color color, Font font) {

    List<DelayedInfoString> remove = new ArrayList<>();
    for (DelayedInfoString line : console) {
      if (!line.isActive()) {
        remove.add(line);
      }
    }
    console.removeAll(remove);
    int lineheight = (int) (g.getFontMetrics(font).getHeight() * 1.15);
    for (int i = 0; i < console.size(); i++) {
      String string = console.get(i).getString();
      g.setColor(color);
      g.setFont(font);
      g.drawString(string, 10, lineheight * (i + 1));
    }
  }

  @Subscribe()
  public void eventHandler(GameEvent event) {
    if (event.getEventType() == GameEventType.ATTACK_WAVE_IS_OVER && event.getMetaData() != null) {
      if (event.getMetaData().getContentDescription().equals("level")) {
        int level = (int) event.getMetaData().getData();
        console.add(new DelayedInfoString("Wave " + level + "  cleared.", 2500));
      }
    }

    if (event.getEventType() == GameEventType.NEW_ATTACK_WAVE_INCOMING) {
      console.add(new DelayedInfoString("New wave of fighters incoming!", 1000));
    }

    if (event.getEventType() == GameEventType.NEW_GAME_HAS_BEGUN) {
      console.add(new DelayedInfoString("Prepare: Attack imminent", 1000));
    }

    if (event.getEventType() == GameEventType.ROCKET_FIRED) {
      console.add(new DelayedInfoString("Rocket Cost: -10 points", 100));
    }

    if (event.getEventType() == GameEventType.ENEMY_SHIP_KILLED) {
      console.add(new DelayedInfoString("Enemy killed: 250 points", 1000));
    }

    if (event.getEventType() == GameEventType.SURFACE_HIT_BY_ENEMY) {
      console.add(new DelayedInfoString("Surface Damage: -250 Points", 1000));
    }

    if (event.getEventType() == GameEventType.ENEMY_HAS_YOUR_LOCATION) {
      console.add(new DelayedInfoString("Enemy aiming at you!!", 500));
    }

    if (event.getEventType() == GameEventType.NEW_ENEMY_INBOUND) {
      console.add(new DelayedInfoString("New enemy in atmosphere", 1000));
    }
  }

  @Override
  public void mousePressed(MouseEvent event) {
    GuiPosition pos =
        new GuiPosition(event.getX() - getWidthOffset(), event.getY() - getHeightOffset());
    director.mouseClick(pos.getBoardPosition());
  }
}
