package controller;

import core.Core;
import core.Position;

import java.util.Observable;
import java.util.Timer;

public class Controller extends Observable {

    long delay = 10;
    long runtime = 50;
    boolean pause;
    private Core core;
    private TimerRoutine timerRoutine;
    private Timer timer;

    public Controller(Core core) {
        this.core = core;
        this.timerRoutine = new TimerRoutine(this);
        timer = new Timer();
        timer.schedule(timerRoutine, delay, runtime);

    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }

    protected void tick() {
        if (!pause) {
            core.tick();
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void action(Position p) {
        if (!pause) {
            core.shootMissile(p);
        } else {
            resume();
        }

    }
}
