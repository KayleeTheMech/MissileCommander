package core.gameObjects;

import static events.GameEventType.PLAYER_HAS_DIED;

import com.google.common.eventbus.EventBus;
import events.GameEvent;

public class Base extends GameObject {

  private final EventBus eventBus;

  Base(EventBus eventBus) {
    super();
    this.eventBus = eventBus;
  }

  @Override
  public void kill() {
    super.kill();
    eventBus.post(new GameEvent(PLAYER_HAS_DIED));
  }
}
