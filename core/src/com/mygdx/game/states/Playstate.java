package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

interface PlayState {
    public void update(float dt);
    public void render(SpriteBatch sb);
    public void dispose();
}
