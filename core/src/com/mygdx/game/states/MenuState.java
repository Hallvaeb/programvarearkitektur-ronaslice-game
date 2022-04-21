package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

import com.mygdx.game.MyGdxGame;

public class MenuState extends State {
    private Sprite playBtn = new Sprite(new Texture("newGameButton.png"));
    private Sprite multiBtn = new Sprite(new Texture("multiplayerButton.png"));
    private Sprite scoreBtn = new Sprite(new Texture("highScoreButton.png"));
    private Sprite settingBtn = new Sprite(new Texture("settingsButton.png"));
    private Sprite helpBtn = new Sprite(new Texture("tutorialButton.png"));
    private Texture background = new Texture("bg_bare_himmel.png");;
    private int MARGIN = 80;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        playBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()- MARGIN)-playBtn.getHeight());
        multiBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        multiBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()- MARGIN)-2*playBtn.getHeight());
        scoreBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        scoreBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()- MARGIN)-3*playBtn.getHeight());
        settingBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        settingBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()- MARGIN)-4*playBtn.getHeight());
        helpBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        helpBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()- MARGIN)-5*playBtn.getHeight());
    }

    @Override
    public void handleInput() {
        MyGdxGame.get_FBIC().SomeFunction();
        if (Gdx.input.justTouched()) {
            if (playBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new SinglePlayerState(gsm));
            }
            if (multiBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MultiplayerState(gsm));
            }
            if (scoreBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new ScoreState(gsm));
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new SettingState(gsm));
            }
            if (helpBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.push(new TutorialState(gsm));
            }
            /*
            if (multiBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.set(new MultiplayerState(gsm));
            }
            */
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glViewport(0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playBtn, playBtn.getX(), playBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(multiBtn, multiBtn.getX(), multiBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(scoreBtn, scoreBtn.getX(), scoreBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(helpBtn, helpBtn.getX(), helpBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
