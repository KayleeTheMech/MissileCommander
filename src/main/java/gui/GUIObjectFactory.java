package gui;

import core.*;

public class GUIObjectFactory {
    public GUIObject getGUIObject(GameObject gameObject) {
        if (gameObject instanceof UFO) return new GUIUfo((UFO) gameObject);
        if (gameObject instanceof Missile) return new GUIMissile((Missile) gameObject);
        if (gameObject instanceof Explosion) return new GUIExplosion((Explosion) gameObject);
        if (gameObject instanceof Base) return new GUIBase((Base) gameObject);
        throw new RuntimeException("GameObject is of a not supported Class");

    }
}
