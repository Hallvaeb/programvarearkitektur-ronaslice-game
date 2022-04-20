package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Syringe extends UFO {

    boolean isSpawnable;
    // Privat konstruktor som forhindrer at det automatisk blir offentlig
    private Syringe() {
        // Startverdi første spawn
        super(150, 60);
        setBoundingRectangle(new Rectangle(super.getPosition().x, super.getPosition().y, super.getSize(), super.getSize()));
        isSpawnable = false;
    }

    private static class SyringeHolder {
        private static Syringe instance = new Syringe();
        private static Texture texture = new Texture("syringe.png");
    }

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
        this.isSpawnable = isSpawnable;
    }

    public void dispose() {
        getTexture().dispose();
    }
}