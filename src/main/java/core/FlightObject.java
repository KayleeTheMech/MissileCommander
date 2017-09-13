package core;

import java.util.Observable;
import java.util.Observer;

public abstract class FlightObject implements IFlightObject, Observer {

    protected Position r;
    protected Position target;
    protected int s;
    protected int clock;
    protected int speed = 10; // immer überschreiben in den unterklassen

    FlightObject() {
        this.clock = 0;
    }

    public Position getPosition() {
        Position rel = target.subtract(r);
        double norm = Math.sqrt(rel.getX() * rel.getX() + rel.getY() * rel.getY());
        return r.add(rel.multiply((double) (clock * speed) / norm));
    }

    public void setTargetVector(Position target) {
        this.target = target;
    }

    public Position getTargetVector() {
        return target;
    }

    public void setInitialCoordinates(Position r) {
        this.r = r;
    }

    public void update(Observable arg0, Object arg1) {
        clock++;
    }

}
