package Util;

import gui.GUIPosition;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    private ArrayUtil() {
        // cannot be instanciateds
    }

    public static List<GUIPosition> getListFromArrays(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new RuntimeException("Given arrays don't have equal length, cannot transform into pairs of GUIPositions");
        }

        List<GUIPosition> points = new ArrayList<GUIPosition>();
        for (int i = 0; i < x.length; i++) {
            points.add(new GUIPosition(x[i], y[i]));
        }
        return points;
    }
}
