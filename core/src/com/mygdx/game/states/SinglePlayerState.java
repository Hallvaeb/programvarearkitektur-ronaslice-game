package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.COV_alpha;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.sprites.COV_omikron;
import com.mygdx.game.sprites.Player;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.Syringe;
import com.mygdx.game.sprites.UFO;


public class SinglePlayerState extends State implements PlayState {
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private Array<UFO> ufos;
    private Texture bg = new Texture("background.png");
    private COV_delta cov_delta = new COV_delta(HEIGHT/10);
    private COV_omikron cov_omikron = new COV_omikron(HEIGHT/15);
    private COV_alpha cov_alpha = new COV_alpha(HEIGHT/23);
    private SickPerson sick_person = new SickPerson(HEIGHT/11);
    private Syringe syringe;
    private Texture health = new Texture("syringe.png");
    private Sprite pause = new Sprite(new Texture("pause.png"));
    private Player player;
    private BitmapFont font;
    private Vector3 touchPoint;

    /**
     * Operates during a single player game. Contains an Array (ufos) of the UFOs in the game.
     * This class notifies all it's subscribers (ufos) each time the score reaches 10, 20, 30 etc.,
     * making them all increase their difficulty (velocity).
     * @param gsm GameStateManager controlling the states of the application.
     */
    public SinglePlayerState(GameStateManager gsm){
        super(gsm);
        player = new Player();
        font = new BitmapFont();
        font.getData().setScale(HEIGHT/400f);
        syringe = Syringe.getInstance();
        pause.setSize(WIDTH/10f, Gdx.graphics.getHeight()/16f);
        pause.setPosition(WIDTH/1.15f,Gdx.graphics.getHeight()/1.08f);
        touchPoint = new Vector3();
        ufos = new Array<>();
        ufos.add(cov_delta, cov_omikron, sick_person, syringe);
        ufos.add(cov_alpha);
    }

    public void setUFODifficulty(int difficulty) {
        for (int i = 0; i < ufos.size; i++){
            ufos.get(i).setDifficulty(difficulty);
        }
    }

    public void gameOver(Player player) {
        syringe.reset();
        gsm.push(new GameOverState(gsm, player));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(),HEIGHT - Gdx.input.getY(),0);
            if (pause.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                MyGdxGame.sound.play();
                gsm.push(new PauseState(gsm));
            }

            // Slice from user
            for (UFO ufo : ufos) {
                if(ufo.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    if (ufo instanceof SickPerson) {
                        gameOver(player);
                        break;
                    }
                    repositionReduceCollisions(ufo);

                    if (ufo instanceof Syringe) {
                        player.gainLife();
                        if (player.getLivesLeft() == 3) {
                            syringe.setSpawnable(false);
                        }
                    }
                    else{
                        int difficulty = player.increaseScoreAndDifficulty(ufo.getPoints());
                        if(difficulty != -1){
                            setUFODifficulty(difficulty);
                        }
                    }
                }
            }
        }
    }

    public void repositionReduceCollisions(UFO ufo1) {
        ufo1.reposition();
        for (int i = 0; i < ufos.size; i++){
            UFO ufo2 = ufos.get(i);
            if(!(ufo1 == ufo2) &&
                    ufo1.getBoundingRectangle().contains(ufo2.getBoundingRectangle())){
                ufo1.reposition();
                repositionReduceCollisions(ufo1);
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        for (UFO ufo : ufos) {
            ufo.update(dt, player);
        }
        if (player.getLivesLeft() == 0) {
            gameOver(player);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, WIDTH, HEIGHT);
        font.draw(sb, "Score: " + player.getScore(), WIDTH/20f,
                0.85f*HEIGHT);
        for (int i = 0; i < player.getLivesLeft(); i++) {
            sb.draw(health, WIDTH/48f+i*WIDTH/8f,
                    0.9f*HEIGHT, HEIGHT/16, HEIGHT/16);
        }
        sb.draw(pause, pause.getX(),pause.getY(), pause.getWidth(), pause.getHeight());
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y,
                cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_alpha.getTexture(), cov_alpha.getPosition().x,cov_alpha.getPosition().y,
                cov_alpha.getSize(), cov_alpha.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y,
                cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y,
                sick_person.getSize(), sick_person.getSize());
        if (syringe.isSpawnable()) {
            sb.draw(syringe.getTexture(), syringe.getPosition().x, syringe.getPosition().y,
                    HEIGHT/16, HEIGHT/16);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        pause.getTexture().dispose();
        health.dispose();
        bg.dispose();
        font.dispose();
        for (UFO ufo:ufos){
            if (!(ufo instanceof Syringe)){
                ufo.dispose();
            }
        }
    }
}
