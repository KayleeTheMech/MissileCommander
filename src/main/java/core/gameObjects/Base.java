package core.gameObjects;

import events.EventUtil;
import events.GameEvent;

import static events.GameEventType.PLAYER_HAS_DIED;

public class Base extends GameObject {

    @Override
    public void kill() {
        super.kill();
        EventUtil.eventBus.post(new GameEvent(PLAYER_HAS_DIED));
    }

}
