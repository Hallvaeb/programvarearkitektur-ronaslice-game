package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class COV_delta extends UFO {
    private Texture texture;


    public COV_delta(int x, int size) {
        super(x, size);
        texture = new Texture("cov_delta_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
        super.setPoints(1);
    }

    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    public void dispose() {
        texture.dispose();
    }
}
