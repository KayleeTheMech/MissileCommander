package gui;

import core.SceneDirector;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

//FIXME remove the static nastiness by introducing an eventbus
public class GameFrame extends JFrame implements MouseListener, WindowListener, Observer {

    static final long serialVersionUID = 2001;

    private static SceneDirector director;

    private static GameStagePanel gameStagePanel;

    private static GameMenuPanel gameMenuPanel;

    private static GamePanel activePanel;
    private static GameFrame thisFrame;

    private String fensterzeile;

    public GameFrame(String fensterzeile) {
        super(fensterzeile + "   Score: ");
        thisFrame = this;
        this.director = new SceneDirector();
        this.fensterzeile = fensterzeile;
        this.addMouseListener(this);
        this.addWindowListener(this);
        this.setSize(WindowWidth + 6, WindowHeight + 25);
        gameStagePanel = new GameStagePanel(director);
        gameMenuPanel = new GameMenuPanel();
        showMenu();
        this.setResizable(false);
    }

    private static void startNewGame() {
        if (activePanel != null) {
            thisFrame.remove(activePanel);
        }
        thisFrame.activePanel = gameStagePanel;
        thisFrame.add(gameStagePanel);

        director.newGame();
        director.addObserver(thisFrame);
        director.addObserver(gameStagePanel);
    }

    public static void showMenu() {
        if (activePanel != null) {
            thisFrame.remove(gameStagePanel);
        }
        thisFrame.activePanel = gameMenuPanel;
        thisFrame.add(gameMenuPanel);
    }

    public static void newGame() {
        if (activePanel != null && thisFrame != null) {
            startNewGame();
        }
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
        this.activePanel.mousePressed(arg0);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        this.setTitle(fensterzeile + "  Score: " + director.getScore());
    }

}
