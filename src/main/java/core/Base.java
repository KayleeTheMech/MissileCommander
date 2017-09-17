package core;

public class Base extends GameObject {
    private int score;

    Base() {
        super();
        this.score = 0;
    }

    public void addScore(int score) {
        this.score = this.score + score;
    }

    public int getScore() {
        return score;
    }

}
