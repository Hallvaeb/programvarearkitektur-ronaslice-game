package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.MyGdxGame;
import java.util.LinkedList;
import java.util.List;


public class ScoreState extends State{

    private static final int MARGIN = 80;
    private Texture img;
    private List scores;
    private List names;
    private BitmapFont scoreFont;
    private BitmapFont nameFont;
    private BitmapFont titleFont;
    private BitmapFont nameTitleFont;
    private BitmapFont scoreTitleFont;
    private Sprite quitBtn;


    protected ScoreState(GameStateManager gsm) {
        super(gsm);
        img = new Texture("bg_bare_himmel.png");
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();

        titleFont = new BitmapFont();
        nameTitleFont = new BitmapFont();
        scoreTitleFont = new BitmapFont();
        nameFont = new BitmapFont();
        scoreFont = new BitmapFont();
        titleFont.getData().setScale(3, 3);

        // TODO: mekke "back"-button
        quitBtn = new Sprite(new Texture("quitBtn.png"));
        quitBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        quitBtn.setPosition(Gdx.graphics.getWidth()/2f-quitBtn.getWidth()/2, 10);

    }



    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MenuState(gsm));
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
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        titleFont.draw(sb, "Highscore! ", (Gdx.graphics.getWidth()/2)-100, Gdx.graphics.getHeight()-50);
        nameTitleFont.draw(sb, "NAME", 100, Gdx.graphics.getHeight()-150);
        scoreTitleFont.draw(sb, "SCORE", Gdx.graphics.getWidth()-150, Gdx.graphics.getHeight()-150);
        if (scores != null) {
            for (int i = 0; i < scores.size(); i++) {
                nameFont.draw(sb, "" + names.get(i), 100, Gdx.graphics.getHeight() - 200 - (i * 50));
                scoreFont.draw(sb, "" + scores.get(i), Gdx.graphics.getWidth() - 135, Gdx.graphics.getHeight() - 200 - (i * 50));
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
