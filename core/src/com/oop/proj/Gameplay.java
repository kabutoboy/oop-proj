package com.oop.proj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aum on 12/15/16.
 */
public class Gameplay {

    protected static UnitComparator UNIT_COMPARATOR = new UnitComparator();

    protected Side player;
    protected Side enemy;

    protected ArrayList<Unit> units;

    protected TextureAtlas atlas;

    public Gameplay(TextureAtlas atl) {
        player = new Side();
        enemy = new Side();
        player.setDirection(1);
        enemy.setDirection(-1);
        atlas = atl;
        units = new ArrayList<Unit>();
    }

    public void addUnit(int code, Side buyer) {
        Unit unit = null;
        switch (code) {
            case Soldier.CODE:
                Soldier soldier = new Soldier();
                loadAnimation(Soldier.NAME, soldier);
                unit = soldier;
                break;
        }
        if (null != unit) {
            unit.owner = buyer;
            units.add(unit);
            if (unit instanceof MovableUnit) {
                MovableUnit mvUnit = (MovableUnit)unit;
                if (buyer.getDirection() > 0) {
                    unit.setPosition(new Vector2(0, 0));
                    mvUnit.startMovingRight();
                } else {
                    unit.setPosition(new Vector2(700, 0));
                    mvUnit.startMovingLeft();
                }
            }
        }
    }

    protected void loadAnimation(String name, AttackableUnit unit) {
        Animation idleAnimation = new Animation(
                0.33f,
                atlas.findRegions(name + "_idle"),
                Animation.PlayMode.LOOP);
        Animation moveAnimation = new Animation(
                0.33f,
                atlas.findRegions(name + "_move"),
                Animation.PlayMode.LOOP);
        Animation attackAnimation = new Animation(
                0.33f,
                atlas.findRegions(name + "_attack"),
                Animation.PlayMode.LOOP);
        unit.setAnimIdle(idleAnimation);
        unit.setAnimMove(moveAnimation);
        unit.setAnimAttack(attackAnimation);
        unit.playAnimIdle();
    }

    public void render(SpriteBatch batch, float stateTime, float deltaTime) {
        Collections.sort(units, UNIT_COMPARATOR);
        for (Unit unit: units) {
            unit.update(deltaTime);
            TextureRegion currentFrame = unit.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, unit.getPosition().x, unit.getPosition().y);
        }
    }

    public Side getPlayer() {
        return player;
    }
    public Side getEnemy() {
        return enemy;
    }

}
