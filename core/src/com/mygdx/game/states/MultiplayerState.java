package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.COV_alpha;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.sprites.COV_omikron;
import com.mygdx.game.sprites.Player;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.Syringe;
import com.mygdx.game.sprites.UFO;

public class MultiplayerState extends State implements PlayState {

    // Array med alle UFO-objekter skjerm 1
    private Array<UFO> ufos1;
    private Array<UFO> ufos2;

    private Texture bg1 = new Texture("background.png");
    private Texture bg2 = new Texture("backgroundFlipped.png");
    private COV_delta cov_delta, cov_delta2;
    private COV_omikron cov_omikron, cov_omikron2;
    private COV_alpha cov_alpha, cov_alpha2;
    private SickPerson sick_person, sick_person2;
    private Syringe syringe;

    private Texture health = new Texture("syringe.png");
    private Sprite pause = new Sprite(new Texture("pause.png"));
    private Sprite pause2 = new Sprite(new Texture("pause.png"));
    private Player player1, player2;
    private BitmapFont font;

    private float time_passed = 0;

    private Vector3 touchPoint;
    private Vector3 touchPoint2;
    OrthographicCamera playerOneCamera;
    OrthographicCamera playerTwoCamera;


    protected MultiplayerState(GameStateManager gsm) {
        super(gsm);
        playerOneCamera = new OrthographicCamera();
        playerTwoCamera = new OrthographicCamera();
        playerOneCamera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        playerTwoCamera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        playerTwoCamera.rotate(180);



        ScreenViewport playerOneViewport = new ScreenViewport(playerOneCamera);
        ScreenViewport playerTwoViewport = new ScreenViewport(playerTwoCamera);
        playerOneViewport.update(MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        playerTwoViewport.update(MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        playerTwoViewport.setScreenY(MyGdxGame.HEIGHT/2);

        player1 = new Player();
        player2 = new Player();
        font = new BitmapFont();
        font.getData().setScale(3, 3);

        cov_delta = new COV_delta(80);
        cov_omikron = new COV_omikron(55);
        cov_alpha = new COV_alpha(35);
        sick_person = new SickPerson(70);
        syringe = Syringe.getInstance();

        cov_delta2 = new COV_delta(80);
        cov_omikron2 = new COV_omikron(55);
        cov_alpha2 = new COV_alpha(35);
        sick_person2 = new SickPerson(70);



        //Pause (Fikse hardkodet verdier)
        pause.setSize(50, 50);
        pause.setPosition(MyGdxGame.WIDTH-60,(MyGdxGame.HEIGHT/2)-60);
        pause2.setSize(50, 50);
        pause2.setPosition(19,416);

        // Slice posisjon til player
        touchPoint = new Vector3();
        touchPoint2 = new Vector3();

        ufos1 = new Array<>();
        ufos1.add(cov_delta, cov_omikron, sick_person, syringe);
        ufos1.add(cov_alpha);

        ufos2 = new Array<>();
        ufos2.add(cov_delta2, cov_omikron2, sick_person2, syringe);
        ufos2.add(cov_alpha2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(),MyGdxGame.HEIGHT - Gdx.input.getY(),0);
            touchPoint2.set(Gdx.input.getX(),MyGdxGame.HEIGHT - Gdx.input.getY(),0);
            if (pause.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                gsm.push(new PauseState(gsm));
            }
            if (pause2.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                gsm.push(new PauseState(gsm));
            }

            // Slice fra bruker.
            for (UFO ufo : ufos1) {

                System.out.println(touchPoint);

                if(ufo.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    if (ufo instanceof SickPerson) {
                        System.out.println("GAME OVER");

                        System.out.println("Gsm has set, back in singleplayer...? OVER");
                    }
                    else if (ufo instanceof Syringe) {
                        ufo.reposition();
                        player1.gainLife();
                        if (player1.getLivesLeft() == 3) {
                            syringe.setSpawnable(false);
                        }
                    }
                    else{
                        // One of the viruses are reposition
                        player1.increaseScoreAndDifficulty(ufo.getPoints());
                        ufo.reposition();
                    }
                }

            }
            for (UFO ufo : ufos2) {

                System.out.println(touchPoint2);

                if(ufo.getBoundingRectangle().contains(MyGdxGame.WIDTH - touchPoint2.x, MyGdxGame.HEIGHT - touchPoint2.y)) {
                    if (ufo instanceof SickPerson) {
                        System.out.println("GAME OVER");

                        System.out.println("Gsm has set, back in singleplayer...? OVER");
                    }
                    else if (ufo instanceof Syringe) {
                        ufo.reposition();
                        player2.gainLife();
                        if (player2.getLivesLeft() == 3) {
                            syringe.setSpawnable(false);
                        }
                    }
                    else{
                        // One of the viruses are reposition
                        player2.increaseScoreAndDifficulty(ufo.getPoints());
                        ufo.reposition();
                    }
                }

            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        time_passed += dt;
        for (UFO ufo : ufos1) {
            ufo.update(dt, player1);
        }
        if (player1.getLivesLeft() == 0) {
            // GAME OVER
            System.out.println("GAME OVER");
        }
        for (UFO ufo : ufos2) {
            ufo.update(dt, player2);
        }
        if (player2.getLivesLeft() == 0) {
            // GAME OVER
            System.out.println("GAME OVER");
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        /*sb.begin();
        sb.draw(bg1, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        sb.draw(bg2, 0, MyGdxGame.HEIGHT/2, MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y/2, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y/2, cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y/2, sick_person.getSize(), sick_person.getSize());

        sb.draw(cov_delta2.getTexture(), cov_delta2.getPosition().x,cov_delta2.getPosition().y/2, cov_delta2.getSize(), cov_delta2.getSize());
        sb.draw(cov_omikron2.getTexture(), cov_omikron2.getPosition().x,cov_omikron2.getPosition().y/2, cov_omikron2.getSize(), cov_omikron2.getSize());
        sb.draw(sick_person2.getTexture(), sick_person2.getPosition().x,sick_person2.getPosition().y/2, sick_person2.getSize(), sick_person2.getSize());

        sb.end();*/
        sb.setProjectionMatrix(playerOneCamera.combined);
        Gdx.gl.glViewport(0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT/2);
        sb.begin();
        sb.draw(bg1, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        for (int i = 0; i < player1.getLivesLeft(); i++) {
            // Gjøre disse piksel-verdiene ikke hardkodet.
            sb.draw(health, 10+i*60, (MyGdxGame.HEIGHT/2)-60, 50, 50);
        }
        font.draw(sb, "Score: " + player1.getScore(), MyGdxGame.WIDTH/2 -130, MyGdxGame.HEIGHT-120);
        sb.draw(pause, MyGdxGame.WIDTH-60,(MyGdxGame.HEIGHT/2)-60, 50, 50);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_alpha.getTexture(), cov_alpha.getPosition().x,cov_alpha.getPosition().y, cov_alpha.getSize(), cov_alpha.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y, cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y, sick_person.getSize(), sick_person.getSize());
        if (syringe.isSpawnable()) {
            sb.draw(syringe.getTexture(), syringe.getPosition().x, syringe.getPosition().y, syringe.getSize(), syringe.getSize());
        }
        sb.end();

        sb.setProjectionMatrix(playerTwoCamera.combined);
        Gdx.gl.glViewport(0,MyGdxGame.HEIGHT/2,MyGdxGame.WIDTH,MyGdxGame.HEIGHT/2);
        sb.begin();
        sb.draw(bg1, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        for (int i = 0; i < player2.getLivesLeft(); i++) {
            // Gjøre disse piksel-verdiene ikke hardkodet.
            sb.draw(health, 10+i*60, (MyGdxGame.HEIGHT/2)-60, 50, 50);
        }
        font.draw(sb, "Score: " + player2.getScore(), MyGdxGame.WIDTH/2 -130, MyGdxGame.HEIGHT-120);
        sb.draw(pause2, MyGdxGame.WIDTH-60,(MyGdxGame.HEIGHT/2)-60, 50, 50);
        sb.draw(cov_delta2.getTexture(), cov_delta2.getPosition().x,cov_delta2.getPosition().y, cov_delta2.getSize(), cov_delta2.getSize());
        sb.draw(cov_alpha2.getTexture(), cov_alpha2.getPosition().x,cov_alpha2.getPosition().y, cov_alpha2.getSize(), cov_alpha2.getSize());
        sb.draw(cov_omikron2.getTexture(), cov_omikron2.getPosition().x,cov_omikron2.getPosition().y, cov_omikron2.getSize(), cov_omikron2.getSize());
        sb.draw(sick_person2.getTexture(), sick_person2.getPosition().x,sick_person2.getPosition().y, sick_person2.getSize(), sick_person2.getSize());
        if (syringe.isSpawnable()) {
            sb.draw(syringe.getTexture(), syringe.getPosition().x, syringe.getPosition().y, syringe.getSize(), syringe.getSize());
        }
        sb.end();

    }

    @Override
    public void dispose() {
        pause.getTexture().dispose();
        health.dispose();
        bg1.dispose();
        font.dispose();
        for (UFO ufo:ufos1){
            ufo.dispose();
        }
    }

    @java.lang.Override
    public void setUFODifficulty(int difficulty) {

    }
}
