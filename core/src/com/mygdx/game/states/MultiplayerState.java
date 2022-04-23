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
import com.mygdx.game.Player;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.Syringe;
import com.mygdx.game.sprites.UFO;
import com.mygdx.game.sprites.Virus;

public class MultiplayerState extends State implements PlayState {
    private static final int WIDTH = Gdx.graphics.getWidth();
    private static final int HEIGHT = Gdx.graphics.getHeight();
    private static final int deltaSize = WIDTH/6;
    private static final int omicronSize = WIDTH/9;
    private static final int alphaSize = WIDTH/14;
    private static final int personSize = WIDTH/7;
    private static final int pauseSize = WIDTH/10;
    private static final int fontSize = WIDTH/160;
    private static final float pause1MarginX = WIDTH-(WIDTH/8);
    private static final float pause1MarginY = (HEIGHT/2)-(pauseSize);
    private static final float pause2MarginX = (WIDTH - pause1MarginX - pauseSize);
    private static final float pause2MarginY = (HEIGHT/2);



    private Array<UFO> ufos1; /** Array of ufos for screen 1 */
    private Array<UFO> ufos2; /** Array of ufos for screen 2 */

    private Texture bg1 = new Texture("background.png");
    private Texture cov_delta_texture = new Texture("cov_delta_sheet.png");
    private Texture cov_omicron_texture = new Texture("cov_omicron_sheet.png");
    private Texture cov_alpha_texture = new Texture("cov_alpha_sheet.png");
    private Virus cov_delta, cov_delta2;
    private Virus cov_omicron, cov_omicron2;
    private Virus cov_alpha, cov_alpha2;
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

        // Initializing the two players with the names "Player 1" and "Player 2"
        player1 = new Player();
        player1.setName("Player 1");
        player2 = new Player();
        player2.setName("Player 2");

        // Initializing the rest of the variables declared
        font = new BitmapFont();
        font.getData().setScale(fontSize, fontSize);

        cov_delta = new Virus(deltaSize, 1, cov_delta_texture);
        cov_omicron = new Virus(omicronSize, 2, cov_omicron_texture);
        cov_alpha = new Virus(alphaSize, 3, cov_alpha_texture);
        sick_person = new SickPerson(personSize);
        syringe = Syringe.getInstance();

        cov_delta2 = new Virus(deltaSize, 1, cov_delta_texture);
        cov_omicron2 = new Virus(omicronSize, 2, cov_omicron_texture);
        cov_alpha2 = new Virus(alphaSize, 3, cov_alpha_texture);
        sick_person2 = new SickPerson(personSize);

        pause.setSize(pauseSize, pauseSize);
        pause.setPosition(pause1MarginX,pause1MarginY);
        pause2.setSize(pauseSize, pauseSize);
        pause2.setPosition(pause2MarginX,pause2MarginY);

        // Slice posisjon til player
        touchPoint = new Vector3();
        touchPoint2 = new Vector3();

        // Adding the covid variants to the two lists of ufos
        ufos1 = new Array<>();
        ufos1.add(cov_delta, cov_omicron, sick_person, syringe);
        ufos1.add(cov_alpha);

        ufos2 = new Array<>();
        ufos2.add(cov_delta2, cov_omicron2, sick_person2, syringe);
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
                // Slicing ufo
                if(ufo.getBoundingRectangle().contains(touchPoint.x, touchPoint.y) && touchPoint.y < HEIGHT/2) {
                    // Slicing the sick patient
                    if (ufo instanceof SickPerson) {
                        gameOver(player2);
                        break;
                    }
                    // Slicing the syringe
                    else if (ufo instanceof Syringe) {
                        ufo.reposition();
                        player1.gainLife();
                        if (player1.getLivesLeft() == 3) {
                            syringe.setSpawnable(false);
                        }
                    }
                    // Slicing one of the covid variants and increasing difficulty when the score gets higher
                    else{
                        int difficulty = player1.increaseScoreAndDifficulty(ufo.getPoints());
                        ufo.reposition();
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
            gameOver(player2);
        }
        for (UFO ufo : ufos2) {
            ufo.update(dt, player2);
        }
        if (player2.getLivesLeft() == 0) {
            // GAME OVER
            gameOver(player1);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        // Setting screen 1
        sb.setProjectionMatrix(playerOneCamera.combined);
        Gdx.gl.glViewport(0,0,WIDTH,HEIGHT/2);
        sb.begin();
        sb.draw(bg1, 0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < player1.getLivesLeft(); i++) {
            sb.draw(health, (WIDTH/32)+i*(WIDTH/10), (HEIGHT/2)-(WIDTH/10), WIDTH/12, WIDTH/12);
        }
        sb.draw(pause, pause1MarginX,pause1MarginY, pauseSize, pauseSize);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_alpha.getTexture(), cov_alpha.getPosition().x,cov_alpha.getPosition().y, cov_alpha.getSize(), cov_alpha.getSize());
        sb.draw(cov_omicron.getTexture(), cov_omicron.getPosition().x,cov_omicron.getPosition().y, cov_omicron.getSize(), cov_omicron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y, sick_person.getSize(), sick_person.getSize());
        if (syringe.isSpawnable()) {
            sb.draw(syringe.getTexture(), syringe.getPosition().x, syringe.getPosition().y, syringe.getSize(), syringe.getSize());
        }
        sb.end();
        // Setting screen 2
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
        sb.draw(cov_omicron2.getTexture(), cov_omicron2.getPosition().x,cov_omicron2.getPosition().y, cov_omicron2.getSize(), cov_omicron2.getSize());
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
