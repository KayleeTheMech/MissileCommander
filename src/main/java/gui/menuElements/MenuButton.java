package gui.menuElements;

import gui.gameElements.GuiPosition;

import java.awt.*;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class MenuButton {
    public static final int width = WindowWidth / 2;
    public static final int height = WindowHeight / 15;

    private final int leftBorder;
    private final int rightBorder;
    private final int topBorder;
    private final int bottomBorder;

    private final Font menuFont = new Font("sans", Font.BOLD, 20);

    private final String buttonText;

    public MenuButton(String buttonText, GuiPosition leftUpperCorner) {
        this.buttonText = buttonText;

        leftBorder = leftUpperCorner.getX();
        rightBorder = leftUpperCorner.getX() + width - 1;
        topBorder = leftUpperCorner.getY();
        bottomBorder = leftUpperCorner.getY() + height - 1;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(leftBorder, topBorder, width, height);
        g.setColor(Color.ORANGE);
        g.fillRect(leftBorder + 1, topBorder + 1, width - 1, height - 1);
        g.setColor(Color.BLACK);
        g.setFont(menuFont);
        int stringWidth = g.getFontMetrics(menuFont).stringWidth(buttonText);
        int stringHeight = g.getFontMetrics(menuFont).getHeight();
        g.drawString(buttonText, leftBorder + (width - stringWidth) / 2, topBorder + height / 2 + stringHeight / 4);
    }

    public boolean isLocatedWithinShape(GuiPosition p) {
        return (p.getX() > leftBorder) && (p.getX() < rightBorder) &&
                (p.getY() > topBorder) && (p.getY() < bottomBorder);
    }
}
