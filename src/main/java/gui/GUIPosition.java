package gui;

import core.Position;

public class GUIPosition {
    public static int WindowHeight = 750;
    public static int WindowWidth = 400;
    private static double stretchX = ((double) WindowWidth) / ((double) Position.gameBoardX);
    private static double stretchY = ((double) WindowHeight) / ((double) Position.gameBoardY);
    private int xStd;
    private int yStd;

    GUIPosition(int x, int y) {
        this.xStd = x;
        this.yStd = y;
    }

    GUIPosition(Position blub) {
        // Koordinatentransform intern to gui
        this.xStd = (int) (blub.getX() * stretchX + WindowWidth / 2);
        this.yStd = (int) (WindowHeight - stretchX * blub.getY());
    }

    public Position getCorePosition() {
        int x = ((int) (this.xStd / stretchX) - Position.gameBoardX / 2);
        int y = (Position.gameBoardY - (int) (this.yStd / stretchY));
        Position pos = new Position(x, y);
        return pos;
    }

    public double getDrehWinkel(GUIPosition p) {
        int vergleichX = -10; // und (y=0)
        int vectorX = p.xStd - this.xStd;
        int vectorY = p.yStd - this.yStd;
        int skalarProdukt = vergleichX * vectorX;
        double laengeVector = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        double winkel = Math.acos(skalarProdukt / (10 * laengeVector));
        if (vectorY < 0) return -(winkel);
        else return (winkel);
    }

    public int getX() {
        return xStd;
    }

    public int getY() {
        return yStd;
    }
}
