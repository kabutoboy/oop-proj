package com.oop.proj;

/**
 * Created by aum on 12/15/16.
 */
public class Soldier extends AttackableUnit {
    public static final String NAME = "soldier";
    public static final int CODE = 1;
    public static final float PRICE = 10f;

    public Soldier() {
        life = 50;
        attackDamage = 10;
        attackRange = 1.5f * 100f;
        attackRate = 10f / 10f;
        speed = 1.5f * 100f;
    }
}
