package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SickPerson extends UFO {
    private Texture texture;

    public SickPerson(int x, int size) {
        super(x, size);
        texture = new Texture("sick_person_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
    }

    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    public void dispose() {
        texture.dispose();
    }
}
