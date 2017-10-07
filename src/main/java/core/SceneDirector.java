package core;

import controller.Controller;
import core.gameObjects.GameObject;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SceneDirector implements Observer {
    private Core core;
    private Controller controller;
    private SceneAssistant assistant;

    private int difficulty;

    public SceneDirector() {
        difficulty = 1;
    }

    @Override
    public void update(Observable observable, Object o) {
        // set new difficulty level
        if (getScore() < 0) {
            difficulty = 1;
        } else {
            difficulty = getScore() / 10000 + 1;
        }

        // Propability for creating a new enemy ship
        if (100 * Math.random() < 1 * difficulty + 5) {
            assistant.createEnemy(difficulty);
        }
    }

    public void newGame() {
        difficulty = 1;
        core = new Core();
        controller = new Controller(core);
        assistant = new SceneAssistant(core);
        core.addObserver(this);

    }

    int getDifficulty() {
        return difficulty;
    }

    public void addObserver(Observer object) {
        core.addObserver(object);
    }

    public void pause() {
        if (controller != null) {
            controller.pause();
        }
    }

    public void resume() {
        if (controller != null) {
            controller.resume();
        }
    }

    public void mouseClick(Position boardPosition) {
        fireMissile(boardPosition);
    }

    public void fireMissile(Position p) {
        if (!controller.isPaused()) {
            assistant.shootMissile(p);
        } else {
            resume();
        }

    }

    public int getScore() {
        return core.getBase().getScore();
    }

    public List<GameObject> getGameObjects() {
        return core.getGameObjects();
    }

    public boolean isGameOngoing() {
        return core.getBase().isAlive();
    }
}
