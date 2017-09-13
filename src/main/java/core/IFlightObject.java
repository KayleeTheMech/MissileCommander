package core;

public interface IFlightObject {
    public void setInitialCoordinates(Position r);

    public void setTargetVector(Position tar);

    public Position getTargetVector();

    public Position getPosition();
}
