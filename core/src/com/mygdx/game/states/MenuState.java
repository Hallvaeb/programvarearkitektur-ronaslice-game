package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {
    private Texture playBtn;
    private Texture multiBtn;
    private Texture scoreBtn;
    private Texture settingBtn;
    private Texture helpBtn;
    private Texture img;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        img = new Texture("bg_bare_himmel.png");
        playBtn = new Texture("heli1.png");


    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new SingleplayerState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,0, 0);
        sb.draw(playBtn, (MyGdxGame.WIDTH/2)-(playBtn.getWidth()/2), (MyGdxGame.HEIGHT/2)-(playBtn.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
    }
}
