import core.Core;
import controller.Controller;
import gui.KampfschirmFenster;
public class MissileCommander {


	public static void main(String[] args) {
		Core kern;
		Controller contr;
		KampfschirmFenster gui;
		kern=new Core();
		contr=new Controller(kern);
		gui=new KampfschirmFenster(kern,contr,"Missile Commander");
		gui.setVisible(true);
		contr.resume();
	}

}
