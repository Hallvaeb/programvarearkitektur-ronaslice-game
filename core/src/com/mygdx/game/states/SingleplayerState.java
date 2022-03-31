package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FireBaseInterface;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.sprites.COV_omikron;
import com.mygdx.game.sprites.SickPerson;
import com.mygdx.game.sprites.UFO;

public class SingleplayerState extends State implements PlayState  {

    // Array med alle UFO-objekter.
    private Array<UFO> ufos;


    private Texture bg = new Texture("background.png");
    private COV_delta cov_delta;
    private COV_omikron cov_omikron;
    private SickPerson sick_person;
    Preferences prefs = Gdx.app.getPreferences("myprefs");
    private int count;

    public SingleplayerState(GameStateManager gsm){
        super(gsm);
        cov_delta = new COV_delta(200, 60);
        cov_omikron = new COV_omikron(100, 60);
        sick_person = new SickPerson(300, 80);
        ufos = new Array<>();
        ufos.add(cov_delta, cov_omikron, sick_person);
        ////
        if(!prefs.contains("ID")) {
            prefs.putInteger("ID", 0);
        }
        else {
            count = prefs.getInteger("ID");
            count++;
            prefs.putInteger("ID", count);
        }
        prefs.flush();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            MyGdxGame.get_FBIC().SetValueInDB(prefs.getInteger("ID")+"", "test");
            //System.out.println(prefs.getInteger("ID"));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        for (UFO ufo : ufos) {
            ufo.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(cov_delta.getTexture(), cov_delta.getPosition().x,cov_delta.getPosition().y, cov_delta.getSize(), cov_delta.getSize());
        sb.draw(cov_omikron.getTexture(), cov_omikron.getPosition().x,cov_omikron.getPosition().y, cov_omikron.getSize(), cov_omikron.getSize());
        sb.draw(sick_person.getTexture(), sick_person.getPosition().x,sick_person.getPosition().y, sick_person.getSize(), sick_person.getSize());
        sb.end();

    }

    @Override
    public void dispose() {

    }

}
