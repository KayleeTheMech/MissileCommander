package controller;

import core.Core;
import core.SceneDirector;

import java.util.Observable;
import java.util.Timer;

public class Controller extends Observable {

    public static final long DELAY = 5;
    public static final long PERIOD = 25;
    private boolean pause;
    private Core core;
    private SceneDirector director;
    private TimerRoutine timerRoutine;
    private Timer timer;

    public Controller(Core core, SceneDirector director) {
        this.core = core;
        this.director = director;
        this.timerRoutine = new TimerRoutine(this);
        timer = new Timer();
        timer.schedule(timerRoutine, DELAY, PERIOD);
    }

    public boolean isPaused() {
        return pause;
    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }

    protected void newFrame() {
        if (!pause) {
            core.newFrame();
            director.newFrame();
        }
    }
}
