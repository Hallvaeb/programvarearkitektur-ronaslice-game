package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Player;

/** Interface for SinglePlayerState and MultiPlayerState */
public interface PlayState {
    void update(float dt);
    void render(SpriteBatch sb);
    void dispose();
    void gameOver(Player player);
    void observerUpdate();
}
