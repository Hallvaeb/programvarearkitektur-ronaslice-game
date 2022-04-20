package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class COV_alpha extends UFO {

    private Texture texture;

    public COV_alpha(int size) {
        super(size);
        texture = new Texture("cov_alpha_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
        super.setPoints(3);
    }

    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    public void dispose() {
        texture.dispose();
    }
}

