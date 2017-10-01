package gui;

import core.SceneDirector;
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

    private SceneDirector director;

    private GamePanel panel;

    private String fensterzeile;

    public GameFrame(String fensterzeile) {
        super(fensterzeile + "   Score: ");
        this.director = new SceneDirector();
        this.fensterzeile = fensterzeile;
        this.addMouseListener(this);
        this.addWindowListener(this);
        this.setSize(WindowWidth + 6, WindowHeight + 25);
        panel = new GameStagePanel(director);
        //panel= (GamePanel) new GameMenuPanel();
        this.add(panel);
        this.setResizable(false);
        director.newGame();
        director.addObserver(this);
        director.addObserver(panel);
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
        director.resume();
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
        director.pause();
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
        director.mouseClick(pos.getBoardPosition());
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void update(Observable bla, Object blub) {
        click++;
        this.setTitle(fensterzeile + "  Score: " + director.getScore());
    }

}
