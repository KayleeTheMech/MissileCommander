package core;

public class Missile extends FlightObject {
    private int speed = 50;

    Missile() {
        super();
    }

    @Override
    public Position getPosition() {
        double currentSpeed = (speed * Math.exp(-(20 / (clock + 0.0000000001))));
        flightVector = target.subtract(position);
        double alpha = (currentSpeed * clock / flightVector.getLength());
        flightVector = flightVector.multiply(alpha);
        return position.add(flightVector);
    }

    public boolean withinRange(Position position) {
        if (position.subtract(getPosition()).getLength() <= detonationRadius) {
            return true;
        }
        return false;
    }

    public boolean reachedTarget() {
        if (getPosition().subtract(this.position).getLength() >= getTargetVector().subtract(this.position).getLength()) {
            return true;
        }
        return false;
    }

}
