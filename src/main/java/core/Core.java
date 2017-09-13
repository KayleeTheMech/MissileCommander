package core;

public class Core extends CoreSuper {
    public static int gameBoardX = 500;
    public static int gameBoardY = 1000;
    private int difficulty = 1; // number from 1 to infinity
    private int missileDetonationRange = 20;
    private int ufoPeng = 50;
    private int basePeng = 120;
    private Position basePosition = new Position(0, 0);
    private Missile[] activeMissiles;
    private UFO[] activeUFOs;
    private Explosion[] activeExplosions;
    private Base baseOp;
    private boolean gameOver;

    /*
     * Konstruktor und öffentliche Methoden
     * */
    public Core() {
        baseOp = new Base(basePosition);
        gameOver = false;
        activeMissiles = new Missile[0];
        activeUFOs = new UFO[0];
        activeExplosions = new Explosion[0];
    }

    // get Methoden
    public Missile[] getActiveMissiles() {
        return activeMissiles;
    }

    public UFO[] getActiveUFOs() {
        return activeUFOs;
    }

    public Explosion[] getActiveExplosions() {
        return activeExplosions;
    }

    public Base getPlayer() {
        return baseOp;
    }

    // Aktionsmethoden
    public void shootMissile(Position target, int range) {
        if (baseOp.alive()) {
            Missile o = new Missile();
            o.setDetonationRadius(missileDetonationRange);
            o.setTargetVector(target);
            o.setRange(range);
            o.setInitialCoordinates(baseOp.getPosition());
            this.addObserver(o);
            baseOp.addScore(-10);
            activeMissiles = addMissile(o, activeMissiles);
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
        o.setInitialCoordinates(r);
        o.setSpeed(speed);
        this.addObserver(o);
        activeUFOs = addUFO(o, activeUFOs);
    }

    private void explodeMissile(Missile o) {
        activeMissiles = removeMissile(o, activeMissiles);
        Explosion baeng = new Explosion(o.getDetonationRadius(), o.getPosition());
        this.addObserver(baeng);
        activeExplosions = addExplosion(baeng, activeExplosions);
    }

    private void explodeUFO(UFO o) {
        activeUFOs = removeUFO(o, activeUFOs);
        Explosion baeng = new Explosion(ufoPeng, o.getPosition());
        this.addObserver(baeng);
        activeExplosions = addExplosion(baeng, activeExplosions);
        baseOp.addScore(250);
    }

    private void explodeBase() {
        int bang = (int) (basePeng * Math.random());
        int xpos = (int) (gameBoardX * Math.random() - gameBoardX / 2);
        Position pos = new Position(xpos, basePosition.getY());
        Explosion baeng = new Explosion(bang, pos);
        this.addObserver(baeng);
        activeExplosions = addExplosion(baeng, activeExplosions);
    }

    private void missileIgnitionRoutine() {
        Missile[] toExplode = new Missile[0];
        // get Missiles to Explode
        for (int i = 0; i < activeMissiles.length; i++) {
            // trotzdem z�nden wenn strecke geflogen
            if (activeMissiles[i].withinRange(new Position(0, 1400))) {
                toExplode = addMissile(activeMissiles[i], toExplode);
            } else {
                // oder z�nden wenn in n�he von ziel
                for (int k = 0; k < activeUFOs.length; k++) {
                    if (activeMissiles[i].withinRange(activeUFOs[k].getPosition())) {
                        toExplode = addMissile(activeMissiles[i], toExplode);
                    }
                }
            }
        }
        // Ignite Missiles toExplode
        for (int i = 0; i < toExplode.length; i++) explodeMissile(toExplode[i]);
    }

    private void destructionRoutine() {
        UFO[] toExplode = new UFO[0];
        // get UFOs to Explode
        for (int i = 0; i < activeUFOs.length; i++) {
            for (int k = 0; k < activeExplosions.length; k++) {
                if (activeExplosions[k].withinRange(activeUFOs[i].getPosition()))
                    toExplode = addUFO(activeUFOs[i], toExplode);
            }
        }
        // Explode UFO
        for (int i = 0; i < toExplode.length; i++) explodeUFO(toExplode[i]);
    }

    private void impactRoutine() {
        UFO[] toExplode = new UFO[0];
        // ufos die einschlagen ermitteln
        for (int i = 0; i < activeUFOs.length; i++) {
            int hoehe = activeUFOs[i].getPosition().getY();
            if (hoehe < 0) {
                toExplode = addUFO(activeUFOs[i], toExplode);
            }
        }
        // ufos explodieren lassen
        for (int i = 0; i < toExplode.length; i++) explodeUFO(toExplode[i]);
        // schauen ob die basis putt ist
        for (int i = 0; i < activeExplosions.length; i++) {
            if (activeExplosions[i].withinRange(baseOp.getPosition())) baseOp.impact();
        }
        if (!baseOp.alive()) {
            gameOver = true;
            Explosion baeng = new Explosion(250, new Position(0, 0));
            this.addObserver(baeng);
            activeExplosions = addExplosion(baeng, activeExplosions);
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
        Explosion[] toDelete = new Explosion[0];
        for (int i = 0; i < activeExplosions.length; i++) {
            if (activeExplosions[i].getRange() < 0) {
                toDelete = addExplosion(activeExplosions[i], toDelete);
            }
        }
        for (int i = 0; i < toDelete.length; i++) activeExplosions = removeExplosion(toDelete[i], activeExplosions);
    }

}
