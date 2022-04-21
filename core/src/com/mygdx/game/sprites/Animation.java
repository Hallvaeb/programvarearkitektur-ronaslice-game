package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;

    /**
     * Animation constructor. Divides a sprite sheet into different frames.
     * @param region The sprite sheet.
     * @param frameCount Frame amount in the sprite sheet.
     * @param cycleTime Amount of milliseconds to finish one cycle of frames.
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * Updates the frame to the next frame. Starts the frame cycle again if
     * it exceeds the maxFrameTime.
     * @param dt Delta time
     */
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }

    /**
     * A getter for the frame.
     * @return TextureRegion frame
     */
    public TextureRegion getFrame(){
        return frames.get(frame);
    }

}
