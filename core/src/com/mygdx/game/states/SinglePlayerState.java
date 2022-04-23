package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.Syringe;
import com.mygdx.game.sprites.UFO;
import com.mygdx.game.sprites.Virus;


public class SinglePlayerState extends State implements PlayState {
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private Array<UFO> ufos;
    private Texture bg = new Texture("background.png");
    private Texture cov_delta_texture = new Texture("cov_delta_sheet.png");
    private Texture cov_omicron_texture = new Texture("cov_omicron_sheet.png");
    private Texture cov_alpha_texture = new Texture("cov_alpha_sheet.png");
    private Virus cov_delta = new Virus(HEIGHT/10, 1, cov_delta_texture);
    private Virus cov_omicron = new Virus(HEIGHT/15, 2, cov_omicron_texture);
    private Virus cov_alpha = new Virus(HEIGHT/23, 3, cov_alpha_texture);
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
        // This classes subject (observer pattern)
        player = new Player();
        player.attach(this);

        font = new BitmapFont();
        font.getData().setScale(HEIGHT/400f);
        syringe = Syringe.getInstance();
        pause.setSize(WIDTH/10f, Gdx.graphics.getHeight()/16f);
        pause.setPosition(WIDTH/1.15f,Gdx.graphics.getHeight()/1.08f);
        touchPoint = new Vector3();
        ufos = new Array<>();
        ufos.add(cov_delta, cov_omicron, sick_person, syringe);
        ufos.add(cov_alpha);
    }

    public void setUFODifficulty(int difficulty) {

    }

    public void gameOver(Player player) {
        syringe.reset();
        gsm.push(new GameOverState(gsm, player));
    }

    /**
     * Increases difficulty of all UFOs once a players score determines so.
     */
    @Override
    public void observerUpdate() {
        for (int i = 0; i < ufos.size; i++){
            ufos.get(i).increaseDifficulty();
        }
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
                        player.increaseScoreAndDifficulty(ufo.getPoints());
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
        sb.draw(cov_omicron.getTexture(), cov_omicron.getPosition().x,cov_omicron.getPosition().y,
                cov_omicron.getSize(), cov_omicron.getSize());
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
        cov_delta_texture.dispose();
        cov_alpha_texture.dispose();
        cov_omicron_texture.dispose();
    }
}
