package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

import java.util.List;


public class ScoreState extends State{

    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private static final int btnSize = WIDTH/4;
    private static final int btnMarginX = (WIDTH/2)-(btnSize/2);
    private static final int btnMarginY = HEIGHT/80;
    private static final int titleSize = WIDTH/160;
    private static final int fontSize = WIDTH/480;
    private static final int titleMarginX = (WIDTH/2)-(HEIGHT/9);
    private static final int titleMarginY = HEIGHT-(HEIGHT/16);
    private static final int nameMarginX = HEIGHT/8;
    private static final float nameMarginY = HEIGHT-(WIDTH/3.2f);
    private static final float scoreMarginX = WIDTH-(WIDTH/3.2f);
    private static final float scoreMarginY = HEIGHT-(WIDTH/3.2f);

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
        /** Initializing the lists "names" and "scores" with Firebase getters */
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();

        titleFont = new BitmapFont();
        nameTitleFont = new BitmapFont();
        scoreTitleFont = new BitmapFont();
        nameFont = new BitmapFont();
        scoreFont = new BitmapFont();

        titleFont.getData().setScale(titleSize, titleSize);
        nameTitleFont.getData().setScale(fontSize, fontSize);
        scoreTitleFont.getData().setScale(fontSize, fontSize);
        nameFont.getData().setScale(fontSize, fontSize);
        scoreFont.getData().setScale(fontSize, fontSize);

        quitBtn = new Sprite(new Texture("return.png"));
        quitBtn.setSize(btnSize, btnSize);
        quitBtn.setPosition(btnMarginX, btnMarginY);

    }



    @Override
    protected void handleInput() {
        /**
         * Updating the lists "names" and "scores",
         * since it might take som time before Firebase returns the data
         */
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();
        if (Gdx.input.isTouched()) {
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
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
        sb.draw(img,0, 0, WIDTH, HEIGHT);
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), btnSize, btnSize);
        titleFont.draw(sb, "Highscore! ", titleMarginX, titleMarginY);
        nameTitleFont.draw(sb, "NAME", nameMarginX, nameMarginY);
        scoreTitleFont.draw(sb, "SCORE", scoreMarginX, scoreMarginY);
        if (scores != null) {
            for (int i = 0; i < scores.size(); i++) {
                nameFont.draw(sb, "" + names.get(i), nameMarginX, HEIGHT-(HEIGHT/4f)-(i * (HEIGHT/16f)));
                scoreFont.draw(sb, "" + scores.get(i), WIDTH-(WIDTH/3.56f), HEIGHT-(HEIGHT/4f)-(i * (HEIGHT/16f)));
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        scoreFont.dispose();
        nameFont.dispose();
        titleFont.dispose();
        nameTitleFont.dispose();
        scoreTitleFont.dispose();
        quitBtn.getTexture().dispose();
    }
}
