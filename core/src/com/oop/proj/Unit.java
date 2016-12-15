package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aum on 12/15/16.
 */
public class Unit {
    protected Vector2 position;
    protected Animation animIdle;
    protected Animation animCurrent;

    public Unit () {
        position = Vector2.Zero.cpy();
    }

    public void playAnimIdle() {
        animCurrent = animIdle;
    }

    public void setAnimIdle(Animation anim) {
        if (animIdle == null || !animIdle.equals(anim)) {
            animIdle = anim;
        }
    }

    public TextureRegion getKeyFrame(float stateTime, boolean looping) {
        return animCurrent.getKeyFrame(stateTime, looping);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float deltaTime) {

    }
}
