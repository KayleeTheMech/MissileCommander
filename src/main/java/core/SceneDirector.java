package core;

import controller.Controller;
import core.gameObjects.GameObject;
import events.EventUtil;

import java.util.List;
import java.util.Observer;

import static events.GameEventType.NEW_GAME_HAS_BEGUN;

public class SceneDirector {
    public static final int FRAME_RATE_SCALING = 1;
    public static final int BASE_CHANCE_FOR_ENEMY_THIS_FRAME = 1;
    private Core core;
    private Controller controller;
    private SceneAssistant assistant;

    private int difficulty;

    public SceneDirector() {
        difficulty = 1;
    }


    public void newFrame() {
        // set new difficulty level
        if (getScore() < 0) {
            difficulty = 1;
        } else {
            difficulty = getScore() / 10000 + 1;
        }

        if (!assistant.isPlayerAlive()) {
            if (100 * Math.random() < 5 / FRAME_RATE_SCALING) {
                // GAME_OVER_SIMULATION: X percent chance to for an explosion on surface per frame
                assistant.randomExplosionOnSurface();
            }
        } else if (100 * Math.random() < (difficulty + BASE_CHANCE_FOR_ENEMY_THIS_FRAME) / FRAME_RATE_SCALING) {
            // X percent to create enemy while playing
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

    private void fireMissile(Position p) {
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
