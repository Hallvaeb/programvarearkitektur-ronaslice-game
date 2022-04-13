package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.MyGdxGame;

public class SettingState extends State{
    private Texture img;
    private Sprite returnBtn;
    private Sprite settings;
    private Sprite soundText;
    private Sprite musicText;
    private Skin uiSkin;
    private Stage stage;
    private CheckBox musicButton;
    private CheckBox soundButton;
    final Slider volumeMusicSlider;
    final Slider volumeSoundSlider;


    public SettingState(GameStateManager gsm){
        super(gsm);
        img= new Texture( "bg_bare_himmel.png");
        settings = new Sprite(new Texture("settingsButton.png"));
        returnBtn = new Sprite(new Texture("return.png"));
        soundText = new Sprite((new Texture("sound.png")));
        musicText = new Sprite((new Texture("music.png")));
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, uiSkin );
        volumeSoundSlider = new Slider( 0f, 1f, 0.1f,false, uiSkin );
        musicButton = new CheckBox("  Turn off music",uiSkin);
        soundButton = new CheckBox("  Turn off sound",uiSkin);

        musicButton.setPosition(340,340);
        musicButton.setSize(20,20);

        soundButton.setPosition(340,430);
        soundButton.setSize(20,20);

        settings.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3);
        settings.setPosition(Gdx.graphics.getWidth()/2 - settings.getWidth()/2,Gdx.graphics.getHeight()/2 +200);

        returnBtn.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3);
        returnBtn.setPosition(50, 50);

        soundText.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/3);
        soundText.setPosition(Gdx.graphics.getWidth()/2 - soundText.getWidth(), Gdx.graphics.getHeight()/2);

        musicText.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/3);
        musicText.setPosition(Gdx.graphics.getWidth()/2 - musicText.getWidth(), Gdx.graphics.getHeight()/2 -100);


        stage = new Stage();

        if (MyGdxGame.returnVolume()==0){
            musicButton.toggle();
        }
        else{
            volumeMusicSlider.setValue(MyGdxGame.returnVolume());
        }
        if (MyGdxGame.returnSoundVolume()==0){
            soundButton.toggle();
        }
        else{
            volumeSoundSlider.setValue(MyGdxGame.returnSoundVolume());
        }
        volumeSoundSlider.setPosition(270,Gdx.graphics.getHeight()/2 +70);
        volumeMusicSlider.setPosition( 270,Gdx.graphics.getHeight()/2-25);

        stage.addActor(volumeMusicSlider);
        stage.addActor(volumeSoundSlider);
        stage.addActor(musicButton);
        stage.addActor(soundButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.set(new MenuState(gsm));
                this.dispose();
            }
            if (volumeMusicSlider.isDragging()){
                MyGdxGame.setVolume(volumeMusicSlider.getValue());
            }
            if (volumeSoundSlider.isDragging()){
                MyGdxGame.setSoundVolume(volumeSoundSlider.getValue());
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        if(musicButton.isChecked()){
            volumeMusicSlider.setValue(0.0f);
            MyGdxGame.setVolume(volumeMusicSlider.getValue());
        }
        if(soundButton.isChecked()){
            volumeSoundSlider.setValue(0.0f);
            MyGdxGame.setSoundVolume(volumeSoundSlider.getValue());
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,0, 0);
        sb.draw(settings, settings.getX(), settings.getY(), Gdx.graphics.getWidth()/3 ,Gdx.graphics.getWidth()/3);
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3);
        volumeMusicSlider.draw(sb,100);
        volumeSoundSlider.draw(sb,100);
        musicButton.draw(sb,100);
        soundButton.draw(sb,100);
        sb.draw(soundText, soundText.getX(), soundText.getY(), Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3 );
        sb.draw(musicText, musicText.getX(), musicText.getY(), Gdx.graphics.getWidth()/3, Gdx.graphics.getWidth()/3 );
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
