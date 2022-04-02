package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class State {
    protected Vector2 mouse;
    protected GameStateManager gsm;


    protected State(GameStateManager gsm){
        this.gsm = gsm;
        mouse = new Vector2();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
