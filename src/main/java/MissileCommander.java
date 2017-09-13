import core.Core;
import controller.Controller;
import gui.KampfschirmFenster;

public class MissileCommander {


    public static void main(String[] args) {
        Core kern;
        Controller controller;
        KampfschirmFenster gui;
        kern = new Core();
        controller = new Controller(kern);
        gui = new KampfschirmFenster(kern, controller, "Missile Commander");
        gui.setVisible(true);
        controller.resume();
    }

}
