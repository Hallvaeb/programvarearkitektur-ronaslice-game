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
    private Texture bg;
    private List<Float> scores;
    private List<String> names;
    private BitmapFont font;
    private BitmapFont scoreFont;
    private BitmapFont nameFont;
    private BitmapFont nameTitleFont;
    private BitmapFont scoreTitleFont;
    private BitmapFont newNameFont;
    private BitmapFont newScoreFont;
    private Sprite quitBtn;
    private boolean bool;
    private Player player;

    protected GameOverState(GameStateManager gsm, final Player player) {
        super(gsm);
        this.player = player;
        bool = false;
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();
        // FOR THE "GAME OVER" TEXT
        font = new BitmapFont();
        nameFont = new BitmapFont();
        scoreFont = new BitmapFont();
        nameTitleFont = new BitmapFont();
        scoreTitleFont = new BitmapFont();
        newNameFont = new BitmapFont();
        newScoreFont = new BitmapFont();
        font.setColor(0,0,0,1);
        font.getData().setScale(2.5f);

        bg = new Texture("bg_bare_himmel.png");
        quitBtn = new Sprite(new Texture("return.png"));
        quitBtn.setSize(Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
        quitBtn.setPosition(Gdx.graphics.getWidth()/2f-quitBtn.getWidth()/2, 5);

        Input.TextInputListener textListener = new Input.TextInputListener() {
            @Override
            public void input(String input) {
                bool = true;
                if(player.getScore() > MyGdxGame.get_FBIC().GetTopScores().get(9)){
                    MyGdxGame.get_FBIC().SetValueInDB(input, player.getScore());

                }
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
        scores = MyGdxGame.get_FBIC().GetTopScores();
        names = MyGdxGame.get_FBIC().GetTopNames();
        if (Gdx.input.isTouched()) {
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
        sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        // -80 må fikses, samme med MARGIN i pauseState.
        font.draw(sb, "GAME OVER", (MyGdxGame.WIDTH/2f)  - (font.getRegion().getRegionWidth()/2f), Gdx.graphics.getHeight()-80);
        if (bool==false)
            font.draw(sb, "Your score: " + player.getScore(), (MyGdxGame.WIDTH/2f)  - (font.getRegion().getRegionWidth()/2f), Gdx.graphics.getHeight()-150);
        if (bool == true){
            boolean bool2 = true;
            nameTitleFont.draw(sb, "NAME", 100, MyGdxGame.HEIGHT-150);
            scoreTitleFont.draw(sb, "SCORE", MyGdxGame.WIDTH-150, MyGdxGame.HEIGHT-150);
            if (scores != null) {
                for (int i = 0; i < scores.size(); i++) {
                    if (( scores.get(i) == player.getScore()) && bool2 == true){
                        newNameFont.setColor(Color.RED);
                        newScoreFont.setColor(Color.RED);
                        newNameFont.draw(sb, "" + names.get(i), 100, MyGdxGame.HEIGHT - 200 - (i * 50));
                        newScoreFont.draw(sb, "" + scores.get(i), MyGdxGame.WIDTH - 135, MyGdxGame.HEIGHT - 200 - (i * 50));
                        bool2 = false;
                    }
                    else {
                        nameFont.setColor(Color.WHITE);
                        scoreFont.setColor(Color.WHITE);
                        nameFont.draw(sb, "" + names.get(i), 100, MyGdxGame.HEIGHT - 200 - (i * 50));
                        scoreFont.draw(sb, "" + scores.get(i), MyGdxGame.WIDTH - 135, MyGdxGame.HEIGHT - 200 - (i * 50));
                    }
                }
            }
        }
        sb.draw(quitBtn, quitBtn.getX(), quitBtn.getY(), Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
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
