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
        gameObjects.add(baseOp);
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> returnList = new ArrayList<>();
        returnList.addAll(gameObjects);
        return returnList;
    }

    public Base getBase() {
        return baseOp;
    }

    // Aktionsmethoden
    public void shootMissile(Position target, int range) {
        if (baseOp.alive()) {
            Missile missile = new Missile();
            missile.setDetonationRadius(missileDetonationRange);
            missile.setTargetVector(target);
            missile.setMaxFlightDistance(range);
            missile.setPosition(baseOp.getPosition());
            this.addObserver(missile);
            baseOp.addScore(-10);
            gameObjects.add(missile);
        }
    }

    public void tick() {
        this.setChanged();
        this.notifyObservers();
        if (baseOp.alive()) {
            // Raketen zünden
            missileIgnitionRoutine();
            // UFOs kaputt machen
            destructionRoutine();
            // Basis platt machen
            impactRoutine();
            // neue UFOs erstellen
            if (100 * Math.random() < 1 * difficulty + 5) createEnemy();
            //alte Explosionen löschen
            deleteDecayedExplosions();
            // neue Schwierigkeit einstellen
            if (baseOp.getScore() < 0) difficulty = 1;
            else difficulty = baseOp.getScore() / 10000 + 1;
        } else {
            explodePlanet();
        }
    }

    /*
     * Private Methoden!!
     * Alles weitere wird nur vom Kern verwendet
     * */
    private <Type> List<Type> getObjectType(Class<Type> typeClass) {
        List<Type> retList = new ArrayList<>();
        for (GameObject object : getGameObjects()) {
            if (typeClass.isInstance(object)) {
                retList.add((Type) object);
            }
        }
        return retList;
    }

    private void newUFO(Position r, Position target, int speed) {
        UFO o = new UFO();
        o.setTargetVector(target);
        o.setPosition(r);
        o.setDetonationRadius(ufoPeng);
        o.setSpeed(speed);
        this.addObserver(o);
        gameObjects.add(o);
    }

    private void explodeObject(GameObject o) {
        gameObjects.remove(o);
        Explosion baeng = new Explosion(o.getDetonationRadius(), o.getPosition());
        this.addObserver(baeng);
        gameObjects.add(baeng);
    }

    private void explodeUFO(UFO o) {
        explodeObject(o);
        baseOp.addScore(250);
    }

    private void explodePlanet() {
        int detonationRadius = (int) (baseOp.getDetonationRadius() * Math.random());
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        Explosion baeng = new Explosion(detonationRadius, new Position(xpos, 0));
        this.addObserver(baeng);
        gameObjects.add(baeng);
    }

    private void missileIgnitionRoutine() {
        // get Missiles to Explode
        List<Missile> toExplode = new ArrayList<>();
        List<UFO> ufos = getObjectType(UFO.class);
        List<Missile> activeMissiles = getObjectType(Missile.class);

        for (Missile missile : activeMissiles) {
            if (missile.reachedTarget()) {
                toExplode.add(missile);
            } else {
                for (UFO ufo : ufos) {
                    if (missile.withinRange(ufo.getPosition())) {
                        toExplode.add(missile);
                    }
                }
            }
        }
        toExplode.forEach(missile -> explodeObject(missile));
    }

    private void destructionRoutine() {
        // get UFOs to Explode
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
        }

        toExplode.forEach(gameObject -> {
            if (gameObject instanceof UFO) {
                explodeUFO((UFO) gameObject);
            } else {
                explodeObject(gameObject);
            }
        });
    }


    private void impactRoutine() {
        List<UFO> toExplode = new ArrayList<>();
        // ufos die einschlagen ermitteln und explodieren lassen
        for (UFO ufo : getObjectType(UFO.class)) {
            if (ufo.getPosition().getY() < 0) {
                toExplode.add(ufo);
            }
        }
        toExplode.forEach(ufo -> explodeUFO(ufo));

        for (Explosion explosion : getObjectType(Explosion.class)) {
            if (explosion.withinRange(baseOp.getPosition())) baseOp.impact();
        }

        if (!baseOp.alive()) {
            Explosion baeng = new Explosion(250, new Position(0, 0));
            this.addObserver(baeng);
            gameObjects.add(baeng);
        }
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

    private void deleteDecayedExplosions() {
        List<Explosion> toBeRemoved = new ArrayList<>();
        for (Explosion explosion : getObjectType(Explosion.class)) {
            if (explosion.getRange() < 0) {
                toBeRemoved.add(explosion);
            }
        }
        toBeRemoved.forEach(explosion -> gameObjects.remove(explosion));
    }

}
