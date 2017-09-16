package gui;

import controller.Controller;
import core.Core;
import core.Explosion;
import core.Missile;
import core.UFO;

import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
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
        List<UFO> ufos = spielkern.getActiveUFOs();
        List<Missile> raketen = spielkern.getActiveMissiles();
        List<Explosion> explosions = spielkern.getActiveExplosions();
        // ufos malen
        for (UFO ufo : ufos) {
            GUIUfo poly = new GUIUfo(ufo);
            g.setColor(Color.green);
            g.fillPolygon(poly);
            g.setColor(Color.yellow);
            g.drawPolygon(poly);
        }
        // raketen malen
        for(Missile missile:raketen){
            GUIMissile poly = new GUIMissile(missile);
            g.setColor(Color.gray);
            g.fillPolygon(poly);
            g.setColor(Color.white);
            g.drawPolygon(poly);
        }
        // explosionen malen
        for(Explosion explosion:explosions){
            GUIExplosion poly = new GUIExplosion(explosion);
            g.setColor(Color.yellow);
            g.fillPolygon(poly);
            g.setColor(Color.red);
            g.drawPolygon(poly);
        }
    }

}
