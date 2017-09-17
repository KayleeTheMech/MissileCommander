package core;

public class Missile extends FlightObject {
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

    public boolean withinRange(Position position) {
        if (position.subtract(getPosition()).getLength() <= detonationRadius) {
            return true;
        }
        return false;
    }

    public boolean reachedTarget() {
        if (getPosition().subtract(this.location).getLength() >= maxFlightDistance) {
            //return true;
        }
        if (getPosition().subtract(getTargetVector()).getLength() <= detonationRadius) {
            return true;
        }
        return false;
    }

}
