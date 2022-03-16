package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.sprites.COV_delta;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.SingleplayerState;

public class MyGdxGame extends ApplicationAdapter {

	public final static int WIDTH = 480;
	public final static int HEIGHT = 800;
	public final static String TITLE = "RonaSlice";
	private GameStateManager gsm;

	private SpriteBatch batch;

	private Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("background.png");


		// Her skal ikke singleplayerstate pushes. Endres når menu er klar.
		gsm.push(new SingleplayerState(gsm));
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
