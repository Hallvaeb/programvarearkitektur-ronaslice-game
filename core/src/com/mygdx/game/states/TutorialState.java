package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Syringe;

public class TutorialState extends State{
    private static final int MARGIN = 50;
    private static final int IMG_DIM = 70;
    private static final int IMG_X = Gdx.graphics.getWidth()/10; //480x800: = 50
    private static final int TEXT_X = IMG_X + IMG_DIM + 10;
    private static final int IMG_Y_FACTOR = Gdx.graphics.getHeight()/8; //480x800: = 100

    private Texture bg;
    private Sprite returnBtn;
    private BitmapFont fontHeader = new BitmapFont();
    private BitmapFont fontInfo = new BitmapFont();
    private Sprite delta;
    private Sprite omicron;
    private Sprite alpha;
    private Syringe syringe;
    private Sprite sick_person;


    public TutorialState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("bg_bare_himmel.png");
        returnBtn = new Sprite(new Texture("return.png"));
        delta = new Sprite(new Texture("cov_delta.png"));
        omicron = new Sprite(new Texture("cov_omikron.png"));
        alpha = new Sprite(new Texture("cov_alpha.png"));
        sick_person = new Sprite(new Texture("sick_person.png"));
        syringe = Syringe.getInstance();

        returnBtn.setSize((float) Gdx.graphics.getWidth()/3,
                (float) Gdx.graphics.getWidth()/3);
        returnBtn.setPosition(Gdx.graphics.getWidth()/10f, Gdx.graphics.getHeight()/16f);

        fontHeader.setColor(0,0,0,1);
        fontHeader.getData().setScale(Gdx.graphics.getHeight()/320f);

        fontInfo.setColor(0,0,0,1);
        fontInfo.getData().setScale(Gdx.graphics.getHeight()/535f);



    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
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
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), Gdx.graphics.getWidth()/3f,
                Gdx.graphics.getWidth()/3f);

        fontHeader.draw(sb, "TUTORIAL", (Gdx.graphics.getWidth()/2f) - 1.75f*MARGIN,
                Gdx.graphics.getHeight()-MARGIN);

        sb.draw(delta, IMG_X,2*IMG_Y_FACTOR, IMG_DIM, IMG_DIM);
        fontInfo.draw(sb, "Delta, slice! " +
                "\nGives 1 points. \nDeals 1 damage.", TEXT_X, 2*IMG_Y_FACTOR + IMG_DIM);

        sb.draw(omicron, IMG_X,3*IMG_Y_FACTOR, IMG_DIM, IMG_DIM);
        fontInfo.draw(sb, "Omicron, slice! " +
                "\nGives 2 points. \nDeals 1 damage.", TEXT_X, 3*IMG_Y_FACTOR + IMG_DIM);

        sb.draw(alpha, IMG_X, 4*IMG_Y_FACTOR, IMG_DIM, IMG_DIM);
        fontInfo.draw(sb, "Alpha, slice! " +
                "\nGives 3 points. \nDeals 1 damage.", TEXT_X, 4*IMG_Y_FACTOR + IMG_DIM);

        sb.draw(syringe.getTexture(), IMG_X,5*IMG_Y_FACTOR, IMG_DIM, IMG_DIM);
        fontInfo.draw(sb, "Syringe! " +
                "\nSlice this for an extra life!", TEXT_X, 5*IMG_Y_FACTOR + IMG_DIM);

        sb.draw(sick_person,IMG_X,6*IMG_Y_FACTOR, IMG_DIM, IMG_DIM);
        fontInfo.draw(sb, "Sick person 'Ulrik'!" +
                        "\nLet him fall to the hospital. " +
                        "\nGAME OVER if you slice!", TEXT_X, 6*IMG_Y_FACTOR + IMG_DIM);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        returnBtn.getTexture().dispose();
        fontHeader.dispose();
        fontInfo.dispose();
        delta.getTexture().dispose();
        omicron.getTexture().dispose();
        alpha.getTexture().dispose();
        sick_person.getTexture().dispose();
    }
}
