package core;

import core.gameObjects.*;
import events.EventUtil;
import events.GameEvent;
import events.GameEventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Core extends Observable {
    // Some constants that later need to move
    public final static int gameBoardX = 500;
    public final static int gameBoardY = 1000;


    // Actual variables
    private List<GameObject> gameObjects;
    private GameObjectFactory objectFactory;

    public Core() {
        gameObjects = new ArrayList<>();
        objectFactory = new GameObjectFactory();
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> returnList = new ArrayList<>();
        returnList.addAll(gameObjects);
        return returnList;
    }

    public void tick() {
        missileIgnitionRoutine();
        destructionRoutine();
        this.setChanged();
        this.notifyObservers();
        deleteDecayedExplosions();
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
        EventUtil.eventBus.post(new GameEvent(GameEventType.EXPLOSION));
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
                    if (gameObject instanceof UFO) {
                        EventUtil.eventBus.post(new GameEvent(GameEventType.ENEMY_SHIP_KILLED));
                    }
                    toExplode.add(gameObject);
                }
            }

            if ((gameObject.getPosition().getY() < 0) && gameObject instanceof UFO) {
                EventUtil.eventBus.post(new GameEvent(GameEventType.SURFACE_HIT_BY_ENEMY));
                toExplode.add(gameObject);
            }
        }

        toExplode.forEach(gameObject -> explodeObject(gameObject));
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

    void addGameObject(GameObject object) {
        this.addObserver(object);
        this.gameObjects.add(object);
    }


}
