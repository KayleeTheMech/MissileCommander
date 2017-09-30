import controller.Controller;
import core.Core;
import gui.GameFrame;

public class MissileCommander {

    public static void main(String[] args) {
        Core core = new Core();
        Controller controller = new Controller(core);
        GameFrame gui = new GameFrame(core, controller, "Missile Commander");
        gui.setVisible(true);
        controller.resume();
    }
}
