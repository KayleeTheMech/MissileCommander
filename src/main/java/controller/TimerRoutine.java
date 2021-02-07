package controller;

import java.util.TimerTask;

public class TimerRoutine extends TimerTask {
    private final Controller controller;

    TimerRoutine(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        controller.newFrame();
    }

}
