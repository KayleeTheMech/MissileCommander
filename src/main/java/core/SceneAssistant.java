package core;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import core.gameObjects.Explosion;
import core.gameObjects.GameObjectFactory;
import core.gameObjects.Missile;
import core.gameObjects.UFO;
import events.EventUtil;
import events.GameEvent;

import static core.Core.GAME_BOARD_HEIGHT;
import static core.Core.GAME_BOARD_WIDTH;
import static events.GameEventType.*;

public class SceneAssistant implements IDeactivate {
    //FIXME get these variables into a file
    public static final int MISSILE_FIRED_PENALTY = -10;
    public static final int SURFACE_HIT_BY_ENEMY_PENALTY = -250;
    public static final int ENEMY_KILLED_BOUNTY = 250;
    public static final int BASE_EXPLOSIVE_PAYLOAD = 120;
    public static final int MISSILE_DETONATION_RANGE = 20;
    public static final int UFO_EXPLOSIVE_PAYLOAD = 50;
    public static final int BASE_UFO_SPEED = 3;
    public static final int FRAME_RATE_SCALING = 1;
    public static final Position HOME = new Position(0, 0);


    private Core core;
    private GameObjectFactory objectFactory;

    //FIXME inject the dependency on the eventBus
    private EventBus eventBus = EventUtil.eventBus;

    private int score = 0;
    private boolean playerAlive;

    SceneAssistant(Core core) {
        this.core = core;
        this.objectFactory = new GameObjectFactory();
        eventBus.register(this);
        addPlayer();
    }

    private void addPlayer() {
        core.addGameObject(objectFactory.makeBase(HOME, BASE_EXPLOSIVE_PAYLOAD));
        playerAlive = true;
    }

    boolean isPlayerAlive() {
        return playerAlive;
    }

    int getScore() {
        return score;
    }

    void shootMissile(Position target) {
        internalCheck();
        if (isPlayerAlive()) {
            addScore(MISSILE_FIRED_PENALTY);
            Missile missile = objectFactory.makeMissile(HOME, target, MISSILE_DETONATION_RANGE);
            core.addGameObject(missile);
            EventUtil.eventBus.post(new GameEvent(ROCKET_FIRED));
        }
    }

    void createEnemy(int difficulty) {
        internalCheck();
        EventUtil.eventBus.post(new GameEvent(NEW_ENEMY_INBOUND));
        int enemySpeed = (int) (FRAME_RATE_SCALING * BASE_UFO_SPEED * (difficulty * Math.random() + 1));
        int randomX = (int) (GAME_BOARD_WIDTH * Math.random() - GAME_BOARD_WIDTH / 2);
        Position entryPointForEnemy = new Position(randomX, GAME_BOARD_HEIGHT);
        Position enemyTarget;
        if (Math.random() < 0.1 * difficulty) {
            // ENEMY GET'S YOUR COORDINATES
            EventUtil.eventBus.post(new GameEvent(ENEMY_HAS_YOUR_LOCATION));
            enemyTarget = HOME;
        } else {
            // ENEMY GET'S RANDOM SURFACE TARGET
            enemyTarget = new Position((int) (GAME_BOARD_WIDTH * Math.random() - GAME_BOARD_WIDTH / 2), 0);
        }
        newUFO(entryPointForEnemy, enemyTarget, enemySpeed);
    }

    private void newUFO(Position position, Position target, int speed) {
        UFO ufo = objectFactory.makeUFO(position, target, speed, UFO_EXPLOSIVE_PAYLOAD);
        core.addGameObject(ufo);
    }

    @Subscribe()
    public void eventHandler(GameEvent event) {
        if (event.getEventType() == SURFACE_HIT_BY_ENEMY) {
            addScore(SURFACE_HIT_BY_ENEMY_PENALTY);
        }

        if (event.getEventType() == ENEMY_SHIP_KILLED) {
            addScore(ENEMY_KILLED_BOUNTY);
        }

        if (event.getEventType() == PLAYER_HAS_DIED) {
            playerAlive = false;
        }
    }

    void addScore(int score) {
        if (isPlayerAlive()) {
            this.score = this.score + score;
        }
    }

    void randomExplosionOnSurface() {
        internalCheck();
        int detonationRadius = (int) (BASE_EXPLOSIVE_PAYLOAD * Math.random());
        int xpos = (int) (GAME_BOARD_WIDTH * Math.random() - GAME_BOARD_WIDTH / 2);
        Explosion explosion = objectFactory.makeExplosion(new Position(xpos, 0), detonationRadius);
        core.addGameObject(explosion);
    }

    public void deactivate() {
        internalCheck();
        eventBus.unregister(this);
        this.eventBus = null;
        this.core = null;
        this.objectFactory = null;

    }

    private void internalCheck() {
        if (eventBus == null || core == null || objectFactory == null) {
            throw new RuntimeException("Should not access SceneAssistant after deactivation");
        }
    }
}
