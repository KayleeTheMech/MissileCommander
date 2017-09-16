package core;

public abstract class GameObject implements IGameObject {

    Position location;

    GameObject(Position location) {
        this.location = location;
    }

    public Position getPosition() {
        return location;
    }

    public void setPosition(Position r) {
        this.location = r;
    }

}
