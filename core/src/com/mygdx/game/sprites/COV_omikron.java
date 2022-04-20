package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

// Denne klassen skal implementere et UFO-grensesnitt eller extende en abstract klasse
// Da vil mange av disse funksjone ikke skrives flere ganger. [GJORT]
public class COV_omikron extends UFO {
    private Texture texture;

    public COV_omikron(int x, int size) {
        super(x, size);
        texture = new Texture("cov_omikron.png");
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
        super.setPoints(3);
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        texture.dispose();
    }
}
