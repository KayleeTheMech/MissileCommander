package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

import controller.Controller;
import core.Core;

import static gui.Kampfschirm.WindowHeight;
import static gui.Kampfschirm.WindowWidth;

public class KampfschirmFenster extends JFrame implements MouseListener,
        WindowListener, Observer {
    static final long serialVersionUID = 2001;
    private int click = 0;
    private Core spielkern;
    private Controller controller;
    private Kampfschirm panel;
    private String fensterzeile;

    public KampfschirmFenster(Core spielkern, Controller controller, String fensterzeile) {
        super(fensterzeile + "   Score: " + spielkern.getBase().getScore());
        this.fensterzeile = fensterzeile;
        this.spielkern = spielkern;
        this.controller = controller;
        this.addMouseListener(this);
        this.addWindowListener(this);
        this.setSize(WindowWidth + 6, WindowHeight + 25);
        panel = new Kampfschirm(spielkern, controller);
        this.add(panel);
        this.setResizable(false);
        controller.addObserver(this);
        controller.addObserver(panel);
    }

    public void windowActivated(WindowEvent arg0) {
        controller.resume();
    }

    public void windowClosed(WindowEvent arg0) {
        System.exit(0);
    }


    public void windowClosing(WindowEvent arg0) {
        arg0.getWindow().dispose();
    }

    public void windowDeactivated(WindowEvent arg0) {
        controller.pause();
    }

    public void windowDeiconified(WindowEvent arg0) {
    }

    public void windowIconified(WindowEvent arg0) {
    }

    public void windowOpened(WindowEvent arg0) {
    }

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
        GUIPosition pos = new GUIPosition(arg0.getX(), arg0.getY());
        controller.action(pos.getBoardPosition());
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    public void update(Observable bla, Object blub) {
        click++;
        this.setTitle(fensterzeile + "  Score: " + spielkern.getBase().getScore() + " Takt:" + click);
    }

}
