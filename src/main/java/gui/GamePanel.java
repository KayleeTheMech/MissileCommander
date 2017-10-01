package gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Observer;

public abstract class GamePanel extends JPanel implements Observer {

    public abstract void mousePressed(MouseEvent event);
}
