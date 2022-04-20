package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public abstract class UFO2 {
    private static final int GRAVITY = -2;
    private Vector3 position;
    private Vector3 velocity;
    private int size;

    public UFO2(int x, int size) {
        // Fikse startposisjon. Dette skal bli randomisert.
        position = new Vector3(x, MyGdxGame.HEIGHT, 0);
        velocity = new Vector3(0, 0 , 0);
        this.size = size;
    }

    public void update(float dt) {


        velocity.scl(dt);
        if (position.y > 0) {
            velocity.add(0, -GRAVITY, 0);
        }

        position.add(0, velocity.y, 0);


        //Denne må endres, UFOer skal falle lenger.
        if (position.y < 0) {
            position.y = 0;
            //Miste et liv her hvis et virus har kommet i bunn av skjermen.
        }

    }

    public Vector3 getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }
}
