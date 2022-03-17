package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseState extends State {
    private Texture img;
    private Sprite playBtn;
    private Sprite resBtn;
    private Sprite settingBtn;
    private Sprite helpBtn;
    private Sprite quitBtn;
    private int margin = 80;

    public PauseState(GameStateManager gsm) {
        super(gsm);
        img = new Texture("bg_bare_himmel.png");
        // PLAYBTN USED FOR SCALING AND POSITIONING THE OTHER BUTTONS
        playBtn = new Sprite(new Texture("newGameButton.png"));

        resBtn = new Sprite(new Texture("multiplayerButton.png"));
        settingBtn = new Sprite(new Texture("settingsButton.png"));
        helpBtn = new Sprite(new Texture("tutorialButton.png"));
        quitBtn = new Sprite(new Texture("highScoreButton.png"));

        playBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        // TODO: insert game paused textbox
        resBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        resBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-1*playBtn.getHeight());
        settingBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        settingBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-2*playBtn.getHeight());
        helpBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        helpBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-3*playBtn.getHeight());
        quitBtn.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        quitBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-margin)-4*playBtn.getHeight());

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            /*
            if (resBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.pop();
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.push(new SettingState(gsm));
            }
            if (helpBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.push(new HelpState(gsm));
            }
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                // HER ER GSM STACKEN [PAUSESTATE, SINGLEPLAYERSTATE]
                gsm.set(new MenuState(gsm));
                // ETTER SET ER DEN [MENUSTATE, SINGLEPLAYERSTATE]....
            }
           */
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,0, 0);
        sb.draw(playBtn, playBtn.getX(), playBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(resBtn, resBtn.getX(), resBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(helpBtn, helpBtn.getX(), helpBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.end();
    }

    @Override
    public void dispose() {
        img.dispose();
    }
}
