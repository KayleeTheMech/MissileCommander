package core;

import java.util.Observable;
import java.util.Observer;

public abstract class GameObject implements IGameObject, Observer {

    private boolean alive;

    protected int detonationRadius;

    protected int clock;

    protected Position location;

    GameObject(Position location) {
        this.location = location;
        this.alive = true;
    }

    public Position getPosition() {
        return location;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void kill() {
        alive = false;
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
