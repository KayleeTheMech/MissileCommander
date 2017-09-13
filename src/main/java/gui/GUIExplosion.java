package gui;

import java.awt.Polygon;
import core.Explosion;
public class GUIExplosion extends Polygon {
	final static long serialVersionUID=2001;
	private int radius;
	private GUIPosition pos;
	private int[] x;
	private int[] y;
	GUIExplosion(Explosion explosion){
		radius=explosion.getRange();
		pos=new GUIPosition(explosion.getPosition());
		// form erzeugen
		int anzahlpunkte=(int)(radius*2*Math.PI)/4;
		double winkelabschnitt=2*Math.PI/anzahlpunkte;
		x=new int[anzahlpunkte];
		y=new int[anzahlpunkte];
		for(int i=0;i<anzahlpunkte;i++){
			double r=1;
			if(i%2==1)r=r*1.2;
			x[i]=(int)(radius*r*Math.cos(i*winkelabschnitt));
			y[i]=(int)(radius*r*Math.sin(i*winkelabschnitt));
		}

		// verschieben
		for(int i=0;i<anzahlpunkte;i++){
			x[i]=x[i]+pos.getX();
			if(x[i]<0)x[i]=0;
			y[i]=y[i]+pos.getY();
			if(y[i]<0)y[i]=0;
		}
		this.npoints=anzahlpunkte;
		this.xpoints=x;
		this.ypoints=y;
	}
}
