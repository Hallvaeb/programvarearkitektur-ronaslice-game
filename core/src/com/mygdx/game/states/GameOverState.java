package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Player;

public class GameOverState extends State {
    private static final float BUTTON_SIZE = Gdx.graphics.getWidth()/4;
    private static final float X_BUTTON = (Gdx.graphics.getWidth()-BUTTON_SIZE)/2;
    private static final float Y_BUTTON = 0.2f*Gdx.graphics.getHeight();
    private static final float Y_TITLE = 0.9f*Gdx.graphics.getHeight();
    private static final float X_NAMES = 0.2f*Gdx.graphics.getWidth();
    private static final float X_SCORES = 1.5f*Gdx.graphics.getWidth();
    private static final float Y_TEXT = 0.75f*Gdx.graphics.getHeight();

    private Player player;
    private Texture bg = new Texture("bg_bare_himmel.png");;
    private List<Float> scores;
    private List<String> names;
    private BitmapFont font = new BitmapFont();;
    private BitmapFont scoreFont = new BitmapFont();;
    private BitmapFont nameFont = new BitmapFont();;
    private BitmapFont nameTitleFont = new BitmapFont();;
    private BitmapFont scoreTitleFont = new BitmapFont();;
    private BitmapFont newNameFont = new BitmapFont();;
    private BitmapFont newScoreFont = new BitmapFont();;
    private Sprite quitBtn = new Sprite(new Texture("return.png"));;
    private boolean writeHighscoreListBool = false;;
    private int X_TITLE = (Gdx.graphics.getWidth() - font.getRegion().getRegionWidth())/2;


    /**
     * Game over screen for singleplayer.
     * @param gsm GameStateManager controlling the states of the application.
     * @param player Provides score of singleplayer, or who won in multiplayer.
     */
    protected GameOverState(GameStateManager gsm, final Player player) {
        super(gsm);
        this.player = player;
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();
        font.setColor(0,0,0,1);
        font.getData().setScale(2.5f);
        quitBtn.setSize(BUTTON_SIZE, BUTTON_SIZE);

        Input.TextInputListener textListener = new Input.TextInputListener() {
            @Override
            public void input(String input) {
                writeHighscoreListBool = true;
                if(player.getScore() > MyGdxGame.get_FBIC().GetTopScores().get(9)){
                    MyGdxGame.get_FBIC().SetValueInDB(input, player.getScore());
                }
            }

            @Override
            public void canceled() {
                writeHighscoreListBool = true;
            }
        };

        Gdx.input.getTextInput(textListener, "Enter name: ", player.getName(), "");
    }

    @Override
    protected void handleInput() {
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();
        if (Gdx.input.isTouched()) {
            if (quitBtn.getBoundingRectangle().contains(Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
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
        sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(sb, "GAME OVER", X_TITLE, Y_TITLE);

        /**
         * Here we use writeHighscoreListBool to write highscores only after the user has
         * given his/her name.
         * writeOnlyOneScoreRedBool is used to make sure the players name becomes red,
         * should he/she enter the highscore list.
         */
        if (!writeHighscoreListBool)
            font.draw(sb, "Your score: " + player.getScore(), X_TITLE, Y_TEXT);
        if (writeHighscoreListBool){
            boolean writeOnlyOneScoreRedBool = true;
            nameTitleFont.draw(sb, "NAME", X_NAMES, Y_TEXT);
            scoreTitleFont.draw(sb, "SCORE", X_SCORES, Y_TEXT);
            if (scores != null) {
                for (int i = 0; i < scores.size(); i++) {
                    if (( scores.get(i) == player.getScore()) && writeOnlyOneScoreRedBool){
                        newNameFont.setColor(Color.RED);
                        newScoreFont.setColor(Color.RED);
                        newNameFont.draw(sb, "" + names.get(i),
                                X_NAMES, Y_TEXT - (i * 50));
                        newScoreFont.draw(sb, "" + scores.get(i),
                                X_SCORES, Y_TEXT - (i * 50));
                        writeOnlyOneScoreRedBool = false;
                    }
                    else {
                        nameFont.setColor(Color.WHITE);
                        scoreFont.setColor(Color.WHITE);
                        nameFont.draw(sb, "" + names.get(i),
                                X_NAMES, Y_TEXT - (i * 50));
                        scoreFont.draw(sb, "" + scores.get(i),
                                X_SCORES, Y_TEXT - (i * 50));
                    }
                }
            }
        }
        sb.draw(quitBtn, X_BUTTON, Y_BUTTON, BUTTON_SIZE, BUTTON_SIZE);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        font.dispose();
        scoreFont.dispose();
        nameFont.dispose();
        nameTitleFont.dispose();
        scoreTitleFont.dispose();
        newNameFont.dispose();
        newScoreFont.dispose();
        quitBtn.getTexture().dispose();
    }
}
