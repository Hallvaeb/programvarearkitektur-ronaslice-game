package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.sprites.COV_omikron;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.UFO;
import com.mygdx.game.sprites.UFO2;

public class MultiplayerState extends State implements PlayState {

    // Array med alle UFO-objekter skjerm 1
    private Array<UFO> ufos1;
    private Array<UFO2> ufos2;

    private Texture bg1 = new Texture("background.png");
    private Texture bg2 = new Texture("backgroundFlipped.png");
    private COV_delta cov_delta;
    private COV_omikron cov_omikron;
    private SickPerson sick_person;

    private COV_delta cov_delta2;
    private COV_omikron cov_omikron2;
    private SickPerson sick_person2;


    protected MultiplayerState(GameStateManager gsm) {
        super(gsm);
        cov_delta = new COV_delta(200, 60);
        cov_omikron = new COV_omikron(100, 60);
        sick_person = new SickPerson(300, 80);

        cov_delta2 = new COV_delta(200, 60);
        cov_omikron2 = new COV_omikron(100, 60);
        sick_person2 = new SickPerson(300, 80);

        ufos1 = new Array<>();
        ufos1.add(cov_delta, cov_omikron, sick_person);

        ufos2 = new Array<>();
        //ufos2.add(cov_delta2, cov_omikron2, sick_person2);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            // Slice fra bruker.
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        for (UFO ufo : ufos1) {
            ufo.update(dt);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg1, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        sb.draw(bg2, 0, MyGdxGame.HEIGHT/2, MyGdxGame.WIDTH, MyGdxGame.HEIGHT/2);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y/2, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y/2, cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y/2, sick_person.getSize(), sick_person.getSize());

        sb.draw(cov_delta2.getTexture(), cov_delta2.getPosition().x,cov_delta2.getPosition().y/2, cov_delta2.getSize(), cov_delta2.getSize());
        sb.draw(cov_omikron2.getTexture(), cov_omikron2.getPosition().x,cov_omikron2.getPosition().y/2, cov_omikron2.getSize(), cov_omikron2.getSize());
        sb.draw(sick_person2.getTexture(), sick_person2.getPosition().x,sick_person2.getPosition().y/2, sick_person2.getSize(), sick_person2.getSize());

        sb.end();

    }

    @Override
    public void dispose() {

    }
}
