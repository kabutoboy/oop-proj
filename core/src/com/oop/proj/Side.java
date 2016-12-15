package com.oop.proj;

import java.util.ArrayList;

/**
 * Created by aum on 12/15/16.
 */
public class Side {

    protected int life;
    protected int gold;

    protected ArrayList<Unit> units;
    protected int direction;

    public Side() {
        life = 0;
        gold = 0;
        units = new ArrayList<Unit>();
        direction = 0;
    }

    public void setDirection(int dir) {
        direction = dir;
    }
    public int getDirection() {
        return direction;
    }
    public void setLife(int lf) {
        life = lf;
    }
    public void addLife(int amnt) {
        life += amnt;
    }
    public void subLife(int amnt) {
        life -= amnt;
    }
    public int getLife() {
        return life;
    }
    public void setGold(int gd) {
        gold = gd;
    }
    public void addGold(int amnt) {
        gold += amnt;
    }
    public void subGold(int amnt) {
        gold -= amnt;
    }
    public int getGold() {
        return gold;
    }

}
