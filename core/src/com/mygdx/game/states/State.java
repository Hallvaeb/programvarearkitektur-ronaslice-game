package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/** Abstract class for all states and declaration/initialization of GameStateManager */
public abstract class State {
    protected GameStateManager gsm;


    protected State(GameStateManager gsm){
        this.gsm = gsm;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
