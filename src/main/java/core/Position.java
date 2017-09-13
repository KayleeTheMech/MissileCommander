package core;

public class Position {
    public static int gameBoardX = Core.gameBoardX;
    public static int gameBoardY = Core.gameBoardY;
    private int xStd;
    private int yStd;

    public Position(int x, int y) {
        this.xStd = x;
        this.yStd = y;
    }

    public Position add(Position pos) {
        return new Position(xStd + pos.getX(), yStd + pos.getY());
    }

    public Position subtract(Position pos) {
        return new Position(xStd - pos.getX(), yStd - pos.getY());
    }

    public Position multiply(double alpha) {
        return new Position((int) (xStd * alpha), (int) (yStd * alpha));
    }

    public double getLength() {
        return Math.sqrt(xStd * xStd + yStd * yStd);
    }

    public int getX() {
        return xStd;
    }

    public int getY() {
        return yStd;
    }
}
