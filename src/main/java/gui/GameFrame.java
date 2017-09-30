package gui;

import controller.Controller;
import core.Core;
import gui.gameElements.GuiPosition;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

public class GameFrame extends JFrame implements MouseListener, WindowListener, Observer {

    static final long serialVersionUID = 2001;

    private int click = 0;

    private Core spielkern;

    private Controller controller;

    private GamePanel panel;

    private String fensterzeile;

    public GameFrame(Core spielkern, Controller controller, String fensterzeile) {
        super(fensterzeile + "   Score: " + spielkern.getBase().getScore());
        this.fensterzeile = fensterzeile;
        this.spielkern = spielkern;
        this.controller = controller;
        this.addMouseListener(this);
        this.addWindowListener(this);
        this.setSize(WindowWidth + 6, WindowHeight + 25);
        //panel = new GameStagePanel(spielkern, controller);
        panel= (GamePanel) new GameMenuPanel();
        this.add(panel);
        this.setResizable(false);
        controller.addObserver(this);
        controller.addObserver(panel);
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
        controller.resume();
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        System.exit(0);
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        arg0.getWindow().dispose();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        controller.pause();
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        GuiPosition pos = new GuiPosition(arg0.getX(), arg0.getY());
        controller.action(pos.getBoardPosition());
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void update(Observable bla, Object blub) {
        click++;
        this.setTitle(fensterzeile + "  Score: " + spielkern.getBase().getScore() + " Takt:" + click);
    }

}
