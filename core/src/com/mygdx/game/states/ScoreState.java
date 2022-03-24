package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ScoreState extends State{

    private int score;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;
    private Stage stage;
    private Container<Table> tableContainer;

    protected ScoreState(GameStateManager gsm) {
        super(gsm);
        score = 0;
        yourScoreName = "score: "+ score;
        yourBitmapFontName = new BitmapFont();
        stage = new Stage();
        yourBitmapFontName = new BitmapFont();
    }



    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        yourBitmapFontName.draw(sb, yourScoreName, Gdx.graphics.getWidth()/2, 100);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
