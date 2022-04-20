package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class UFO {
    private static final int GRAVITY = -2;
    private Vector3 position;
    private Vector3 velocity;
    private int size;
    private Rectangle bounds;
    private double points;
    private Syringe syringe;

    public UFO (int x, int size) {
        // Fikse startposisjon. Dette skal bli randomisert.
        position = new Vector3(x, Gdx.graphics.getHeight(), 0);
        velocity = new Vector3(0, 0 , 0);
        this.size = size;
        bounds = null;
        points = 0;
        syringe = Syringe.getInstance();
    }

    public void update(float dt, Player player) {
        velocity.scl(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        position.add(0, velocity.y, 0);


        //Denne må endres, UFOer skal falle lenger.
        if (position.y < 0) {
            position.y = Gdx.graphics.getHeight();
            //Miste et liv her hvis et virus har kommet i bunn av skjermen.
            if (!(this instanceof SickPerson || this instanceof Syringe)) {
                player.loseLife();
                syringe.setSpawnable(true);
            }
        }
        bounds = new Rectangle(position.x, position.y, size, size);
        //System.out.println(bounds);

    }

    /**
     * Sliced repositions the viruses.
     */
    public void reposition() {
        position.y = Gdx.graphics.getHeight() + (int) (Math.random() * Gdx.graphics.getHeight());
        position.x = getBoundingRectangle().width + (int) (Math.random() * (Gdx.graphics.getWidth() - getBoundingRectangle().width));
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

    public void setBoundingRectangle(Rectangle bounds) {
        this.bounds = bounds;
    }

    public abstract void dispose();
}
