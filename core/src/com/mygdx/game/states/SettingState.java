package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingState extends State{
    private Texture img;

    public SettingState(GameStateManager gsm){
        super(gsm);
        img= new Texture( "bg_bare_himmel.png");

    }

    @java.lang.Override
    protected void handleInput() {

    }

    @java.lang.Override
    public void update(float dt) {

    }

    @java.lang.Override
    public void render(SpriteBatch sb) {

    }

    @java.lang.Override
    public void dispose() {

    }
}
