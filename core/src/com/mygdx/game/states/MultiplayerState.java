package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private static final int deltaSize = WIDTH/6;
    private static final int omikronSize = WIDTH/9;
    private static final int alphaSize = WIDTH/14;
    private static final int personSize = WIDTH/7;
    private static final int pauseSize = WIDTH/10;
    private static final int fontSize = WIDTH/160;
    private static final float pause1MarginX = WIDTH-(WIDTH/8);
    private static final float pause1MarginY = (HEIGHT/2)-(pauseSize);
    private static final float pause2MarginX = (WIDTH - pause1MarginX - pauseSize);
    private static final float pause2MarginY = (HEIGHT/2);


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
    private int currentDifficulty;

    private float time_passed = 0;

    private Vector3 touchPoint;
    private Vector3 touchPoint2;
    OrthographicCamera playerOneCamera;
    OrthographicCamera playerTwoCamera;


    protected MultiplayerState(GameStateManager gsm) {
        super(gsm);
        playerOneCamera = new OrthographicCamera();
        playerTwoCamera = new OrthographicCamera();
        playerOneCamera.setToOrtho(false, WIDTH, HEIGHT/2);
        playerTwoCamera.setToOrtho(false, WIDTH, HEIGHT/2);
        playerTwoCamera.rotate(180);



        ScreenViewport playerOneViewport = new ScreenViewport(playerOneCamera);
        ScreenViewport playerTwoViewport = new ScreenViewport(playerTwoCamera);
        playerOneViewport.update(WIDTH, HEIGHT/2);
        playerTwoViewport.update(WIDTH, HEIGHT/2);
        playerTwoViewport.setScreenY(HEIGHT/2);

        player1 = new Player();
        player1.setName("Player 1");
        player2 = new Player();
        player2.setName("Player 2");
        font = new BitmapFont();
        font.getData().setScale(fontSize, fontSize);

        cov_delta = new COV_delta(deltaSize);
        cov_omikron = new COV_omikron(omikronSize);
        cov_alpha = new COV_alpha(alphaSize);
        sick_person = new SickPerson(personSize);
        syringe = Syringe.getInstance();

        cov_delta2 = new COV_delta(deltaSize);
        cov_omikron2 = new COV_omikron(omikronSize);
        cov_alpha2 = new COV_alpha(alphaSize);
        sick_person2 = new SickPerson(personSize);



        //Pause (Fikse hardkodet verdier)
        pause.setSize(pauseSize, pauseSize);
        pause.setPosition(pause1MarginX,pause1MarginY);
        pause2.setSize(pauseSize, pauseSize);
        pause2.setPosition(pause2MarginX,pause2MarginY);

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
            touchPoint.set(Gdx.input.getX(),HEIGHT - Gdx.input.getY(),0);
            touchPoint2.set(Gdx.input.getX(),HEIGHT - Gdx.input.getY(),0);
            if (pause.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                MyGdxGame.sound.play();
                gsm.push(new PauseState(gsm));
            }
            if (pause2.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                MyGdxGame.sound.play();
                gsm.push(new PauseState(gsm));
            }

            // User 1
            for (UFO ufo : ufos1) {
                if(ufo.getBoundingRectangle().contains(touchPoint.x, touchPoint.y) && touchPoint.y < HEIGHT/2) {
                    if (ufo instanceof SickPerson) {
                        System.out.println("GAME OVER");
                        gameOver(player2);
                        break;
                    }
                    else if (ufo instanceof Syringe) {
                        ufo.reposition();
                        player1.gainLife();
                        if (player1.getLivesLeft() == 3) {
                            syringe.setSpawnable(false);
                        }
                    }
                    else{
                        int difficulty = player1.increaseScoreAndDifficulty(ufo.getPoints());
                        ufo.reposition();
                        System.out.println(difficulty);
                        if(difficulty != -1 && difficulty != currentDifficulty){
                            setUFODifficulty(difficulty);
                        }
                    }
                }

            }

            // User 2
            for (UFO ufo : ufos2) {
                if(ufo.getBoundingRectangle().contains(WIDTH - touchPoint.x, HEIGHT - touchPoint.y) && touchPoint.y > HEIGHT/2) {
                    if (ufo instanceof SickPerson) {
                        System.out.println("GAME OVER");
                        gameOver(player1);
                        break;
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
                        int difficulty = player2.increaseScoreAndDifficulty(ufo.getPoints());
                        ufo.reposition();
                        if(difficulty != -1 && difficulty != currentDifficulty){
                            setUFODifficulty(difficulty);
                        }
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
            gameOver(player2);
        }
        for (UFO ufo : ufos2) {
            ufo.update(dt, player2);
        }
        if (player2.getLivesLeft() == 0) {
            // GAME OVER
            System.out.println("GAME OVER");
            gameOver(player1);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(playerOneCamera.combined);
        Gdx.gl.glViewport(0,0,WIDTH,HEIGHT/2);
        sb.begin();
        sb.draw(bg1, 0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < player1.getLivesLeft(); i++) {
            // Gjøre disse piksel-verdiene ikke hardkodet.
            sb.draw(health, (WIDTH/32)+i*(WIDTH/10), (HEIGHT/2)-(WIDTH/10), WIDTH/12, WIDTH/12);
        }
        sb.draw(pause, pause1MarginX,pause1MarginY, pauseSize, pauseSize);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_alpha.getTexture(), cov_alpha.getPosition().x,cov_alpha.getPosition().y, cov_alpha.getSize(), cov_alpha.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y, cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y, sick_person.getSize(), sick_person.getSize());
        if (syringe.isSpawnable()) {
            sb.draw(syringe.getTexture(), syringe.getPosition().x, syringe.getPosition().y, syringe.getSize(), syringe.getSize());
        }
        sb.end();

        sb.setProjectionMatrix(playerTwoCamera.combined);
        Gdx.gl.glViewport(0,HEIGHT/2, WIDTH,HEIGHT/2);
        sb.begin();
        sb.draw(bg1, 0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < player2.getLivesLeft(); i++) {
            // Gjøre disse piksel-verdiene ikke hardkodet.
            sb.draw(health, (WIDTH/32)+i*(WIDTH/10), (HEIGHT/2)-(WIDTH/10), WIDTH/12, WIDTH/12);
        }
        sb.draw(pause2, pause1MarginX, pause1MarginY, pauseSize, pauseSize);
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
        for (UFO ufo:ufos2){
            ufo.dispose();
        }
    }

    @Override
    public void setUFODifficulty(int difficulty) {
        for (int i = 0; i < ufos1.size; i++){
            ufos1.get(i).setDifficulty(difficulty);
        }
        for (int i = 0; i < ufos2.size; i++){
            ufos2.get(i).setDifficulty(difficulty);
        }
    }

    @Override
    public void gameOver(Player player) {
        Syringe.getInstance().reset();
        gsm.push(new MultiPlayerGameOverState(gsm, player));
    }
}
