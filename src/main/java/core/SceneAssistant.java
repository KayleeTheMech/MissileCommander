package core;

import core.gameObjects.GameObjectFactory;
import core.gameObjects.Missile;
import core.gameObjects.UFO;

import static core.Core.gameBoardX;
import static core.Core.gameBoardY;

public class SceneAssistant {
    //fixme get these variables into a file
    private final int missileDetonationRange = 20;
    private final int ufoPeng = 50;

    private Core core;

    private GameObjectFactory objectFactory;

    SceneAssistant(Core core) {
        this.core = core;
        this.objectFactory = new GameObjectFactory();
    }


    public void shootMissile(Position target) {
        if (core.getBase().isAlive()) {
            core.getBase().addScore(-10);
            Missile missile = objectFactory.makeMissile(core.getBase().getPosition(), target, missileDetonationRange);
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
            target = core.getBase().getPosition();
        } else {
            target = new Position((int) (gameBoardX * Math.random() - gameBoardX / 2), 0);
        }
        newUFO(p, target, speed);
    }

    private void newUFO(Position position, Position target, int speed) {
        UFO ufo = objectFactory.makeUFO(position, target, speed, ufoPeng);
        core.addGameObject(ufo);
    }
}
