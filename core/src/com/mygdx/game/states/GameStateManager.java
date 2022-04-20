package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import java.util.Arrays;
import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
        System.out.println("state is PUSHED, states now is: "+ Arrays.toString(states.toArray()));
    }

    public void pop(){
        states.pop().dispose();
        System.out.println("state is POPPED, states now is: "+ Arrays.toString(states.toArray()));

    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
        System.out.println("state is SET, states now is: "+ Arrays.toString(states.toArray()));
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
