package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class SingleplayerState extends State {
    private Texture playBtn;

    protected SingleplayerState(GameStateManager gsm) {
        super(gsm);
        playBtn = new Texture("badlogic.jpg");
    }

    @java.lang.Override
    protected void handleInput() {

    }

    @java.lang.Override
    public void update(float dt) {

    }

    @java.lang.Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playBtn, (MyGdxGame.WIDTH/2)-(playBtn.getWidth()/2), (MyGdxGame.HEIGHT/2)-(playBtn.getHeight()/2));
        sb.end();
    }

    @java.lang.Override
    public void dispose() {

    }
}
