package core;

import core.gameObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Core extends Observable {
    // Some constants that later need to move
    public final static int gameBoardX = 500;
    public final static int gameBoardY = 1000;
    private final int missileDetonationRange = 20;
    private final int ufoPeng = 50;
    private final int basePeng = 120;
    private final int basePositionX = 0;
    private final int basePositionY = 0;

    // Actual variables
    private List<GameObject> gameObjects;
    private GameObjectFactory objectFactory;
    private Base baseOp;

    public Core() {
        gameObjects = new ArrayList<>();
        objectFactory = new GameObjectFactory();
        baseOp = objectFactory.makeBase(new Position(basePositionX, basePositionY), basePeng);
        addGameObject(baseOp);
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> returnList = new ArrayList<>();
        returnList.addAll(gameObjects);
        return returnList;
    }

    public Base getBase() {
        return baseOp;
    }


    public void tick() {

        missileIgnitionRoutine();

        destructionRoutine();

        this.setChanged();
        this.notifyObservers();

        deleteDecayedExplosions();

        // if player alive let's see...
        if (!baseOp.isAlive()) {
            explodePlanet();
        }
    }

    private <Type> List<Type> getObjectType(Class<Type> typeClass) {
        List<Type> retList = new ArrayList<>();
        for (GameObject object : getGameObjects()) {
            if (typeClass.isInstance(object)) {
                retList.add((Type) object);
            }
        }
        return retList;
    }


    private void explodeObject(GameObject object) {
        gameObjects.remove(object);
        object.kill();
        Explosion explosion = objectFactory.makeExplosion(object.getPosition(), object.getDetonationRadius());
        addGameObject(explosion);
    }

    private void explodeUFO(UFO o) {
        explodeObject(o);
        if (baseOp.isAlive()) {
            baseOp.addScore(250);
        }
    }

    private void explodePlanet() {
        int detonationRadius = (int) (baseOp.getDetonationRadius() * Math.random());
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        Explosion explosion = objectFactory.makeExplosion(new Position(xpos, 0), detonationRadius);
        addGameObject(explosion);
    }

    private void missileIgnitionRoutine() {
        List<Missile> toExplode = new ArrayList<>();
        for (Missile missile : getObjectType(Missile.class)) {
            if (missile.reachedTarget()) {
                toExplode.add(missile);
            } else {
                for (UFO ufo : getObjectType(UFO.class)) {
                    if (missile.withinRange(ufo.getPosition())) {
                        toExplode.add(missile);
                    }
                }
            }
        }
        toExplode.forEach(missile -> explodeObject(missile));
    }

    private void destructionRoutine() {
        List<GameObject> toExplode = new ArrayList<>();
        List<Explosion> explosions = getObjectType(Explosion.class);
        List<GameObject> notExplosions = getGameObjects();
        notExplosions.removeAll(explosions);
        for (GameObject gameObject : notExplosions) {
            for (Explosion explosion : explosions) {
                if (explosion.withinRange(gameObject.getPosition())) {
                    toExplode.add(gameObject);
                }
            }

            if ((gameObject.getPosition().getY() < 0) && gameObject instanceof UFO) {
                toExplode.add(gameObject);
            }

        }

        toExplode.forEach(gameObject -> {
            if (gameObject instanceof UFO) {
                explodeUFO((UFO) gameObject);
            } else {
                explodeObject(gameObject);
            }
        });
    }

    private void deleteDecayedExplosions() {
        List<Explosion> toBeRemoved = new ArrayList<>();
        List<Explosion> allExplosions = getObjectType(Explosion.class);
        for (Explosion explosion : allExplosions) {
            if (explosion.getDetonationRadius() <= 1) {
                toBeRemoved.add(explosion);
            }
        }
        toBeRemoved.forEach(explosion -> gameObjects.remove(explosion));
    }

    private void newUFO(Position position, Position target, int speed) {
        UFO ufo = objectFactory.makeUFO(position, target, speed, ufoPeng);
        addGameObject(ufo);
    }

    public void shootMissile(Position target) {
        if (baseOp.isAlive()) {
            baseOp.addScore(-10);
            Missile missile = objectFactory.makeMissile(baseOp.getPosition(), target, missileDetonationRange);
            addGameObject(missile);
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

    private void addGameObject(GameObject object) {
        this.addObserver(object);
        this.gameObjects.add(object);
    }


}
