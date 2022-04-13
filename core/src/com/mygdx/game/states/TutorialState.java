package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class TutorialState extends State{
    private Texture bg;
    private Sprite returnBtn;
    private static final int MARGIN = 80;
    private BitmapFont fontHeader = new BitmapFont();
    private BitmapFont fontInfo = new BitmapFont();
    private String textInfo = "Hit the viruses and the syringes, while avoiding Ulrik!";
    private final float fontInfoX;

    public TutorialState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg_bare_himmel.png");
        returnBtn = new Sprite(new Texture("return.png"));

        returnBtn.setSize((float) Gdx.graphics.getWidth()/3, (float) Gdx.graphics.getWidth()/3);
        returnBtn.setPosition(50, 50);

        fontHeader.setColor(0,0,0,1);
        fontHeader.getData().setScale(2.5f);

        fontInfo.setColor(0,0,0,1);
        fontInfo.getData().setScale(1.8f);

        final GlyphLayout layoutInfo = new GlyphLayout(fontInfo, textInfo);
        // Forsøkt sentrert med (Gdx.graphics.getWidth() - layoutInfo.width)/2; //
        fontInfoX = (Gdx.graphics.getWidth())/2;
    }

    @Override
    public void handleInput() {
        if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
            MyGdxGame.sound.play();
            gsm.pop();
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
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
        fontInfo.draw(sb, textInfo, fontInfoX, Gdx.graphics.getHeight()-(2*MARGIN), Gdx.graphics.getWidth()- (Gdx.graphics.getWidth()/2f)  - (fontHeader.getRegion().getRegionWidth()/2f), 1, true);
        fontHeader.draw(sb, "TUTORIAL", (Gdx.graphics.getWidth()/2f)  - (fontHeader.getRegion().getRegionWidth()/2f), Gdx.graphics.getHeight()-MARGIN);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        returnBtn.getTexture().dispose();
        fontHeader.dispose();
        fontInfo.dispose();
    }
}
