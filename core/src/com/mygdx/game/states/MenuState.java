package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import com.mygdx.game.MyGdxGame;

public class MenuState extends State {

    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private static final float MARGIN = Gdx.graphics.getWidth()/6;
    private static final float btnSize = WIDTH/4;
    private static final float btnMarginX = (WIDTH/2)-(btnSize/2);
    private static final float btnMarginY = HEIGHT-MARGIN;
    private Sprite playBtn = new Sprite(new Texture("new_game_btn.png"));
    private Sprite multiBtn = new Sprite(new Texture("multiplayer_btn.png"));
    private Sprite scoreBtn = new Sprite(new Texture("highscore_btn.png"));
    private Sprite settingBtn = new Sprite(new Texture("settings_btn.png"));
    private Sprite helpBtn = new Sprite(new Texture("tutorial_btn.png"));
    private Texture background = new Texture("bg_sky.png");;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playBtn.setSize(btnSize, btnSize);
        playBtn.setPosition(btnMarginX, btnMarginY-btnSize);
        multiBtn.setSize(btnSize, btnSize);
        multiBtn.setPosition(btnMarginX, btnMarginY-(2*btnSize));
        scoreBtn.setSize(btnSize, btnSize);
        scoreBtn.setPosition(btnMarginX, btnMarginY-(3*btnSize));
        settingBtn.setSize(btnSize, btnSize);
        settingBtn.setPosition(btnMarginX, btnMarginY-(4*btnSize));
        helpBtn.setSize(btnSize, btnSize);
        helpBtn.setPosition(btnMarginX, btnMarginY-(5*btnSize));

    }

    @Override
    public void handleInput() {
        // Method for updating nameList and scoreList with the data from Firebase
        MyGdxGame.get_FBIC().SetTop10Lists();
        if (Gdx.input.justTouched()) {
            if (playBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new SinglePlayerState(gsm));
            }
            if (multiBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MultiplayerState(gsm));
            }
            if (scoreBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new ScoreState(gsm));
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new SettingState(gsm));
            }
            if (helpBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new TutorialState(gsm));
            }
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glViewport(0,0, WIDTH, HEIGHT);
        sb.begin();
        sb.draw(background, 0, 0, WIDTH, HEIGHT);
        sb.draw(playBtn, playBtn.getX(), playBtn.getY(), btnSize, btnSize);
        sb.draw(multiBtn, multiBtn.getX(), multiBtn.getY(), btnSize, btnSize);
        sb.draw(scoreBtn, scoreBtn.getX(), scoreBtn.getY(), btnSize, btnSize);
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY(), btnSize, btnSize);
        sb.draw(helpBtn, helpBtn.getX(), helpBtn.getY(), btnSize, btnSize);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.getTexture().dispose();
        multiBtn.getTexture().dispose();
        scoreBtn.getTexture().dispose();
        settingBtn.getTexture().dispose();
        helpBtn.getTexture().dispose();
    }
}
