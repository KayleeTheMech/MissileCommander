package core;

import java.util.Observable;
import java.util.Observer;

public abstract class GameObject implements IGameObject, Observer {

    private boolean alive;

    protected int detonationRadius;

    protected int clock;

    protected Position position;

    GameObject() {
        this.alive = true;
    }

    GameObject(Position position) {
        this.position = position;
        this.alive = true;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void kill() {
        alive = false;
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
