package core;

import java.util.Observable;
import java.util.Observer;

public class Explosion extends GameObject implements Observer {

    private int range;
    private int ticks;
    private double decayConstant = 0.125;

    Explosion(int range, Position location) {
        super(location);
        this.range = range;
        this.ticks = 0;
    }

    public int getTicks() {
        return ticks;
    }

    public Position getPosition() {
        return location;
    }

    public int getRange() {
        return range;
    }

    public boolean withinRange(Position abs) {
        Position rel = new Position(abs.getX() - location.getX(), abs.getY() - location.getY());
        if (Math.sqrt(rel.getX() * rel.getX() + rel.getY() * rel.getY()) < range)
            return true;
        else return false;
    }

    public void update(Observable arg0, Object arg1) {
        if (ticks > 2) range = (int) (range - ticks * decayConstant * range);
        else if (ticks == 1 || ticks == 2) range = (int) (range * 1.2);
        ticks++;
    }

}
