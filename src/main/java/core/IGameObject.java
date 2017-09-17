package core;

public interface IGameObject {

    boolean isAlive();

    void kill();

    void setPosition(Position r);

    Position getPosition();
}
