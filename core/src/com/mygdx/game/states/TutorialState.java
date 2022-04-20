package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.sprites.Syringe;
import com.mygdx.game.sprites.UFO;

public class TutorialState extends State{
    private Texture bg;
    private Sprite returnBtn;
    private static final int MARGIN = 50;
    private BitmapFont fontHeader = new BitmapFont();
    private BitmapFont fontInfo = new BitmapFont();
    private String textInfo = "Slice the viruses and the syringes, while avoiding the sick person!";
    private final float fontInfoX;
    private Sprite delta;
    private Sprite omikron;
    private Sprite alpha;
    private Syringe syringe;
    private Sprite sick_person;


    public TutorialState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg_bare_himmel.png");
        returnBtn = new Sprite(new Texture("return.png"));
        delta = new Sprite(new Texture("cov_delta.png"));
        omikron = new Sprite(new Texture("cov_omikron.png"));
        alpha = new Sprite(new Texture("cov_alpha.png"));
        sick_person = new Sprite(new Texture("sick_person.png"));
        syringe = Syringe.getInstance();

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
        if (Gdx.input.isTouched()) {
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.pop();
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
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
        //fontInfo.draw(sb, textInfo, fontInfoX, Gdx.graphics.getHeight()-(2*MARGIN), Gdx.graphics.getWidth()- (Gdx.graphics.getWidth()/2f)  - (fontHeader.getRegion().getRegionWidth()/2f), 1, true);
        fontHeader.draw(sb, "TUTORIAL", (Gdx.graphics.getWidth()/2f)  - (fontHeader.getRegion().getRegionWidth()/2f), Gdx.graphics.getHeight()-MARGIN);
        sb.draw(delta,50,200, 70, 70);
        sb.draw(omikron,50,300, 70, 70);
        sb.draw(alpha,50,400, 70, 70);
        sb.draw(syringe.getTexture(),50,500, 70, 70);
        sb.draw(sick_person,50,600, 70, 70);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        returnBtn.getTexture().dispose();
        fontHeader.dispose();
        fontInfo.dispose();
        delta.getTexture().dispose();
        omikron.getTexture().dispose();
        alpha.getTexture().dispose();
        sick_person.getTexture().dispose();
        System.out.println("Tutorial disposed.");
    }
}
