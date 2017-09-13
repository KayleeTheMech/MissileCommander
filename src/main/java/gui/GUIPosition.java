package gui;

import core.Position;

public class GUIPosition {
    public static int WindowHeight = 600;
    public static int WindowWidth = 300;
    private static double stretchx = ((double) WindowWidth) / ((double) Position.gameboardx);
    private static double stretchy = ((double) WindowHeight) / ((double) Position.gameboardy);
    private int xstd;
    private int ystd;

    GUIPosition(int x, int y) {
        this.xstd = x;
        this.ystd = y;
    }

    GUIPosition(Position blub) {
        // Koordinatentransform intern to gui
        this.xstd = (int) (blub.getX() * stretchx + WindowWidth / 2);
        this.ystd = (int) (WindowHeight - stretchx * blub.getY());
    }

    public Position getCorePosition() {
        int x = ((int) (this.xstd / stretchx) - Position.gameboardx / 2);
        int y = (Position.gameboardy - (int) (this.ystd / stretchy));
        Position pos = new Position(x, y);
        return pos;
    }

    public double getDrehWinkel(GUIPosition p) {
        int vergleichx = -10; // und (y=0)
        int vectorx = p.xstd - this.xstd;
        int vectory = p.ystd - this.ystd;
        int skalarprodukt = vergleichx * vectorx;
        double laengevector = Math.sqrt(vectorx * vectorx + vectory * vectory);
        double winkel = Math.acos(skalarprodukt / (10 * laengevector));
        if (vectory < 0) return -(winkel);
        else return (winkel);
    }

    public int getX() {
        return xstd;
    }

    public int getY() {
        return ystd;
    }
}
