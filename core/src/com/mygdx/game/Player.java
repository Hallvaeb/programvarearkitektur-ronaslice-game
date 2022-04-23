package com.mygdx.game;

import com.mygdx.game.states.PlayState;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private int livesLeft = 3;
    private int score = 0;
    private String name;
    private int currentDifficulty;
    private List<PlayState> observers = new ArrayList<>();

    /**
     * Constructor for a player. Starts with 3 lives, 0 score and 0 difficulty.
     */
    public Player(){
        // This value can be modified to easily increase what score the players should start on.
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
     **/
    public void increaseScoreAndDifficulty(double score) {
        this.score += score;
        int newDifficulty = getDifficulty();
        if(currentDifficulty != newDifficulty){
            currentDifficulty = newDifficulty;
            updateObservers();
        }
    }

    /**
     * This updates all the states, which in this app will be only one state, but it's made this
     * way to be easily scalable.
     */
    private void updateObservers() {
        for (PlayState observer : observers){
            observer.observerUpdate();
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

    public void attach(PlayState state) {
        observers.add(state);
    }
}
