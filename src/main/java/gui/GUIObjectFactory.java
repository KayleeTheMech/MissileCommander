package gui;

import core.*;

public class GUIObjectFactory {

    public GUIObject getGUIObject(GameObject gameObject) {
        if (gameObject instanceof UFO) {
            return new GuiUfo((UFO) gameObject);

        } else if (gameObject instanceof Missile) {
            return new GUIMissile((Missile) gameObject);

        } else if (gameObject instanceof Explosion) {
            return new GUIExplosion((Explosion) gameObject);

        } else if (gameObject instanceof Base) {
            return new GUIBase((Base) gameObject);

        } else {
            throw new RuntimeException("GameObject is of a not supported Class");
        }
    }
}
