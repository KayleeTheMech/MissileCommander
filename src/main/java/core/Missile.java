package core;

public class Missile extends FlightObject {
    private int detonationRadius;
    private int speed = 50;
    private int startrunden = 3;

    Missile() {
        super();
    }

    @Override
    public Position getPosition() {
        double actspeed = (speed * Math.exp(-(20 / (clock + 0.0000000001))));
        Position rel = target.subtract(location);
        double alpha = (actspeed * clock / rel.getLength());
        rel = rel.multiply(alpha);
        return location.add(rel);
    }

    public void setRange(int range) {
        this.s = range;
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
        if (rel.getLength() < detonationRadius && clock > startrunden || rel2.getLength() > s)
            return true;
        else return false;
    }

}
