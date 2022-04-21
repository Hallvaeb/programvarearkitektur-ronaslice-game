package com.mygdx.game.sprites;


public class Player {

    private int livesLeft;
    private int score;
    private String name;
    private int currentDifficulty;

    /**
     * Constructor for a player. Starts with 3 lives, 0 score and 0 difficulty.
     */
    public Player(){
        livesLeft = 3;
        score = 0;
        currentDifficulty = 0;
    }

    /**
     * A getter for lives left.
     * @return Amount of lives left.
     */
    public int getLivesLeft() {
        return livesLeft;
    }

    /**
     * Method to lose lives for a player.
     */
    public void loseLife() {
        if (livesLeft > 0) {
            livesLeft--;
        }
    }

    /**
     * Method to increase lives. Cant exceed 3 lives.
     */
    public void gainLife() {
        if (livesLeft > 0 && livesLeft < 3) {
            livesLeft++;
        }
    }

    /**
     * A getter for the player name.
     * @return The player name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets a new name for the player.
     * @param name The name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter for score.
     * @return The player score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increases score, checks for difficulty increase.
     * @return -1 if difficulty is not updated, otherwise the new difficulty score 0-10.
     **/
    public int increaseScoreAndDifficulty(double score) {
        this.score += score;
        int newDifficulty = getDifficulty();
        if(currentDifficulty != newDifficulty){
            currentDifficulty = newDifficulty;
            return newDifficulty;
        }
        else{
            return -1;
        }
    }

    /**
     * Help method to find the fitting difficulty.
     * @return A difficulty score 0-10.
     */
    private int getDifficulty() {
        for (int i = 100; i > 0; i -= 10) {
            if (score > i) {
                return i / 10;
            }
        }
        return 0;
    }
}
