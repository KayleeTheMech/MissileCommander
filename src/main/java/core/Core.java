package core;

import java.util.*;

public class Core extends Observable {
    public final static int gameBoardX = 500;
    public final static int gameBoardY = 1000;
    private final int missileDetonationRange = 20;
    private final int ufoPeng = 50;
    private final int basePeng = 120;
    private final int basePositionX = 0;
    private final int basePositionY = 0;
    private final int startDifficulty = 1;

    private List<GameObject> gameObjects;
    private Base baseOp;

    private int difficulty;

    /*
     * Konstruktor und öffentliche Methoden
     * */
    public Core() {
        difficulty = startDifficulty;
        baseOp = new Base(new Position(basePositionX, basePositionY));
        baseOp.setDetonationRadius(basePeng);
        gameObjects = new ArrayList<>();
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
        this.setChanged();
        this.notifyObservers();

        missileIgnitionRoutine();

        destructionRoutine();

        // Propability for creating a new enemy ship
        if (100 * Math.random() < 1 * difficulty + 5) {
            createEnemy();
        }

        deleteDecayedExplosions();
        // if player alive let's see...
        if (baseOp.isAlive()) {
            // set new difficulty level
            if (baseOp.getScore() < 0) {
                difficulty = 1;
            } else {
                difficulty = baseOp.getScore() / 10000 + 1;
            }
        } else {
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


    private void explodeObject(GameObject o) {
        gameObjects.remove(o);
        o.kill();
        Explosion baeng = new Explosion(o.getDetonationRadius(), o.getPosition());
        addGameObject(baeng);
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
        Explosion baeng = new Explosion(detonationRadius, new Position(xpos, 0));
        addGameObject(baeng);
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


    private void newUFO(Position r, Position target, int speed) {
        UFO o = new UFO();
        o.setTargetVector(target);
        o.setPosition(r);
        o.setDetonationRadius(ufoPeng);
        o.setSpeed(speed);
        addGameObject(o);
    }

    private void createEnemy() {
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        int ypos = gameBoardY;
        Position target;
        int speed = (int) (difficulty * Math.random() * 10 + 10) / 2;
        Position p = new Position(xpos, ypos);
        if (Math.random() < 0.1 * difficulty) target = baseOp.getPosition();
        else target = new Position((int) (gameBoardX * Math.random() - gameBoardX / 2), 0);
        newUFO(p, target, speed);
    }

    // Aktionsmethoden
    public void shootMissile(Position target, int range) {
        if (baseOp.isAlive()) {
            baseOp.addScore(-10);
            Missile missile = new Missile();
            missile.setDetonationRadius(missileDetonationRange);
            missile.setTargetVector(target);
            missile.setMaxFlightDistance(range);
            missile.setPosition(baseOp.getPosition());
            addGameObject(missile);
        }
    }

    private void addGameObject(GameObject object) {
        this.addObserver(object);
        this.gameObjects.add(object);
    }


}
