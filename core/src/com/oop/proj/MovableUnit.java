package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aum on 12/15/16.
 */
public class MovableUnit extends Unit {
    public static float GRAVITY = 10;

    protected Animation animMove;

    // pixels per sec
    protected Vector2 velocity;
    protected float speed;

    public MovableUnit () {
        velocity = Vector2.Zero.cpy();
        speed = 0f;
    }

    public void setSpeed(float spd) {
        speed = spd;
    }
    public void setAnimMove(Animation anim) {
        animMove = anim;
    }
    public void playAnimMove() {
        if (!animCurrent.equals(animMove)) {
            animCurrent = animMove;
        }
    }
    public void moveLeft() {
        playAnimMove();
        velocity.set(-speed, velocity.y);
    }
    public void moveRight() {
        playAnimMove();
        velocity.set(speed, velocity.y);
    }
    public void moveUp() {
        playAnimMove();
        velocity.set(velocity.x, speed);
    }
    public void moveDown() {
        playAnimMove();
        velocity.set(velocity.x, -speed);
    }
    public void stopMovingX() {
        playAnimIdle();
        velocity.set(0, velocity.y);
    }
    public void stopMovingY() {
        playAnimIdle();
        velocity.set(velocity.x, 0);
    }
    @Override
    public void update(float deltaTime) {
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
