package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;

public class MultiPlayerGameOverState extends State {

    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private static final int btnSize = WIDTH/4;
    private static final int btnMarginX = (WIDTH/2)-(btnSize/2);
    private static final int btnMarginY = HEIGHT-(HEIGHT/10)-(2*btnSize);
    private static final float textMarginX = (WIDTH/2f)-(WIDTH/3.1f);
    private static final float textMarginY = HEIGHT-(HEIGHT/10);

    private Player winner;
    private Texture bg;
    private BitmapFont winnerFont;
    private OrthographicCamera cam;
    private Sprite playAgainBtn;
    private Sprite returnBtn;


    protected MultiPlayerGameOverState(GameStateManager gsm, Player winner) {
        super(gsm);
        this.winner = winner;

        // Switching from split-screen back to normal
        cam = new OrthographicCamera();
        cam.setToOrtho(false, WIDTH, HEIGHT);

        bg = new Texture("bg_sky.png");
        winnerFont = new BitmapFont();
        winnerFont.setColor(0,0,0,1);
        winnerFont.getData().setScale(HEIGHT/200);
        playAgainBtn = new Sprite(new Texture("play_again_btn.png"));
        playAgainBtn.setSize(btnSize, btnSize);
        playAgainBtn.setPosition(btnMarginX, btnMarginY);
        returnBtn = new Sprite(new Texture("return.png"));
        returnBtn.setSize(btnSize, btnSize);
        returnBtn.setPosition(btnMarginX, btnMarginY - btnSize - (HEIGHT/80));
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playAgainBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MultiPlayerState(gsm));
            }
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())) {
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
        // Setting normal screen
        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glViewport(0,0, WIDTH, HEIGHT);
        sb.begin();
        sb.draw(bg, 0, 0, WIDTH, HEIGHT);
        sb.draw(playAgainBtn, playAgainBtn.getX(), playAgainBtn.getY(), WIDTH/4f, WIDTH/4f);
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), WIDTH/4f, WIDTH/4f);
        winnerFont.draw(sb, winner.getName()+" won!", textMarginX, textMarginY);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        winnerFont.dispose();
        playAgainBtn.getTexture().dispose();
        returnBtn.getTexture().dispose();
    }
}
