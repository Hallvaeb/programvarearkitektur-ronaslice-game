package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.Stack;

/**
 * Keeps track of which state should be updated and drawn, and keeps the states when entering
 * settings and pause.
 *
 * Printing states can be done so (ex):
 * System.out.println("state is PUSHED, states now is: "+ Arrays.toString(states.toArray()));
 */
public class GameStateManager {
    private Stack<State> states;
    public GameStateManager(){
        states = new Stack<>();
    }

    /**
     * Puts the state on top of the states stack.
     * @param state
     */
    public void push(State state){
        states.push(state);
    }

    /**
     * Removes the state on top of the states stack.
     */
    public void pop(){
        states.pop().dispose();
    }

    /**
     * Pops the current state and pushes the input state.
     * @param state
     */
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    /**
     * Updates the current state.
     * @param dt
     */
    public void update(float dt){
        states.peek().update(dt);
    }

    /**
     * Renders the current state.
     * @param sb
     */
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
