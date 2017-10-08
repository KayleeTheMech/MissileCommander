package core;

import com.google.common.eventbus.Subscribe;
import core.gameObjects.*;
import events.EventUtil;
import events.MessageEvent;
import events.ObjectDeadMessageEvent;
import events.SurfaceHitMessageEvent;

import static core.Core.gameBoardX;
import static core.Core.gameBoardY;

public class SceneAssistant {
    public final int basePeng = 120;
    //fixme get these variables into a file
    private final int missileDetonationRange = 20;
    private final int ufoPeng = 50;
    private final int basePositionX = 0;
    private final int basePositionY = 0;

    private Core core;

    private Base baseOp;

    private GameObjectFactory objectFactory;

    SceneAssistant(Core core) {
        this.core = core;
        this.objectFactory = new GameObjectFactory();
        //FIXME inject the dependency on the eventBus
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
        return baseOp.getScore();
    }

    public void shootMissile(Position target) {
        if (baseOp.isAlive()) {
            baseOp.addScore(-10);
            Missile missile = objectFactory.makeMissile(baseOp.getPosition(), target, missileDetonationRange);
            core.addGameObject(missile);
        }
    }

    public void createEnemy(int difficulty) {
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
    public void objectDied(MessageEvent messageEvent) {
        if (messageEvent instanceof SurfaceHitMessageEvent) {
            addScore(-500);
        } else if (messageEvent instanceof ObjectDeadMessageEvent) {
            if (((ObjectDeadMessageEvent) messageEvent).getMyClass() == UFO.class) {
                addScore(250);
            }
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
