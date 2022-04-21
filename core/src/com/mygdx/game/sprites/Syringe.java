package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Syringe extends UFO {

    boolean isSpawnable;

    /**
     * Private constructor to avoid unwanted instantiation.
     */
    private Syringe() {
        // The syringe size.
        super(60);
        isSpawnable = false;
    }

    /**
     * Class with Syringe
     */
    private static class SyringeHolder {
        private static final Syringe instance = new Syringe();
        private static final Texture texture = new Texture("syringe.png");

    }

    /**
     * Sets difficulty to 0 and isSpawnable to false.
     */
    public void reset(){
        setDifficulty(0);
        setSpawnable(false);
    }

    /**
     * A singleton pattern approach to get the Syringe instance.
     * @return A Syringe instance.
     */
    public static Syringe getInstance() {
        return SyringeHolder.instance;
    }

    public Texture getTexture() {
        return SyringeHolder.texture;
    }

    public boolean isSpawnable() {
        return isSpawnable;
    }

    public void setSpawnable(boolean isSpawnable) {
        reposition();
        this.isSpawnable = isSpawnable;
    }

    public void dispose() {

    }
}