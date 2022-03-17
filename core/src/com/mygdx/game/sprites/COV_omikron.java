package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

// Denne klassen skal implementere et UFO-grensesnitt eller extende en abstract klasse
// Da vil mange av disse funksjone ikke skrives flere ganger. [GJORT]
public class COV_omikron extends UFO {
    private Texture texture;

    public COV_omikron(int x, int size) {
        super(x, size);
        texture = new Texture("cov_omikron.png");
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose() {
        // Se om denne skal i den abstracte klassen eller ikke. Kan hende de samme variablene skal disposes.
    }
}
