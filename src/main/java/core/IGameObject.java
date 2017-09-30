package core;

public interface IGameObject {

    boolean isAlive();

    void kill();

    Position getPosition();

    void setPosition(Position r);
}
