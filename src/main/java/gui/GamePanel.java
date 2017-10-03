package gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Observer;

public abstract class GamePanel extends JPanel implements Observer {

    protected GameFrame parent;

    GamePanel(GameFrame parent) {
        this.parent = parent;
    }

    public abstract void mousePressed(MouseEvent event);

    protected int getHeightOffset() {
        return (parent.getSize().height - parent.getContentPane().getSize().height);
    }

    protected int getWidthOffset() {
        return (parent.getSize().width - parent.getContentPane().getSize().width);
    }
}
