package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Virus extends UFO {

    /**
     * Constructor for corona virus variant. Sets the texture, size and amount of points when sliced.
     * @param size The size of the virus in pixels.
     */
    public Virus(int size, int points, Texture texture) {
        super(size);
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        super.setPoints(points);
    }

    /**
     * A getter for the TextureRegion texture.
     * @return Current texture frame.
     */
    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    /**
     * Dispose method.
     */
    public void dispose() {
        //texture.dispose();
    }
}

