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
    private Array<UFO> ufos;
    private Texture bg = new Texture("background.png");
    private COV_delta cov_delta;
    private COV_omikron cov_omikron;
    private COV_alpha cov_alpha;
    private SickPerson sick_person;
    private Syringe syringe;
    private Texture health = new Texture("syringe.png");
    private Sprite pause = new Sprite(new Texture("pause.png"));
    private Player player;
    private BitmapFont font;
    private Vector3 touchPoint;

    public SinglePlayerState(GameStateManager gsm){
        super(gsm);
        player = new Player();
        font = new BitmapFont();
        font.getData().setScale(Gdx.graphics.getHeight()/400f);

        cov_delta = new COV_delta(80);
        cov_omikron = new COV_omikron(55);
        cov_alpha = new COV_alpha(35);
        sick_person = new SickPerson(70);
        syringe = Syringe.getInstance();

        //Pause
        pause.setSize(Gdx.graphics.getWidth()/10f, Gdx.graphics.getHeight()/16f);
        pause.setPosition(Gdx.graphics.getWidth()-60,Gdx.graphics.getHeight()-60);

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

    private void gameOver() {
        Syringe.getInstance().reset();
        gsm.push(new GameOverState(gsm, player));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(),MyGdxGame.HEIGHT - Gdx.input.getY(),0);
            if (pause.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                gsm.push(new PauseState(gsm));
            }

            // Slice from user
            for (UFO ufo : ufos) {
                if(ufo.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    if (ufo instanceof SickPerson) {
                        gameOver();
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
                        // One of the viruses are sliced, should difficulty increase?
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
            if(!(ufo1 == ufo2) && ufo1.getBoundingRectangle().contains(ufo2.getBoundingRectangle())){
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
            gameOver();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        font.draw(sb, "Score: " + player.getScore(), MyGdxGame.WIDTH/20f,
                0.85f*MyGdxGame.HEIGHT);
        for (int i = 0; i < player.getLivesLeft(); i++) {
            sb.draw(health, Gdx.graphics.getWidth()/48f+i*Gdx.graphics.getWidth()/8f,
                    0.9f*Gdx.graphics.getHeight(), 50, 50);
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
                    syringe.getSize(), syringe.getSize());
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
