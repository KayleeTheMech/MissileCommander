package gui;

import core.SceneDirector;

import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import static gui.GameStagePanel.WindowHeight;
import static gui.GameStagePanel.WindowWidth;

//FIXME remove the static nastiness by introducing an eventbus
public class GameFrame extends JFrame implements KeyListener, MouseListener, WindowListener, Observer {

    static final long serialVersionUID = 2001;

    private SceneDirector director;

    private GameStagePanel gameStagePanel;

    private GameMenuPanel gameMenuPanel;

    private GamePanel activePanel;


    private String fensterzeile;

    public GameFrame(String fensterzeile) {
        super(fensterzeile + "   Score: ");
        this.director = new SceneDirector();
        this.fensterzeile = fensterzeile;
        this.addMouseListener(this);
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.setSize(WindowWidth + 6, WindowHeight + 25);
        gameStagePanel = new GameStagePanel(this, director);
        gameMenuPanel = new GameMenuPanel(this);
        showMenu();
        this.setResizable(false);
    }

    private void startNewGame() {
        this.remove(gameMenuPanel);
        this.activePanel = gameStagePanel;
        this.add(gameStagePanel);

        director.newGame();
        director.addObserver(this);
        director.addObserver(gameStagePanel);

        revalidate();
        repaint();
    }

    void showMenu() {
        if (activePanel != null) {
            this.remove(gameStagePanel);
        }
        this.activePanel = gameMenuPanel;
        this.add(gameMenuPanel);
        revalidate();
        repaint();
    }

    void newGame() {
        startNewGame();
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Escape pressed.");
            if (director != null && !director.isGameOngoing()) {
                showMenu();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
