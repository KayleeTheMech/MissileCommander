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
}
