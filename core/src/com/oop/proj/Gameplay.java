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
            case Tank.CODE:
                Tank tank = new Tank();
                loadAnimation(Tank.NAME, tank);
                unit = tank;
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

    public void render(SpriteBatch batch, float deltaTime) {
        Collections.sort(units, UNIT_COMPARATOR);
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            unit.update(deltaTime);
            if (unit instanceof AttackableUnit) {
                AttackableUnit atkUnit = (AttackableUnit) unit;
                // looking to the right
                if (unit.owner.getDirection() > 0) {
                    // first enemy to the right
                    int j;
                    for (j = i+1; j < units.size(); j++) {
                        if (units.get(j).owner != unit.owner) {
                            break;
                        }
                    }
                    if (j < units.size()) {
                        if (units.get(j).getPosition().x - unit.getPosition().x < atkUnit.attackRange) {
                            // at least one enemy is in range of attacking
                            atkUnit.stopMovingX();
                            atkUnit.startAttacking();
                        } else {
                            if (atkUnit.getIsAttacking()) {
                                atkUnit.stopAttacking();
                                atkUnit.startMovingRight();
                            }
                        }
                    } else {
                        if (atkUnit.getIsAttacking()) {
                            atkUnit.stopAttacking();
                            atkUnit.startMovingRight();
                        }
                    }
                }
                // looking to the left
                else {
                    // first enemy to the left
                    int j;
                    for (j = i-1; j >= 0; j--) {
                        if (units.get(j).owner != unit.owner) {
                            break;
                        }
                    }
                    if (j >= 0) {
                        if (unit.getPosition().x - units.get(j).getPosition().x < atkUnit.attackRange) {
                            // at least one enemy is in range of attacking
                            atkUnit.stopMovingX();
                            atkUnit.startAttacking();
                        } else {
                            if (atkUnit.getIsAttacking()) {
                                atkUnit.stopAttacking();
                                atkUnit.startMovingLeft();
                            }
                        }
                    } else {
                        if (atkUnit.getIsAttacking()) {
                            atkUnit.stopAttacking();
                            atkUnit.startMovingLeft();
                        }
                    }
                }
            }
            TextureRegion currentFrame = unit.getKeyFrame(deltaTime, true);
            batch.draw(currentFrame, unit.getDrawPosition().x, unit.getDrawPosition().y);
        }
    }

    public Side getPlayer() {
        return player;
    }
    public Side getEnemy() {
        return enemy;
    }

}
