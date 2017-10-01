package core.gameObjects;

import core.Position;

public class GameObjectFactory {


    public Base makeBase(Position home, int detonationRadius) {
        return makeGameObject(home, detonationRadius, Base.class);
    }

    public Explosion makeExplosion(Position position, int detonationRadius) {
        return makeGameObject(position, detonationRadius, Explosion.class);
    }

    public Missile makeMissile(Position position, Position target, int detonationRadius) {
        Missile missile = makeGameObject(position, detonationRadius, Missile.class);
        missile.setTargetVector(target);
        return missile;
    }

    public UFO makeUFO(Position position, Position target, int speed, int detonationRadius) {
        UFO ufo = makeGameObject(position, detonationRadius, UFO.class);
        ufo.setSpeed(speed);
        ufo.setTargetVector(target);
        return ufo;
    }

    private <T extends GameObject> T makeGameObject(Position position, int detonationRadius, Class<T> objectClass) {
        T object;
        try {
            object = objectClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        object.setPosition(position);
        object.setDetonationRadius(detonationRadius);
        return object;
    }
}