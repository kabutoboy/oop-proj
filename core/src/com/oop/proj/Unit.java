package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aum on 12/15/16.
 */
public class Unit {
    public static String NAME = "null";
    public static int CODE = 0;
    public static int PRICE = 0;

    public Side owner;

    protected int life;
    protected int direction;
    protected Vector2 position;
    protected Vector2 drawOffset;
    protected Animation animIdle;
    protected Animation animCurrent;

    protected float stateTime;

    public Unit () {
        life = 1;
        direction = 1;
        position = Vector2.Zero.cpy();
        drawOffset = Vector2.Zero.cpy();
        stateTime = 0f;
    }

    public void takeDamage(int amnt) {
        life -= amnt;
        if (isDead()) {
            die();
        }
    }

    public boolean isDead() {
        return life <= 0;
    }

    protected void die() {

    }

    public void playAnimIdle() {
        if (animCurrent != animIdle) {
//            stateTime = 0f;
            animCurrent = animIdle;
        }
    }

    public void setAnimIdle(Animation anim) {
        if (animIdle == null || !animIdle.equals(anim)) {
            animIdle = anim;
        }
    }

    public TextureRegion getKeyFrame(float deltaTime, boolean looping) {
        stateTime += deltaTime;
        TextureRegion frame =  animCurrent.getKeyFrame(stateTime, looping);
        drawOffset.x = -frame.getRegionWidth() / 2f;
        if (frame.isFlipX() != (direction < 0)) {
            frame.flip(direction < 0, false);
        }
        return frame;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDrawPosition() {
        return position.cpy().add(drawOffset);
    }

    public void setPosition(Vector2 pos) {
        position.set(pos);
    }

    public void update(float deltaTime) {

    }
}
