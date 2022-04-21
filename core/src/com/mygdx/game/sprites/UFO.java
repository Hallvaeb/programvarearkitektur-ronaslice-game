package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Player;

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

    /**
     * Constructor for a UFO. Sets position, bounding rectangle, velocity.
     * @param size The size of the UFO.
     */
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

    /**
     * Update method that updates the animation frame, adds gravity and difficulty, and
     * utilizes the loseLife method.
     * @param dt Delta time.
     * @param player Player instance.
     */
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
                // Starts dropping syringes if max health is not achieved.
                Syringe.getInstance().setSpawnable(true);
            }
        }
        rect = rect.set(position.x, position.y, size, size);
    }

    /**
     * Moves UFOs to top of screen after it is sliced by the player og hits the bottom.
     */
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

    /**
     * A getter for reward for slicing a virus (UFO).
     * @return Amount of points.
     */
    public double getPoints() {
        return points;
    }

    /**
     * Sets the points achieved for slicing a UFO.
     * @param points Amount of points.
     */
    public void setPoints(double points) {
        this.points = points;
    }

    /**
     * A getter for the UFO position.
     * @return Vector3 position.
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * A getter for the UFO size
     * @return The size of the UFO in pixels.
     */
    public int getSize() {
        return size;
    }

    /**
     * A getter for the bounding rectangle for the UFO.
     * @return The bounding rectangle.
     */
    public Rectangle getBoundingRectangle(){ return rect;}

    /**
     * A getter for the texture animation.
     * @return Animation texture.
     */
    public Animation getTextureAnimation() {
        return textureAnimation;
    }

    /**
     * Sets the texture animation.
     * @param textureAnimation Animation object.
     */
    public void setTextureAnimation(Animation textureAnimation) {
        this.textureAnimation = textureAnimation;
    }

    /**
     * Sets the difficulty.
     * @param difficulty int value representing the difficulty.
     */
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty*(-DIFFICULTY_INCREASE_FACTOR);
    }

    /**
     * Abstract method to dispose all textures.
     */
    public abstract void dispose();

}

