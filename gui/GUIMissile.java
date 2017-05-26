package gui;
import java.awt.Polygon;
import core.Missile;

public class GUIMissile extends Polygon {
	static final long serialVersionUID=2001;
	GUIPosition direction;
	GUIPosition centerofmass;
	int laenge=25;
	int breite=10;

	
	GUIMissile(Missile missile){

		direction=new GUIPosition(missile.getTargetVector());
		centerofmass=new GUIPosition(missile.getPosition());
		int[] x={
			-(laenge*1/2),
			-(laenge*3/8),
			-(laenge*1/4),
			+(laenge*3/8),
			+(laenge*1/2),
			+(laenge*1/2),
			+(laenge*3/8),
			-(laenge*1/4),
			-(laenge/2*3/4)
		};
		int[] y={
			+0,
			-(breite*1/2),
			-(breite*1/4),
			-(breite*1/4),
			-(breite*1/2),
			(breite*1/2),
			(breite*1/4),
			(breite*1/4),
			(breite*1/2)
			};
		// drehen
		double alpha=centerofmass.getDrehWinkel(direction);
		for(int i=0;i<y.length;i++){
			int xold=x[i];
			int yold=y[i];
			x[i]=(int)(xold*Math.cos(alpha)-yold*Math.sin(alpha));
			y[i]=-(int)(xold*Math.sin(alpha)+yold*Math.cos(alpha));
		}
		// relativ verschieben
		for(int i=0;i<y.length;i++){
			x[i]=centerofmass.getX()+x[i];
			y[i]=centerofmass.getY()+y[i];
		}
		this.xpoints=x;
		this.ypoints=y;	
		this.npoints=9;
	}
}
