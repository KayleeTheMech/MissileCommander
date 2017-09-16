package gui;

import controller.Controller;
import core.Core;
import core.Explosion;
import core.Missile;
import core.UFO;

import java.awt.*;
import java.util.Observable;

public class Kampfschirm extends KampfschirmSuper {
    public static final int WindowHeight = 750;
    public static final int WindowWidth = 400;

    private GUIBase base;
    private Core spielkern;

    static final long serialVersionUID = 2001;

    public Kampfschirm(Core spielkern, Controller controller) {
        super();
        this.setBackground(Color.black);
        this.setSize(WindowWidth, WindowHeight);
        this.base = new GUIBase(spielkern.getBase());
        this.spielkern = spielkern;
    }

    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.fillPolygon(base);
        // Spielobjekte bekommen
        UFO[] ufos = spielkern.getActiveUFOs();
        Missile[] raketen = spielkern.getActiveMissiles();
        Explosion[] explosionen = spielkern.getActiveExplosions();
        // ufos malen
        for (int i = 0; i < ufos.length; i++) {
            GUIUfo poly = new GUIUfo(ufos[i]);
            g.setColor(Color.green);
            g.fillPolygon(poly);
            g.setColor(Color.yellow);
            g.drawPolygon(poly);
        }
        // raketen malen
        for (int i = 0; i < raketen.length; i++) {
            GUIMissile poly = new GUIMissile(raketen[i]);
            g.setColor(Color.gray);
            g.fillPolygon(poly);
            g.setColor(Color.white);
            g.drawPolygon(poly);
        }
        // explosionen malen
        for (int i = 0; i < explosionen.length; i++) {
            GUIExplosion poly = new GUIExplosion(explosionen[i]);
            g.setColor(Color.yellow);
            g.fillPolygon(poly);
            g.setColor(Color.red);
            g.drawPolygon(poly);
        }
    }

}
