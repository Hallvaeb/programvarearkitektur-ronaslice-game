package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class COV_delta extends UFO {
    private Texture texture;


    public COV_delta(int size) {
        super(size);
        texture = new Texture("cov_delta_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        super.setPoints(1);
    }

    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    public void dispose() {
        texture.dispose();
    }
}
