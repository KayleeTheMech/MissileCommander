package gui;

import java.awt.*;
import java.util.Observable;

public class GameMenuPanel extends GamePanel {

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        int height = GameStagePanel.WindowHeight;
        int width = GameStagePanel.WindowWidth;
        final int menuItemWidth = width / 2;
        final int menuItemHeight = height / 10;
        final int leftBorder = (width - menuItemWidth) / 2;
        final String menuText = "New Game";
        final Font menuFont = new Font("sans", Font.BOLD, 20);
        g.setColor(Color.RED);


        g.drawRect(leftBorder, (height - menuItemHeight) / 2, menuItemWidth, menuItemHeight);
        g.setColor(Color.ORANGE);
        g.fillRect(leftBorder, (height - menuItemHeight) / 2, menuItemWidth, menuItemHeight);
        g.setColor(Color.BLACK);
        g.setFont(menuFont);
        int stringWidth = g.getFontMetrics(menuFont).stringWidth(menuText);
        int stringHeight = g.getFontMetrics(menuFont).getHeight();
        g.drawString("New Game", leftBorder + (menuItemWidth - stringWidth) / 2, (height) / 2 + stringHeight/4);
    }
}
