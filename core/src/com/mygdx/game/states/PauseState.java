package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseState extends State {
    private Texture bg;
    private Sprite playBtn;
    private Sprite resBtn;
    private Sprite settingBtn;
    private Sprite tutorialBtn;
    private Sprite quitBtn;
    private static final int MARGIN = 80;
    private BitmapFont font;
    private static final CharSequence GAMEPAUSED = "GAME PAUSED";

    public PauseState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg_bare_himmel.png");
        // PLAYBTN USED FOR SCALING AND POSITIONING THE OTHER BUTTONS
        playBtn = new Sprite(new Texture("playbtn.png"));
        resBtn = new Sprite(new Texture("resBtn.png"));
        settingBtn = new Sprite(new Texture("settingsButton.png"));
        tutorialBtn = new Sprite(new Texture("tutorialButton.png"));
        quitBtn = new Sprite(new Texture("quitBtn.png"));

        playBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        resBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        resBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-MARGIN)-2*playBtn.getHeight());
        settingBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        settingBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-MARGIN)-3*playBtn.getHeight());
        tutorialBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        tutorialBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-MARGIN)-4*playBtn.getHeight());
        quitBtn.setSize(Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        quitBtn.setPosition(Gdx.graphics.getWidth()/2f-playBtn.getWidth()/2, (Gdx.graphics.getHeight()-MARGIN)-5*playBtn.getHeight());

        // FOR THE "GAME PAUSED" TEXT
        font = new BitmapFont();
        font.setColor(0,0,0,1);
        font.getData().setScale(2.5f);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (resBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.pop();
            }
            if (settingBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.push(new SettingState(gsm));
            }
            if (tutorialBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.push(new TutorialState(gsm));
            }
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                gsm.pop();
                gsm.set(new MenuState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(resBtn, resBtn.getX(), resBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(settingBtn, settingBtn.getX(), settingBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(tutorialBtn, tutorialBtn.getX(), tutorialBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), Gdx.graphics.getWidth()/4f, Gdx.graphics.getWidth()/4f);
        font.draw(sb, GAMEPAUSED, (Gdx.graphics.getWidth()/2f)  - (font.getRegion().getRegionWidth()/2), Gdx.graphics.getHeight()-MARGIN);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        playBtn.getTexture().dispose();
        resBtn.getTexture().dispose();
        settingBtn.getTexture().dispose();
        tutorialBtn.getTexture().dispose();
        quitBtn.getTexture().dispose();
    }
}
