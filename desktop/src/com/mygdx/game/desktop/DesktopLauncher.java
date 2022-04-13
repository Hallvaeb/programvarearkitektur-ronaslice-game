package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Gdx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 480;
		config.height = 800;
		config.title = MyGdxGame.TITLE;
		new LwjglApplication(new MyGdxGame(new DesktopInterfaceClass()), config);
	}
}
