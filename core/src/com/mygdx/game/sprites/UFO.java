package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class UFO {
    private static final int GRAVITY = -2;
    private static final float DIFFICULTY_INCREASE_FACTOR = 1;
    private final Vector3 position;
    private final Vector3 velocity;
    private final int size;
    private Rectangle rect;
    private double points;
    private float difficulty;
    private Animation textureAnimation;

    public UFO (int size) {
        position = new Vector3(0,0, 0);
        this.size = size;
        rect = new Rectangle(position.x, position.y, size, size);
        reposition();
        velocity = new Vector3(0, 0 , 0);
        textureAnimation = null;
        points = 0;
        difficulty = 0;
    }

    public void update(float dt, Player player) {
        if(textureAnimation != null) {
            textureAnimation.update(dt);
        }
        velocity.scl(dt);
        if (position.y > 0) {
            velocity.add(0, (GRAVITY+difficulty), 0);
        }
        position.add(0, velocity.y, 0);
        if (position.y < 0) {
            reposition();
            if (!(this instanceof SickPerson || this instanceof Syringe)) {
                player.loseLife();
                Syringe.getInstance().setSpawnable(true);
            }
        }
        rect = rect.set(position.x, position.y, size, size);
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
        rect = rect.set(position.x, position.y, size, size);
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

    public Rectangle getBoundingRectangle(){ return rect;}

    public Animation getTextureAnimation() {
        return textureAnimation;
    }

    public void setBoundingRectangle(Rectangle rect) {
        this.rect = rect;
    }

    public void setTextureAnimation(Animation textureAnimation) {
        this.textureAnimation = textureAnimation;
    }

    public abstract void dispose();

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty*(-DIFFICULTY_INCREASE_FACTOR);
    }
}

