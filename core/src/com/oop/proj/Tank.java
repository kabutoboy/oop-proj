package com.oop.proj;

/**
 * Created by aum on 12/15/16.
 */
public class Tank extends AttackableUnit {
    public static final String NAME = "tank";
    public static final int CODE = 3;
    public static final float PRICE = 20f;

    public Tank() {
        life = 100;
        attackDamage = 30;
        attackRange = 2.0f * 100f;
        attackRate = 15f / 30f;
        speed = 1.0f * 100f;
    }
}
