package core;

public class Base {
    private int score;
    private Position loc;
    private boolean dead;


    Base(Position loc) {
        this.loc = loc;
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
        return loc;
    }

    public boolean alive() {
        return !dead;
    }

    public void impact() {
        dead = true;
    }
}
