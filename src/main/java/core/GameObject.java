package core;

import java.util.Observable;
import java.util.Observer;

public abstract class GameObject implements IGameObject, Observer {

    protected int detonationRadius;

    protected int clock;

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

    public void update(Observable arg0, Object arg1) {
        clock++;
    }

    public void setDetonationRadius(int r) {
        this.detonationRadius = r;
    }

    public int getDetonationRadius() {
        return detonationRadius;
    }


}
