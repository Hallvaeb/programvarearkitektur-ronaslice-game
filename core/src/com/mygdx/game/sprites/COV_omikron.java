package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class COV_omikron extends UFO {
    private final Texture texture;

    /**
     * Constructor for omicron variant. Sets the texture, size and amount of points when sliced.
     * @param size The size of the virus in pixels.
     */
    public COV_omikron(int size) {
        super(size);
        texture = new Texture("cov_omikron_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        super.setPoints(2);
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
        texture.dispose();
    }
}
