package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aum on 12/15/16.
 */
public class MovableUnit extends Unit {
    public static float GRAVITY = 50;

    protected Animation animMove;

    // pixels per sec
    protected Vector2 velocity;
    protected float speed;
    protected boolean isMoving;

    public MovableUnit () {
        velocity = Vector2.Zero.cpy();
        speed = 0f;
        isMoving = false;
    }

    public void setSpeed(float spd) {
        speed = spd;
    }
    public void setAnimMove(Animation anim) {
        animMove = anim;
    }
    public void playAnimMove() {
        if (!isMoving) {
//            stateTime = 0f;
            animCurrent = animMove;
        }
    }
    public void startMovingLeft() {
        direction = -1;
        playAnimMove();
        velocity.set(-speed, velocity.y);
        isMoving = true;
    }
    public void startMovingRight() {
        direction = 1;
        playAnimMove();
        velocity.set(speed, velocity.y);
        isMoving = true;
    }
    public void startMovingUp() {
        playAnimMove();
        velocity.set(velocity.x, speed);
        isMoving = true;
    }
    public void startMovingDown() {
        playAnimMove();
        velocity.set(velocity.x, -speed);
        isMoving = true;
    }
    public void stopMovingX() {
        if (isMoving) {
            playAnimIdle();
            velocity.set(0, velocity.y);
            isMoving = Math.abs(velocity.y) > 0.001f;
        }
    }
    public void stopMovingY() {
        if (isMoving) {
            playAnimIdle();
            velocity.set(velocity.x, 0);
            isMoving = Math.abs(velocity.x) > 0.001f;
        }
    }
    public boolean getIsMoving() {
        return isMoving;
    }
//    @Override
//    public TextureRegion getKeyFrame(float stateTime, boolean looping) {
//        TextureRegion frame =  animCurrent.getKeyFrame(stateTime, looping);
//        if (frame.isFlipX() != (velocity.x < 0)) {
//            frame.flip(velocity.x < 0, false);
//        }
//        return frame;
//    }
    public void update(float deltaTime) {
        super.update(deltaTime);
        velocity.add(0, -GRAVITY * deltaTime);
        float deltaX = velocity.x * deltaTime;
        float deltaY = velocity.y * deltaTime;
        position.add(deltaX, deltaY);
        if (position.y < 0) {
            velocity.set(velocity.x, 0);
            position.set(position.x, 0);
        }
    }
}
