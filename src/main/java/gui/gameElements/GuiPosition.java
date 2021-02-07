package gui.gameElements;

import static core.Core.GAME_BOARD_HEIGHT;
import static core.Core.GAME_BOARD_WIDTH;
import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

import core.Position;

public class GuiPosition {

  private static final double stretchX = ((double) WindowWidth) / ((double) GAME_BOARD_WIDTH);

  private static final double stretchY = ((double) WindowHeight) / ((double) GAME_BOARD_HEIGHT);

  private final int x;

  private final int y;

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
    int x = ((int) (this.x / stretchX) - GAME_BOARD_WIDTH / 2);
    int y = (GAME_BOARD_HEIGHT - (int) (this.y / stretchY));
    return new Position(x, y);
  }

  double getDrehWinkel(GuiPosition p) {
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
    return "GuiPosition{" + "x=" + x + ", y=" + y + '}';
  }
}
