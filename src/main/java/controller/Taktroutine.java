package controller;

import java.util.TimerTask;

public class Taktroutine extends TimerTask {
	Controller contr;
	Taktroutine(Controller contr){
		this.contr=contr;
	}
	public void run() {
		contr.tick();
	}

}
