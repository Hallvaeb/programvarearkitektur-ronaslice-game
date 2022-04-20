package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

// Denne klassen skal implementere et UFO-grensesnitt eller extende en abstract klasse
// Da vil mange av disse funksjone ikke skrives flere ganger. [GJORT]
public class COV_omikron extends UFO {
    private Texture texture;

    public COV_omikron(int size) {
        super(size);
        texture = new Texture("cov_omikron_sheet.png");
        setTextureAnimation(new Animation(new TextureRegion(texture), 10, 1f));
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
        super.setPoints(2);
    }

    public TextureRegion getTexture(){
        return super.getTextureAnimation().getFrame();
    }

    public void dispose() {
        texture.dispose();
    }
}
