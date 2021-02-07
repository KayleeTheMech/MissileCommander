package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observer;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public abstract class GamePanel extends JPanel implements Observer {


    static final long serialVersionUID = 2001;

    protected final GameFrame parent;

    GamePanel(GameFrame parent) {
        this.parent = parent;
        this.setBackground(Color.black);
        this.setSize(WindowWidth, WindowHeight);
    }

    public abstract void mousePressed(MouseEvent event);

    protected int getHeightOffset() {
        return (parent.getSize().height - parent.getContentPane().getSize().height);
    }

    protected int getWidthOffset() {
        return (parent.getSize().width - parent.getContentPane().getSize().width);
    }
}
