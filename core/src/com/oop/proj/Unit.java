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
    protected Vector2 position;
    protected Animation animIdle;
    protected Animation animCurrent;

    public Unit () {
        life = 1;
        position = Vector2.Zero.cpy();
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

    public void setPosition(Vector2 pos) {
        position.set(pos);
    }

    public void update(float deltaTime) {

    }
}
