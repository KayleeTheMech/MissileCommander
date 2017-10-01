package gui.menuElements;

import gui.gameElements.GuiPosition;

import java.awt.*;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class MenuButton {
    public static final int width = WindowWidth / 2;
    public static final int height = WindowHeight / 10;

    private Font menuFont = new Font("sans", Font.BOLD, 20);

    private GuiPosition leftUpperCorner;
    private String buttonText;

    public MenuButton(String buttonText, GuiPosition leftUpperCorner) {
        this.leftUpperCorner = leftUpperCorner;
        this.buttonText = buttonText;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(leftUpperCorner.getX(), leftUpperCorner.getY(), width, height);
        g.setColor(Color.yellow);
        g.fillRect(leftUpperCorner.getX() + 1, leftUpperCorner.getY() + 1, width - 1, height - 1);
        g.setColor(Color.BLACK);
        g.setFont(menuFont);
        int stringWidth = g.getFontMetrics(menuFont).stringWidth(buttonText);
        int stringHeight = g.getFontMetrics(menuFont).getHeight();
        g.drawString("New Game", leftUpperCorner.getX() + (width - stringWidth) / 2, leftUpperCorner.getY() + height / 2 + stringHeight / 4);
    }

    public boolean isLocatedWithinShape(GuiPosition p) {
        return (((p.getX() > leftUpperCorner.getX()) && (p.getX() < leftUpperCorner.getX() + width)) &&
                ((p.getY() > leftUpperCorner.getY()) && (leftUpperCorner.getY() < leftUpperCorner.getY() + height)));
    }
}
