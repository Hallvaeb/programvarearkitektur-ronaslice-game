package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class COV_alpha extends UFO {

    private Texture texture;

    public COV_alpha(int x, int size) {
        super(x, size);
        texture = new Texture("cov_alpha.png");
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
        super.setPoints(1);
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }
}

