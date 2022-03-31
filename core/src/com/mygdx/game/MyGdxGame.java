package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;

import com.mygdx.game.states.MenuState;


public class MyGdxGame extends ApplicationAdapter {

	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	public final static String TITLE = "RonaSlice";
	private static int count = 0;
	private static FireBaseInterface _FBIC;
	public int ID;
	private GameStateManager gsm;

	private SpriteBatch batch;

	private Texture img;


	//FireBaseInterface _FBIC;

	public MyGdxGame(FireBaseInterface FBIC){

		_FBIC = FBIC;
	}

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		//_FBIC.SomeFunction();
		//_FBIC.FirstFireBaseTest();
		//_FBIC.SetOnValueChangedListener();
		//_FBIC.SetValueInDB(ID+"/message", "new text");
		//_FBIC.SetValueInDB("message2/text2", "new text");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	public static FireBaseInterface get_FBIC() {
		return _FBIC;
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
