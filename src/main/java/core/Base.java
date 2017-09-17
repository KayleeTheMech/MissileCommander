package core;

public class Base extends GameObject {
    private int score;

    Base(Position location) {
        super(location);
        this.score = 0;
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public int getScore() {
        return score;
    }

}
