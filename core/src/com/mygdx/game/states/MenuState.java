package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

public class MenuState extends State {
    private Vector2 touch;
    private Sprite playBtn;
    private Sprite multiBtn;
    private Sprite scoreBtn;
    private Sprite settingBtn;
    private Sprite helpBtn;
    private Texture img;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        img = new Texture("bg_bare_himmel.png");
        playBtn = new Sprite(new Texture("heli1.png"));
        multiBtn = new Sprite(new Texture("heli1.png"));
        scoreBtn = new Sprite(new Texture("heli1.png"));
        settingBtn = new Sprite(new Texture("heli1.png"));
        helpBtn = new Sprite(new Texture("heli1.png"));

        playBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()-2*playBtn.getHeight());
        multiBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()-4*playBtn.getHeight());
        scoreBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()-6*playBtn.getHeight());
        settingBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()-8*playBtn.getHeight());
        helpBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()-10*playBtn.getHeight());

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (playBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.set(new SingleplayerState(gsm));
            }
            if (multiBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.set(new MultiplayerState(gsm));
            }
           if (scoreBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.set(new ScoreState(gsm));
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.set(new SettingState(gsm));
            }
            if (helpBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.set(new HelpState(gsm));
            }
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
        sb.draw(playBtn, playBtn.getX(), playBtn.getY());
        sb.draw(multiBtn, multiBtn.getX(), multiBtn.getY());
        sb.draw(scoreBtn, scoreBtn.getX(), scoreBtn.getY());
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY());
        sb.draw(helpBtn, helpBtn.getX(), helpBtn.getY());
        sb.end();
    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
