package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SickPerson extends UFO {
    private Texture texture;

    public SickPerson(int size) {
        super(size);
        texture = new Texture("sick_person_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
    }

    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    public void dispose() {
        texture.dispose();
    }
}
