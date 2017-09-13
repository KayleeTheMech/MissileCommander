package core;

import java.util.Observable;
import java.util.Observer;

public class Explosion implements Observer {

	private int range;
	private int ticks;
	private Position r;
	private double decayconstant=0.125;
	Explosion(int range, Position r){
		this.r=r;
		this.range=range;
		this.ticks=0;
	}
	public int getTicks(){
		return ticks;
	}
	public Position getPosition(){
		return r;
	}
	public int getRange(){
		return range;
	}
	
	public boolean withinRange(Position abs){
		Position rel=new Position(abs.getX()-r.getX(),abs.getY()-r.getY());
		if(Math.sqrt(rel.getX()*rel.getX()+rel.getY()*rel.getY())<range)
				return true;
		else 	return false;
	}
	
	public void update(Observable arg0, Object arg1) {
		if(ticks>2)range=(int)(range-ticks*decayconstant*range);
		else if(ticks==1||ticks==2)range=(int)(range*1.2);
		ticks++;
		}

}
