package core;

public class Missile extends FlightObject {
	private int detonationradius;
	private int speed=50;
	private int startrunden=3;
	Missile(){
		super();
	}
	
	public Position getPosition() {
		double actspeed=(speed*Math.exp(-(20/(clock+0.0000000001))));
		Position rel=target.subtract(r);
		double alpha=(actspeed*clock/rel.getLength());
		rel=rel.multiply(alpha);
		return 	r.add(rel);
	}
	public void setRange(int range){
		this.s=range;
	}

	public void setDetonationRadius(int r){
		this.detonationradius=r;
	}
	public int getDetonationRadius(){
		return detonationradius;
	}
	public boolean withinRange(Position abs){
		Position act=getPosition();
		Position rel=abs.subtract(act);
		Position rel2=r.subtract(act);
		if(rel.getLength()<detonationradius&&clock>startrunden||rel2.getLength()>s)
				return true;
		else 	return false;
	}
	
}
