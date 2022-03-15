package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class PauseState extends State{
    private Texture play_btn;
    private String msg;
    private BitmapFont font;
    protected OrthographicCamera cam;
    protected State prev_state;

    public PauseState(GameStateManager gsm, String msg, State prev_state) {
        super(gsm);
        this.msg = msg;
        this.prev_state = prev_state;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        play_btn = new Texture("playbtn.png");
        font = new BitmapFont();
        font.setColor(0,0,0,1);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (prev_state instanceof SingleplayerState){
                //gsm.set(new SingleplayerState(gsm));
            }
            else if (prev_state instanceof SingleplayerState){
                //gsm.set(new MultiplayerState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(play_btn, cam.position.x - (play_btn.getWidth() / 2), cam.position.y);
        font.draw(sb, msg, MyGdxGame.WIDTH/2 - 100, (MyGdxGame.HEIGHT/4) + cam.position.y);
        sb.end();
    }

    @Override
    public void dispose(){
        play_btn.dispose();
    }
}
