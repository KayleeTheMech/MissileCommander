package gui.gameElements;

import core.gameObjects.*;

public class GuiObjectFactory {

    public GuiObject getGUIObject(GameObject gameObject) {
        if (gameObject instanceof UFO) {
            return new GuiUfo((UFO) gameObject);

        } else if (gameObject instanceof Missile) {
            return new GuiMissile((Missile) gameObject);

        } else if (gameObject instanceof Explosion) {
            return new GuiExplosion((Explosion) gameObject);

        } else if (gameObject instanceof Base) {
            return new GuiBase((Base) gameObject);

        } else {
            throw new RuntimeException("GameObject " + gameObject.getClass().toString() + "is of a not supported Class");
        }
    }
}
