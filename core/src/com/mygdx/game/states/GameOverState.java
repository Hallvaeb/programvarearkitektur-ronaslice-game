package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Player;

import javax.swing.JOptionPane;


public class GameOverState extends State {

    private Texture bg = new Texture("background.png");
    private BitmapFont font;

    private Player player;

    protected GameOverState(GameStateManager gsm, Player player) {
        super(gsm);
        this.player = player;
        // FOR THE "GAME OVER" TEXT
        font = new BitmapFont();
        font.setColor(0,0,0,1);
        font.getData().setScale(2.5f);

        Input.TextInputListener textListener = new Input.TextInputListener() {
            @Override
            public void input(String input) {
                System.out.println(input);
            }

            @Override
            public void canceled() {
                System.out.println("Aborted");
            }
        };
        Gdx.input.getTextInput(textListener, "Enter name: ", player.getName(), "");
        //MyGdxGame.get_FBIC().SetValueInDB(player.getName(), player.getScore());
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        // -80 må fikses, samme med MARGIN i pauseState.
        font.draw(sb, "GAME OVER", (MyGdxGame.WIDTH/2f)  - (font.getRegion().getRegionWidth()/2), Gdx.graphics.getHeight()-80);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
