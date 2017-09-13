package core;

public class Position {
    public static int gameboardx = Core.gameBoardX;
    public static int gameboardy = Core.gameBoardY;
    private int xstd;
    private int ystd;

    public Position(int x, int y) {
        this.xstd = x;
        this.ystd = y;
    }

    public Position add(Position pos) {
        return new Position(xstd + pos.getX(), ystd + pos.getY());
    }

    public Position subtract(Position pos) {
        return new Position(xstd - pos.getX(), ystd - pos.getY());
    }

    public Position multiply(double alpha) {
        return new Position((int) (xstd * alpha), (int) (ystd * alpha));
    }

    public double getLength() {
        return Math.sqrt(xstd * xstd + ystd * ystd);
    }

    public int getX() {
        return xstd;
    }

    public int getY() {
        return ystd;
    }
}
