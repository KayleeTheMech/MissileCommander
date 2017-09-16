package gui;

import core.Position;

public class GUIPosition {
    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;
    private static double stretchX = ((double) WindowWidth) / ((double) Position.gameBoardX);
    private static double stretchY = ((double) WindowHeight) / ((double) Position.gameBoardY);
    private int x;
    private int y;

    public GUIPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    GUIPosition(Position boardPosition) {
        // Koordinatentransform intern to gui
        this.x = (int) (boardPosition.getX() * stretchX + WindowWidth / 2);
        this.y = (int) (WindowHeight - stretchX * boardPosition.getY());
    }

    public Position getBoardPosition() {
        int x = ((int) (this.x / stretchX) - Position.gameBoardX / 2);
        int y = (Position.gameBoardY - (int) (this.y / stretchY));
        Position pos = new Position(x, y);
        return pos;
    }

    public double getDrehWinkel(GUIPosition p) {
        int vergleichX = -10; // und (y=0)
        int vectorX = p.x - this.x;
        int vectorY = p.y - this.y;
        int skalarProdukt = vergleichX * vectorX;
        double laengeVector = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        double winkel = Math.acos(skalarProdukt / (10 * laengeVector));
        if (vectorY < 0) return -(winkel);
        else return (winkel);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
