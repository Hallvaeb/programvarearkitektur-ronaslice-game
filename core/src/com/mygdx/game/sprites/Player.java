package com.mygdx.game.sprites;

public class Player {

    private int livesLeft;
    private float score;
    private String name;

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

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void increaseScore(double score) {
        this.score += score;
    }

}
