package core;

public class Missile extends FlightObject {
    private int detonationRadius;
    private int speed = 50;
    private int startrunden = 3;
    private int maxFlightDistance;

    Missile() {
        super();
    }

    @Override
    public Position getPosition() {
        double currentSpeed = (speed * Math.exp(-(20 / (clock + 0.0000000001))));
        flightVector = target.subtract(location);
        double alpha = (currentSpeed * clock / flightVector.getLength());
        flightVector = flightVector.multiply(alpha);
        return location.add(flightVector);
    }

    public void setRange(int range) {
        this.maxFlightDistance = range;
    }

    public void setDetonationRadius(int r) {
        this.detonationRadius = r;
    }

    public int getDetonationRadius() {
        return detonationRadius;
    }

    public boolean withinRange(Position abs) {
        Position act = getPosition();
        Position rel = abs.subtract(act);
        Position rel2 = location.subtract(act);
        if (rel.getLength() < detonationRadius && clock > startrunden || rel2.getLength() > maxFlightDistance)
            return true;
        else return false;
    }

}
