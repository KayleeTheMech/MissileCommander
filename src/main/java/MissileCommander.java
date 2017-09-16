import core.Core;
import controller.Controller;
import gui.KampfschirmFenster;

public class MissileCommander {

    public static void main(String[] args) {
        Core core = new Core();
        Controller controller = new Controller(core);
        KampfschirmFenster gui = new KampfschirmFenster(core, controller, "Missile Commander");
        gui.setVisible(true);
        controller.resume();
    }
}
