package gui.gameElements;

import core.Position;

import static core.Core.gameBoardX;
import static core.Core.gameBoardY;
import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class GuiPosition {

    private static double stretchX = ((double) WindowWidth) / ((double) gameBoardX);

    private static double stretchY = ((double) WindowHeight) / ((double) gameBoardY);

    private int x;

    private int y;

    public GuiPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GuiPosition(Position boardPosition) {
        // Koordinatentransform intern to gui
        this.x = (int) (boardPosition.getX() * stretchX + WindowWidth / 2);
        this.y = (int) (WindowHeight - stretchX * boardPosition.getY());
    }

    public Position getBoardPosition() {
        int x = ((int) (this.x / stretchX) - gameBoardX / 2);
        int y = (gameBoardY - (int) (this.y / stretchY));
        Position pos = new Position(x, y);
        return pos;
    }

    public double getDrehWinkel(GuiPosition p) {
        int vergleichX = -10; // und (y=0)
        int vectorX = p.x - this.x;
        int vectorY = p.y - this.y;
        int skalarProdukt = vergleichX * vectorX;
        double laengeVector = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        double winkel = Math.acos(skalarProdukt / (10 * laengeVector));
        if (vectorY < 0) {
            return -(winkel);
        } else {
            return (winkel);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "GuiPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
