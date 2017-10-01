package gui.gameElements;

import core.gameObjects.UFO;
import gui.gameElements.shapes.UfoShape;

import java.awt.*;
import java.util.List;

/**
 * This class manually defines the shape of an enemy UFO. It further rotates and moves this values to fit the object the shape
 * shall represent.
 */
public class GuiUfo extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final UfoShape ufoShape = new UfoShape();

    /**
     * This constructor takes an UFO Object and re-calculates the hard coded array into the position on screen.
     *
     * @param ufo
     */
    GuiUfo(UFO ufo) {
        super(ufo);
        rotateShapeArrays(Math.PI / 2);
        initialize();
        fillColor = Color.green;
        borderColor = Color.yellow;

    }

    @Override
    protected List<GuiPosition> getShape() {
        return ufoShape.getMyShape();
    }
}
