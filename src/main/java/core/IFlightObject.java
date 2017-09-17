package core;

public interface IFlightObject extends IGameObject {

    void setTargetVector(Position tar);

    Position getTargetVector();

}
