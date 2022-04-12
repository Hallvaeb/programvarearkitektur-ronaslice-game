package com.mygdx.game.sprites;

public class Player {

    private int livesLeft;
    private float score;

    public Player(){
        livesLeft = 3;
        score = 0;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void loseLife() {
        if (livesLeft > 0) {
            livesLeft--;
        }
    }
    public void gainLife() {
        if (livesLeft > 0 && livesLeft < 3) {
            livesLeft++;
        }
    }

    public float getScore() {
        return score;
    }

    public void increaseScore(float score) {
        this.score += score;
    }

}
