package events;

public class GameEvent {
    private final GameEventType eventType;

    private final GameEventMetaData metaData;

    public GameEvent(GameEventType eventType) {
        this.eventType = eventType;
        this.metaData = null;
    }

    public GameEvent(GameEventType eventType, GameEventMetaData metaData) {
        this.eventType = eventType;
        this.metaData = metaData;
    }

    public GameEventType getEventType() {
        return eventType;
    }

    public GameEventMetaData getMetaData() {
        return metaData;
    }
}
