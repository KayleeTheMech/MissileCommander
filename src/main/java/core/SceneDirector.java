package core;

import controller.Controller;
import core.gameObjects.GameObject;
import events.EventUtil;

import java.util.List;
import java.util.Observer;

import static events.GameEventType.NEW_GAME_HAS_BEGUN;

public class SceneDirector {
    private Core core;
    private Controller controller;
    private SceneAssistant assistant;

    private int difficulty;

    public SceneDirector() {
        difficulty = 1;
    }


    public void tick() {
        // set new difficulty level
        if (getScore() < 0) {
            difficulty = 1;
        } else {
            difficulty = getScore() / 10000 + 1;
        }

        if (!assistant.isPlayerAlive()) {
            assistant.gameOverSimulation();
        } else if (200 * Math.random() < 1 * difficulty + 5) {
            assistant.createEnemy(difficulty);
        }

    }

    public void newGame() {
        difficulty = 1;
        core = new Core();
        assistant = new SceneAssistant(core);
        assistant.addPlayer();
        controller = new Controller(core, this);
        EventUtil.eventBus.post(NEW_GAME_HAS_BEGUN);

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
        return assistant.getScore();
    }

    public List<GameObject> getGameObjects() {
        return core.getGameObjects();
    }

    public boolean isGameOngoing() {
        return assistant.isPlayerAlive();
    }
}
