package core;

public interface IFlightObject extends IGameObject {

    Position getTargetVector();

    void setTargetVector(Position tar);

}
