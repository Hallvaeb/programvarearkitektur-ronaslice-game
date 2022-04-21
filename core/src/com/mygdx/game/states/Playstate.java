package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.sprites.Player;

interface PlayState {
    public void update(float dt);
    public void render(SpriteBatch sb);
    public void dispose();
    public abstract void setUFODifficulty(int difficulty);
    public void gameOver(Player player);
}
