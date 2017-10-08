package events;

import core.gameObjects.GameObject;

public class ObjectDeadMessageEvent<T extends GameObject> extends MessageEvent {
    private Class<T> myClass;

    public ObjectDeadMessageEvent(Class<T> myClass) {
        this.myClass = myClass;
    }

    public Class<T> getMyClass() {
        return myClass;
    }
}
