package core;

import java.util.Observable;
import java.util.Observer;

public class Explosion extends GameObject implements Observer {

    private double decayConstant = 0.125;

    Explosion(){
        super();
        this.clock=0;
    }

    Explosion(int detonationRadius) {
        super();
        this.clock = 0;
        this.detonationRadius = detonationRadius;
    }

    public boolean withinRange(Position abs) {
        Position rel = abs.subtract(this.position);
        if (rel.getLength() < detonationRadius)
            return true;
        else return false;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (clock > 2) {
            detonationRadius = (int) (detonationRadius - clock * decayConstant * detonationRadius);
        } else if (clock == 1 || clock == 2) {
            detonationRadius = (int) (detonationRadius * 1.2);
        }
        clock++;
    }

}
