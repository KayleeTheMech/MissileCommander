package gui.shapes;

import gui.GUIPosition;

import java.util.ArrayList;

public class UfoShape extends AbstractShape {

    public UfoShape() {
        final int breite = 16;
        final int hoehe = 16;

        myShape = new ArrayList<>();
        myShape.add(new GUIPosition(+(int) ((double) 0 * breite), +(int) ((double) 6 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 2 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 4 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 14 / 16 * breite), +(int) ((double) 4 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 14 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 15 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 16 / 16 * breite), +(int) ((double) 1 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 8 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 4 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GUIPosition(+(int) ((double) 2 / 16 * breite), -(int) ((double) 5 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 2 / 16 * breite), -(int) ((double) 5 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 4 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 8 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 16 / 16 * breite), +(int) ((double) 1 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 15 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 14 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 14 / 16 * breite), +(int) ((double) 4 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 4 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        myShape.add(new GUIPosition(-(int) ((double) 2 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));

    }
}
