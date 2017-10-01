package core.gameObjects;

import core.Position;

public interface IFlightObject extends IGameObject {

    Position getTargetVector();

    void setTargetVector(Position tar);

}
