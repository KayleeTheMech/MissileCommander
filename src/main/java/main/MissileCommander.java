package main;

import com.google.common.eventbus.EventBus;
import core.SceneDirector;
import gui.GameFrame;

public class MissileCommander {

  public static void main(String[] args) {

    EventBus eventBus = new EventBus();
    SceneDirector director = new SceneDirector(eventBus);

    GameFrame gui = new GameFrame(eventBus, director, "Missile Commander");
    gui.setVisible(true);
  }
}
