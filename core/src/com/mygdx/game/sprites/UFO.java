package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class UFO {
    private static final int GRAVITY = -2;
    private static final float DIFFICULTYINCREASIONFACTOR = 1;
    private Vector3 position;
    private Vector3 velocity;
    private int size;
    private Rectangle bounds;
    private double points;
    private Syringe syringe;
    private float difficulty;


    public UFO (int x, int size) {
        // Fikse startposisjon. Dette skal bli randomisert.
        position = new Vector3(x, Gdx.graphics.getHeight(), 0);
        velocity = new Vector3(0, 0 , 0);
        this.size = size;
        bounds = null;
        points = 0;
        syringe = Syringe.getInstance();
        difficulty = 0;
    }

    public void update(float dt, Player player) {
        velocity.scl(dt);
        if (position.y > 0) {
            velocity.add(0, (GRAVITY+difficulty), 0);
        }
        position.add(0, velocity.y, 0);
        if (position.y < 0) {
            position.y = Gdx.graphics.getHeight();
            if (!(this instanceof SickPerson || this instanceof Syringe)) {
                player.loseLife();
                syringe.setSpawnable(true);
            }
        }
        bounds = new Rectangle(position.x, position.y, size, size);
    }

    public void reposition() {
        if(this instanceof Syringe){
            // Syringes drops more rarely based on difficulty
            position.y = Gdx.graphics.getHeight() +
                    (int) (Math.random() * Gdx.graphics.getHeight()) +
                    difficulty*5;
        }
        else{
            position.y = Gdx.graphics.getHeight() + (int) (Math.random() * Gdx.graphics.getHeight());
        }
        position.x = (int) (Math.random() * (Gdx.graphics.getWidth() - getBoundingRectangle().width));
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

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty*(-DIFFICULTYINCREASIONFACTOR);
    };
}
