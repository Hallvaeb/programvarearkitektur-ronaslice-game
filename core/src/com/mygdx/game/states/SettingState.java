package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;

public class SettingState {
    private Texture img;

    public SettingState(GameStateManager gsm){
        super(gsm);
        img= new Texture( "bg_bare_himmel.png");

    }
}
