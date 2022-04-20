package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private double points;
    private Syringe syringe;
    private Animation textureAnimation;

    public UFO (int x, int size) {
        // Fikse startposisjon. Dette skal bli randomisert.
        position = new Vector3(x, Gdx.graphics.getHeight(), 0);
        velocity = new Vector3(0, 0 , 0);
        this.size = size;
        bounds = null;
        textureAnimation = null;
        type = 0;
        points = 0;
        syringe = Syringe.getInstance();
    }

    public void update(float dt, Player player) {
        if(textureAnimation != null) {
            textureAnimation.update(dt);
        }
        velocity.scl(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        position.add(0, velocity.y, 0);


        //Denne må endres, UFOer skal falle lenger.
        if (position.y < 0) {
            position.y = Gdx.graphics.getHeight();
            //Miste et liv her hvis et virus har kommet i bunn av skjermen.
            if (type == 1) {
                player.looseLife();
                syringe.setSpawnable(true);
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
        position.y = Gdx.graphics.getHeight() + (int) (Math.random() * Gdx.graphics.getHeight());
        System.out.println(getBoundingRectangle().width);
        position.x = getBoundingRectangle().width + (int) (Math.random() * (Gdx.graphics.getWidth() - getBoundingRectangle().width));
        return type;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public Vector3 getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public Rectangle getBoundingRectangle(){ return bounds;}

    public Animation getTextureAnimation() {
        return textureAnimation;
    }

    public void setBoundingRectangle(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setTextureAnimation(Animation textureAnimation) {
        this.textureAnimation = textureAnimation;
    }

    public void setType(int type) {
        this.type = type;
    }
}
