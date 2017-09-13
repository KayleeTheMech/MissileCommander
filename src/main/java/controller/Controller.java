package controller;

import java.util.Observable;
import java.util.Timer;

import core.Core;
import core.Position;

public class Controller extends Observable {
    Core kern;
    TimerRoutine takt;
    Timer timer;
    long delay = 10;
    long runtime = 50;
    boolean pause;

    public Controller(Core kern) {
        this.kern = kern;
        this.takt = new TimerRoutine(this);
        timer = new Timer();
        timer.schedule(takt, delay, runtime);

    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;

    }

    protected void tick() {
        if (!pause) {
            kern.tick();
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void action(Position p) {
        if (!pause) kern.shootMissile(p, (int) Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY()));
        else resume();
    }
}
