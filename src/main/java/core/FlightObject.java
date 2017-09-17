package core;

import java.util.Observable;
import java.util.Observer;

public abstract class FlightObject extends GameObject implements IFlightObject {

    protected Position flightVector;
    protected Position target;
    protected int speed;

    FlightObject() {
        super(null);
        this.clock = 0;
    }

    @Override
    public Position getPosition() {
        flightVector = target.subtract(location);
        double norm = flightVector.getLength();
        return location.add(flightVector.multiply((double) (clock * speed) / norm));
    }

    public void setTargetVector(Position target) {
        this.target = target;
    }

    public Position getTargetVector() {
        return target;
    }

}
