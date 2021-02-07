package gui.gameElements.shapes;

import gui.gameElements.GuiPosition;
import java.util.ArrayList;

public class BaseShape extends AbstractShape {
  public BaseShape() {
    myShape = new ArrayList<>();
    final int width = 30;
    final int height = 20;
    myShape.add(new GuiPosition(-width, -height));
    myShape.add(new GuiPosition(-width, +0));
    myShape.add(new GuiPosition(+width, +0));
    myShape.add(new GuiPosition(+width, -height));
    myShape.add(new GuiPosition(+width / 2, -height / 2));
    myShape.add(new GuiPosition(-width / 2, -height / 2));
  }
}
