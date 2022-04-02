package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.sprites.COV_omikron;
import com.mygdx.game.sprites.Player;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.Syringe;
import com.mygdx.game.sprites.UFO;

public class SingleplayerState extends State implements PlayState  {

    // Array med alle UFO-objekter.
    private Array<UFO> ufos;


    private Texture bg = new Texture("background.png");
    private COV_delta cov_delta;
    private COV_omikron cov_omikron;
    private SickPerson sick_person;
    private Syringe syringe;

    private Texture health = new Texture("health.png");
    private Sprite pause = new Sprite(new Texture("pause.png"));
    private Player player;
    private BitmapFont font;

    private float time_passed = 0;

    private Vector3 touchPoint;


    public SingleplayerState(GameStateManager gsm){
        super(gsm);
        player = new Player();
        font = new BitmapFont();
        font.getData().setScale(3, 3);

        cov_delta = new COV_delta(200, 60);
        cov_omikron = new COV_omikron(100, 60);
        sick_person = new SickPerson(300, 80);
        syringe = Syringe.getInstance();

        //Pause (Fikse hardkodet verdier)
        pause.setSize(50, 50);
        pause.setPosition(MyGdxGame.WIDTH-60,MyGdxGame.HEIGHT-60);

        // Slice posisjon til player
        touchPoint = new Vector3();

        ufos = new Array<>();
        ufos.add(cov_delta, cov_omikron, sick_person, syringe);
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(),MyGdxGame.HEIGHT - Gdx.input.getY(),0);
            if (pause.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                gsm.push(new PauseState(gsm));
            }

            // Slice fra bruker.
            for (UFO ufo : ufos) {


                System.out.println(touchPoint);

                if(ufo.getBoundingRectangle().contains(touchPoint.x, touchPoint.y))
                {
                    if (ufo.sliced() == -1) {
                        // GAME OVER (slicet en pasient)
                        System.out.println("GAME OVER");
                    }
                    if (ufo instanceof SickPerson) {
                        ufo.sliced();
                        System.out.println("GAME OVER");
                    }
                    else if (ufo.sliced() == 1) {
                        player.increaseScore(1);
                    }
                    else if (ufo.sliced() == 2) {
                        player.gainLife();
                    }
                    System.out.println("au");
                }

            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        time_passed += dt;
        for (UFO ufo : ufos) {
            ufo.update(dt, player);
        }
        if (player.getLivesLeft() == 0) {
            // GAME OVER
            System.out.println("GAME OVER");
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        for (int i = 0; i < player.getLivesLeft(); i++) {
            // Gjøre disse piksel-verdiene ikke hardkodet.
            sb.draw(health, 10+i*60, MyGdxGame.HEIGHT-60, 50, 50);
        }
        font.draw(sb, "Score: " + player.getScore(), MyGdxGame.WIDTH/2 -130, MyGdxGame.HEIGHT-120);
        sb.draw(pause, MyGdxGame.WIDTH-60,MyGdxGame.HEIGHT-60, 50, 50);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y, cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y, sick_person.getSize(), sick_person.getSize());
        sb.draw(syringe.getTexture(), syringe.getPosition().x,syringe.getPosition().y, syringe.getSize(), syringe.getSize());
        sb.end();

    }

    @Override
    public void dispose() {

    }

}
