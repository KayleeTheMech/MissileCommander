package core;

public class Base extends GameObject {
    private int score;
    private boolean dead;
    
    Base(Position location) {
        super(location);
        this.score = 0;
        this.dead = false;
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public int getScore() {
        return score;
    }

    public boolean alive() {
        return !dead;
    }

    public void impact() {
        dead = true;
    }
}
