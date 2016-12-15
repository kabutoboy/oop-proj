package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by aum on 12/15/16.
 */
public class AttackableUnit extends MovableUnit {

    protected Animation animAttack;

    protected float attackRate;
    protected int attackDamage;
    protected boolean isAttacking;

    public AttackableUnit() {
        attackRate = 0;
        attackDamage = 0;
        isAttacking = false;
    }

    public void setAnimAttack(Animation anim) {
        animAttack = anim;
    }
    public void playAnimAttack() {
        if (!animCurrent.equals(animAttack)) {
            animCurrent = animAttack;
        }
    }
    public void startAttacking() {
        isAttacking = true;
        playAnimAttack();
    }
    public void stopAttacking() {
        isAttacking = false;
        playAnimIdle();
    }
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
