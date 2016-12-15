package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by aum on 12/15/16.
 */
public class AttackableUnit extends MovableUnit {

    protected Animation animAttack;

    protected float attackRate;
    protected float attackRange;
    protected int attackDamage;
    protected boolean isAttacking;

    public AttackableUnit() {
        attackRate = 0;
        attackRange = 0;
        attackDamage = 0;
        isAttacking = false;
    }

    public void setAnimAttack(Animation anim) {
        if (animAttack != anim) {
            animAttack = anim;
        }
    }
    public void playAnimAttack() {
        if (!isAttacking) {
//            stateTime = 0f;
            animCurrent = animAttack;
        }
    }
    public void startAttacking() {
        playAnimAttack();
        isAttacking = true;
    }
    public void stopAttacking() {
        playAnimIdle();
        isAttacking = false;
    }
    public boolean getIsAttacking() {
        return isAttacking;
    }
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
