package core;

import controller.Controller;
import core.gameObjects.GameObject;
import events.EventUtil;
import events.GameEvent;
import events.GameEventMetaData;
import events.GameEventType;

import java.util.List;
import java.util.Observer;

import static events.GameEventType.NEW_GAME_HAS_BEGUN;

public class SceneDirector {
    public static final int FRAME_RATE_SCALING = 1;
    public static final int BASE_CHANCE_FOR_ENEMY_THIS_FRAME = 1;
    public static final int NUMBER_OF_FRAMES_PER_ROUND = 1000;
    public static final int NUMBER_OF_FRAMES_OF_ACTIVE_WAVE = 750;
    private static int frames;
    private static int difficulty;

    private Core core;
    private Controller controller;
    private SceneAssistant assistant;

    public SceneDirector() {

    }

    public void newFrame() {
        if (!assistant.isPlayerAlive()) {
            gameOverCycle();
        } else {
            gameRound();
        }
    }

    private void gameRound() {
        frames++;
        if (frames % NUMBER_OF_FRAMES_PER_ROUND * FRAME_RATE_SCALING == 0) {
            // reset round counter
            frames = 1;
            // increase level
            difficulty++;
            // inform everyone who's interested
            EventUtil.eventBus.post(new GameEvent(GameEventType.NEW_ATTACK_WAVE_INCOMING));
            System.out.println("New Attack Wave Incoming: " + difficulty);
        }

        if (frames % NUMBER_OF_FRAMES_OF_ACTIVE_WAVE == 0) {
            EventUtil.eventBus.post(new GameEvent(GameEventType.ATTACK_WAVE_IS_OVER, new GameEventMetaData<>("level", difficulty)));
            System.out.println("Attack wave over.");
        }

        if (frames < NUMBER_OF_FRAMES_OF_ACTIVE_WAVE && 100 * Math.random() < (difficulty + BASE_CHANCE_FOR_ENEMY_THIS_FRAME) / FRAME_RATE_SCALING) {
            // X percent to create enemy while playing
            assistant.createEnemy(difficulty);
        }
    }

    private void gameOverCycle() {
        if (100 * Math.random() < 5 / FRAME_RATE_SCALING) {
            // GAME_OVER_SIMULATION: X percent chance to for an explosion on surface per frame
            assistant.randomExplosionOnSurface();
        }
    }

    public void newGame() {
        difficulty = 1;
        frames = 0;
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
