package core;

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
        flightVector = target.subtract(position);
        double norm = flightVector.getLength();
        return position.add(flightVector.multiply((double) (clock * speed) / norm));
    }

    public void setTargetVector(Position target) {
        this.target = target;
    }

    public Position getTargetVector() {
        return target;
    }

}
