package core;

public abstract class GameObject {

    protected Position location;


    GameObject(Position location) {
        this.location = location;
    }

    public Position getPosition() {
        return location;
    }
}
