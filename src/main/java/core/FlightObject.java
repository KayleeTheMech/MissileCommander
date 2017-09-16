package core;

import java.util.Observable;
import java.util.Observer;

public abstract class FlightObject extends GameObject implements IFlightObject, Observer {

    protected Position flightVector;
    protected Position target;
    protected int clock;
    //FIXME seems dirty
    protected int speed = 10; // immer überschreiben in den unterklassen

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

    public void update(Observable arg0, Object arg1) {
        clock++;
    }

}
