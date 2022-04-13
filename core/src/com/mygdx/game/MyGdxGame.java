package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;


public class MyGdxGame extends ApplicationAdapter {

	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	public final static String TITLE = "RonaSlice";
	private GameStateManager gsm;

	private SpriteBatch batch;

	private Texture img;
	private static Music music;
	public static Music sound;


	@Override
	public void create () {
		batch = new SpriteBatch();



		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
		sound = Gdx.audio.newMusic(Gdx.files.internal("soundclick.wav"));

		sound.setVolume(0.5f);
		music.setVolume(0.5f);
		music.setLooping(true);
		music.play();


	}

	@Override
	public void render () {

		ScreenUtils.clear(1, 0, 0, 1);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	public static void setVolume(float number){
		music.setVolume(number);
	}

	public static void setSoundVolume(float number){
		sound.setVolume(number);
	}

	public static float returnVolume(){
		return music.getVolume();
	}
	public static float returnSoundVolume(){
		return sound.getVolume();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		music.dispose();
		sound.dispose();
	}
}
