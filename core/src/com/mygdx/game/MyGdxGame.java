package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.states.GameStateManager;

import com.mygdx.game.states.SingleplayerState;

import com.mygdx.game.states.MenuState;


public class MyGdxGame extends ApplicationAdapter {

	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	public final static String TITLE = "RonaSlice";
	public static int count = -1;
	public int ID;
	private GameStateManager gsm;

	private SpriteBatch batch;

	private Texture img;


	FireBaseInterface _FBIC;

	public MyGdxGame(FireBaseInterface FBIC){
		ID = ++count;
		_FBIC = FBIC;
	}

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		_FBIC.SomeFunction();
		_FBIC.FirstFireBaseTest();
		_FBIC.SetOnValueChangedListener();
		_FBIC.SetValueInDB(ID+"/message", "new text");
		//_FBIC.SetValueInDB("message2/text2", "new text");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));




	}

	@Override
	public void render () {

		ScreenUtils.clear(1, 0, 0, 1);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
