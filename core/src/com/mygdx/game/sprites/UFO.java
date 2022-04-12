package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public abstract class UFO {
    private static final int GRAVITY = -2;
    private Vector3 position;
    private Vector3 velocity;
    private int size;
    private Rectangle bounds;
    private int type;

    public UFO (int x, int size) {
        // Fikse startposisjon. Dette skal bli randomisert.
        position = new Vector3(x, MyGdxGame.HEIGHT, 0);
        velocity = new Vector3(0, 0 , 0);
        this.size = size;
        bounds = null;
        type = 0;
    }

    public void update(float dt, Player player) {
        velocity.scl(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        position.add(0, velocity.y, 0);


        //Denne må endres, UFOer skal falle lenger.
        if (position.y < 0) {
            position.y = MyGdxGame.HEIGHT;
            //Miste et liv her hvis et virus har kommet i bunn av skjermen.
            if (type == 1) {
                player.loseLife();
            }
        }
        bounds = new Rectangle(position.x, position.y, size, size);
        //System.out.println(bounds);

    }

    /**
     * Sliced repositions the viruses.
     * @return type
     */
    public int sliced() {
        position.y = MyGdxGame.HEIGHT + (int) (Math.random() * MyGdxGame.HEIGHT);
        System.out.println(getBoundingRectangle().width);
        position.x = getBoundingRectangle().width + (int) (Math.random() * (MyGdxGame.WIDTH - getBoundingRectangle().width));
        return type;
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public Rectangle getBoundingRectangle(){ return bounds;}

    public void setBoundingRectangle(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setType(int type) {
        this.type = type;
    }
}
