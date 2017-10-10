package core;

import com.google.common.eventbus.Subscribe;
import core.gameObjects.*;
import events.EventUtil;
import events.GameEvent;

import static core.Core.gameBoardX;
import static core.Core.gameBoardY;
import static events.GameEventType.*;

public class SceneAssistant {
    public final int basePeng = 120;
    //FIXME get these variables into a file
    private final int missileDetonationRange = 20;
    private final int ufoPeng = 50;
    private final int basePositionX = 0;
    private final int basePositionY = 0;

    private Core core;

    //FIXME let the assistant handle the score and get it out of the base
    private Base baseOp;

    private GameObjectFactory objectFactory;

    //FIXME inject the dependency on the eventBus
    SceneAssistant(Core core) {
        this.core = core;
        this.objectFactory = new GameObjectFactory();
        EventUtil.eventBus.register(this);
    }

    public void addPlayer() {
        baseOp = objectFactory.makeBase(new Position(basePositionX, basePositionY), basePeng);
        core.addGameObject(baseOp);
    }

    public boolean isPlayerAlive() {
        return baseOp.isAlive();
    }

    public int getScore() {
        if (baseOp == null) return 0;
        return baseOp.getScore();
    }

    public void shootMissile(Position target) {
        if (baseOp.isAlive()) {
            baseOp.addScore(-10);
            Missile missile = objectFactory.makeMissile(baseOp.getPosition(), target, missileDetonationRange);
            core.addGameObject(missile);
            EventUtil.eventBus.post(new GameEvent(ROCKET_FIRED));
        }
    }

    public void createEnemy(int difficulty) {
        EventUtil.eventBus.post(new GameEvent(NEW_ENEMY_INBOUND));
        int speed = (int) (difficulty * Math.random() * 10 + 10) / 2;
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        int ypos = gameBoardY;
        Position p = new Position(xpos, ypos);
        Position target;
        if (Math.random() < 0.1 * difficulty) {
            target = baseOp.getPosition();
        } else {
            target = new Position((int) (gameBoardX * Math.random() - gameBoardX / 2), 0);
        }
        newUFO(p, target, speed);
    }

    private void newUFO(Position position, Position target, int speed) {
        UFO ufo = objectFactory.makeUFO(position, target, speed, ufoPeng);
        core.addGameObject(ufo);
    }

    @Subscribe()
    public void eventHandler(GameEvent event) {
        if (event.getEventType() == SURFACE_HIT_BY_ENEMY) {
            addScore(-250);
        }

        if (event.getEventType() == ENEMY_SHIP_KILLED) {
            addScore(250);
        }
    }

    public void addScore(int score) {
        if (isPlayerAlive()) {
            baseOp.addScore(score);
        }
    }

    public void gameOverSimulation() {
        //FIXME retrieve basepeng differently
        int detonationRadius = (int) (basePeng * Math.random());
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        Explosion explosion = objectFactory.makeExplosion(new Position(xpos, 0), detonationRadius);
        core.addGameObject(explosion);
    }

}
