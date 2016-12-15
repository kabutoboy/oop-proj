package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by aum on 12/15/16.
 */
public class AttackableUnit extends MovableUnit {

    protected Animation animAttack;

    protected float attackRate;
    protected float attackPeriod;
    protected float attackRange;
    protected int attackDamage;
    protected boolean isAttacking;
    protected float attackTime;

    public AttackableUnit() {
        attackRate = 0;
        attackPeriod = 0;
        attackRange = 0;
        attackDamage = 0;
        attackTime = 0;
        isAttacking = false;
    }

    public void setAnimAttack(Animation anim) {
        if (animAttack != anim) {
            animAttack = anim;
        }
    }
    public void playAnimAttack() {
        if (!isAttacking) {
            stateTime = 0f;
            attackTime = stateTime;
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
    public float getAttackTime() {
        return stateTime - attackTime;
    }
    public void resetAttackTime() {
        attackTime = stateTime;
    }
    public float getAttackPeriod() {
        return attackPeriod;
    }
    public float getAttackRate() {
        return attackRate;
    }
    public boolean getIsAttacking() {
        return isAttacking;
    }
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
