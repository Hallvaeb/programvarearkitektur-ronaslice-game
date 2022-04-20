package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
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
        musicButton = new CheckBox("   Turn off music",uiSkin);
        soundButton = new CheckBox("   Turn off sound",uiSkin);

        musicButton.setPosition(340,340);
        musicButton.setSize(20,20);

        soundButton.setPosition(340,430);
        soundButton.setSize(20,20);

        settings.setSize(Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
        settings.setPosition(Gdx.graphics.getWidth()/2f - settings.getWidth()/2,Gdx.graphics.getHeight()/2f +200);

        returnBtn.setSize(Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
        returnBtn.setPosition(50, 50);

        soundText.setSize(Gdx.graphics.getWidth()/3f,Gdx.graphics.getWidth()/3f);
        soundText.setPosition(Gdx.graphics.getWidth()/2f - soundText.getWidth(), Gdx.graphics.getHeight()/2f);

        musicText.setSize(Gdx.graphics.getWidth()/3f,Gdx.graphics.getWidth()/3f);
        musicText.setPosition(Gdx.graphics.getWidth()/2f - musicText.getWidth(), Gdx.graphics.getHeight()/2f -100);


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
        volumeSoundSlider.setPosition(270,Gdx.graphics.getHeight()/2f +70);
        volumeMusicSlider.setPosition( 270,Gdx.graphics.getHeight()/2f-25);

        stage.addActor(volumeMusicSlider);
        stage.addActor(volumeSoundSlider);
        stage.addActor(musicButton);
        stage.addActor(soundButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (returnBtn.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                MyGdxGame.sound.play();
                gsm.pop();
            }

            // Music
            if (volumeMusicSlider.isDragging()){
                musicButton.setChecked(false);
                MyGdxGame.setVolume(volumeMusicSlider.getValue());
                MyGdxGame.sound.play();
            }

            // Sound
            if (volumeSoundSlider.isDragging()){
                soundButton.setChecked(false);
                MyGdxGame.setSoundVolume(volumeSoundSlider.getValue());
                MyGdxGame.sound.play();
            }

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        if(musicButton.isChecked()){
            MyGdxGame.setVolume(0.0f);
        }
        else{
            MyGdxGame.setVolume(volumeMusicSlider.getValue());
        }
        if(soundButton.isChecked()){
            MyGdxGame.setSoundVolume(0.0f);
        }
        else{
            MyGdxGame.setSoundVolume(volumeSoundSlider.getValue());
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(img,0, 0);
        sb.draw(settings, settings.getX(), settings.getY(), Gdx.graphics.getWidth()/3f ,Gdx.graphics.getWidth()/3f);
        sb.draw(returnBtn, returnBtn.getX(), returnBtn.getY(), Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f);
        volumeMusicSlider.draw(sb,100);
        volumeSoundSlider.draw(sb,100);
        musicButton.draw(sb,100);
        soundButton.draw(sb,100);
        sb.draw(soundText, soundText.getX(), soundText.getY(), Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f );
        sb.draw(musicText, musicText.getX(), musicText.getY(), Gdx.graphics.getWidth()/3f, Gdx.graphics.getWidth()/3f );
        sb.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        returnBtn.getTexture().dispose();
        settings.getTexture().dispose();
        soundText.getTexture().dispose();
        musicText.getTexture().dispose();
        uiSkin.dispose();
        stage.dispose();
    }
}
