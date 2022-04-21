package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Player;

public class MultiPlayerGameOverState extends State {

    private Player winner;
    private Texture bg;
    private BitmapFont winnerFont;
    private OrthographicCamera cam;
    private Sprite playAgainBtn;
    private Sprite returnBtn;


    protected MultiPlayerGameOverState(GameStateManager gsm, Player winner) {
        super(gsm);
        this.winner = winner;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);

        bg = new Texture("bg_bare_himmel.png");
        winnerFont = new BitmapFont();
        winnerFont.setColor(0,0,0,1);
        winnerFont.getData().setScale(4f);
        playAgainBtn = new Sprite(new Texture("multiplayerButton.png"));
        playAgainBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        playAgainBtn.setPosition(Gdx.graphics.getWidth()/2f-playAgainBtn.getWidth()/2, (Gdx.graphics.getHeight()- 80)-2*playAgainBtn.getHeight());
        returnBtn = new Sprite(new Texture("return.png"));
        returnBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        returnBtn.setPosition(Gdx.graphics.getWidth()/2f-returnBtn.getWidth()/2, playAgainBtn.getY()- playAgainBtn.getHeight()-10);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (playAgainBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MultiplayerState(gsm));
            }
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
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
        sb.setProjectionMatrix(cam.combined);
        Gdx.gl.glViewport(0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
        sb.begin();
        sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playAgainBtn, playAgainBtn.getX(), playAgainBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        winnerFont.draw(sb, winner.getName()+" won!", (MyGdxGame.WIDTH/2f)-175, MyGdxGame.HEIGHT-80);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
