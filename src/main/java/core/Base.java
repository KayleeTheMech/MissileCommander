package core;

public class Base {
    private int score;
    private Position location;
    private boolean dead;


    Base(Position location) {
        this.location = location;
        this.score = 0;
        this.dead = false;
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public int getScore() {
        return score;
    }

    public Position getPosition() {
        return location;
    }

    public boolean alive() {
        return !dead;
    }

    public void impact() {
        dead = true;
    }
}
