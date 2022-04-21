package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SickPerson extends UFO {
    private Texture texture;

    /**
     * Constructor for sick person. Sets the texture and size.
     * @param size The size of the sick person in pixels.
     */
    public SickPerson(int size) {
        super(size);
        texture = new Texture("sick_person_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
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
