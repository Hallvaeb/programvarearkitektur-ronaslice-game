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

    public void looseLife() {
        if (livesLeft > 0) {
            livesLeft--;
        }
    }

    public float getScore() {
        return score;
    }

    public void increaseScore(float score) {
        this.score += score;
    }

}
