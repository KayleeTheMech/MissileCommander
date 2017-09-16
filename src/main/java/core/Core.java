package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Core extends Observable {
    public static int gameBoardX = 500;
    public static int gameBoardY = 1000;
    private int difficulty = 1; // number from 1 to infinity
    private int missileDetonationRange = 20;
    private int ufoPeng = 50;
    private int basePeng = 120;
    private Position basePosition = new Position(0, 0);
    private List<Missile> activeMissiles;
    private List<UFO> activeUFOs;
    private List<Explosion> activeExplosions;
    private Base baseOp;
    private boolean gameOver;

    /*
     * Konstruktor und öffentliche Methoden
     * */
    public Core() {
        baseOp = new Base(basePosition);
        gameOver = false;
        activeMissiles = new ArrayList<>();
        activeUFOs = new ArrayList<>();
        activeExplosions = new ArrayList<>();
    }

    // get Methoden
    public List<Missile> getActiveMissiles() {
        return activeMissiles;
    }

    public List<UFO> getActiveUFOs() {
        return activeUFOs;
    }

    public List<Explosion> getActiveExplosions() {
        return activeExplosions;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> returnList = new ArrayList<>();
        returnList.addAll(activeMissiles);
        returnList.addAll(activeUFOs);
        returnList.addAll(activeExplosions);
        returnList.add(baseOp);
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
            missile.setRange(range);
            missile.setPosition(baseOp.getPosition());
            this.addObserver(missile);
            baseOp.addScore(-10);
            activeMissiles.add(missile);
        }
    }

    public void tick() {
        this.setChanged();
        this.notifyObservers();
        if (!gameOver) {
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
        } else explodeBase();
    }

    /*
     * Private Methoden!!
     * Alles weitere wird nur vom Kern verwendet
     * */
    private void newUFO(Position r, Position target, int speed) {
        UFO o = new UFO();
        o.setTargetVector(target);
        o.setPosition(r);
        o.setSpeed(speed);
        this.addObserver(o);
        activeUFOs.add(o);
    }

    private void explodeMissile(Missile o) {
        activeMissiles.remove(o);
        Explosion baeng = new Explosion(o.getDetonationRadius(), o.getPosition());
        this.addObserver(baeng);
        activeExplosions.add(baeng);
    }

    private void explodeUFO(UFO o) {
        activeUFOs.remove(o);
        Explosion baeng = new Explosion(ufoPeng, o.getPosition());
        this.addObserver(baeng);
        activeExplosions.add(baeng);
        baseOp.addScore(250);
    }

    private void explodeBase() {
        int bang = (int) (basePeng * Math.random());
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        Position pos = new Position(xpos, basePosition.getY());
        Explosion baeng = new Explosion(bang, pos);
        this.addObserver(baeng);
        activeExplosions.add(baeng);
    }

    private void missileIgnitionRoutine() {
        // get Missiles to Explode
        List<Missile> toExplode = new ArrayList<>();
        for (Missile missile : activeMissiles) {
            if (missile.withinRange(new Position(0, 1400))) {
                toExplode.add(missile);
            } else {
                // oder zünden wenn in nähe von ziel
                for (UFO enemy : activeUFOs) {
                    if (missile.withinRange(enemy.getPosition())) {
                        toExplode.add(missile);
                    }
                }
            }
        }
        toExplode.forEach(missile -> explodeMissile(missile));
    }

    private void destructionRoutine() {
        // get UFOs to Explode
        List<UFO> toExplode = new ArrayList<>();
        for (UFO ufo : activeUFOs) {
            for (Explosion explosion : activeExplosions) {
                if (explosion.withinRange(ufo.getPosition())) {
                    toExplode.add(ufo);
                }
            }
        }
        toExplode.forEach(ufo -> explodeUFO(ufo));
    }


    private void impactRoutine() {
        List<UFO> toExplode = new ArrayList<UFO>();
        // ufos die einschlagen ermitteln und explodieren lassen
        for (UFO ufo : activeUFOs) {
            if (ufo.getPosition().getY() < 0) {
                toExplode.add(ufo);
            }
        }
        toExplode.forEach(ufo -> explodeUFO(ufo));

        for (Explosion explosion : activeExplosions) {
            if (explosion.withinRange(baseOp.getPosition())) baseOp.impact();
        }

        if (!baseOp.alive()) {
            gameOver = true;
            Explosion baeng = new Explosion(250, new Position(0, 0));
            this.addObserver(baeng);
            activeExplosions.add(baeng);
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
        for (Explosion explosion : activeExplosions) {
            if (explosion.getRange() < 0) {
                toBeRemoved.add(explosion);
            }
        }
        toBeRemoved.forEach(explosion -> activeExplosions.remove(explosion));
    }

}
