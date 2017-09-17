package core;

public class GameObjectFactory {
    public Base makeBase(Position home, int detonationRadius) {
        Base base = new Base();
        base.setPosition(home);
        base.setDetonationRadius(detonationRadius);
        return base;
    }

    public UFO makeUFO(Position position, Position target, int speed, int detonationRadius) {
        UFO ufo = new UFO();
        ufo.setSpeed(speed);
        ufo.setTargetVector(target);
        ufo.setPosition(position);
        ufo.setDetonationRadius(detonationRadius);
        return ufo;
    }

    public Missile makeMissile(Position position, Position target, int detonationRadius) {
        Missile missile = new Missile();
        missile.setPosition(position);
        missile.setTargetVector(target);
        missile.setDetonationRadius(detonationRadius);
        return missile;
    }

    public Explosion makeExplosion(Position position, int detonationRadius) {
        Explosion explosion = new Explosion();
        explosion.setPosition(position);
        explosion.setDetonationRadius(detonationRadius);
        return explosion;
    }
}
