package core;

import java.util.Observable;
import java.util.Observer;

public abstract class FlightObject extends GameObject implements IFlightObject, Observer {

    protected Position target;
    protected int s;
    protected int clock;
    //FIXME seems dirty
    protected int speed = 10; // immer überschreiben in den unterklassen

    FlightObject() {
        super(null);
        this.clock = 0;
    }

    public Position getPosition() {
        Position rel = target.subtract(location);
        double norm = Math.sqrt(rel.getX() * rel.getX() + rel.getY() * rel.getY());
        return location.add(rel.multiply((double) (clock * speed) / norm));
    }

    public void setTargetVector(Position target) {
        this.target = target;
    }

    public Position getTargetVector() {
        return target;
    }

    public void setInitialCoordinates(Position r) {
        this.location = r;
    }

    public void update(Observable arg0, Object arg1) {
        clock++;
    }

}
