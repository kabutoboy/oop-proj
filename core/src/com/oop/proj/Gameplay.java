package com.oop.proj;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.Array;

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
    protected BitmapFont hpFont;
    protected BitmapFont goldFont;

    public Gameplay(TextureAtlas atl) {
        player = new Side();
        enemy = new Side();
        player.setDirection(1);
        enemy.setDirection(-1);
        player.setLife(100);
        enemy.setLife(100);
        atlas = atl;
        units = new ArrayList<Unit>();
        hpFont = new BitmapFont();
        hpFont.setColor(Color.RED);
        goldFont = new BitmapFont();
        goldFont.setColor(Color.GOLD);
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
                    unit.setPosition(new Vector2(100, 0));
                    mvUnit.startMovingRight();
                } else {
                    unit.setPosition(new Vector2(1100, 0));
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
        Array<TextureAtlas.AtlasRegion> attackFrames = atlas.findRegions(name + "_attack");
        Animation attackAnimation = new Animation(
                1f / unit.getAttackRate() / (float)attackFrames.size,
                attackFrames,
                Animation.PlayMode.LOOP);
        unit.setAnimIdle(idleAnimation);
        unit.setAnimMove(moveAnimation);
        unit.setAnimAttack(attackAnimation);
        unit.playAnimIdle();
    }

    public void render(SpriteBatch batch, float deltaTime) {
        Collections.sort(units, UNIT_COMPARATOR);
        hpFont.draw(batch, Integer.toString(player.getLife()), 10, 40);
        hpFont.draw(batch, Integer.toString(enemy.getLife()), 1000, 40);
        goldFont.draw(batch, Float.toString(player.getGold()), 10, 20);
        goldFont.draw(batch, Float.toString(enemy.getGold()), 1000, 20);
        player.updateGold(deltaTime);
        enemy.updateGold(deltaTime);
        for (int i = 0; i < units.size(); i++) {
            Unit unit = units.get(i);
            unit.update(deltaTime);
            // damage to side
            if (unit.getPosition().x < -50 || unit.getPosition().x > 1250) {
                // damage enemy
                if (unit.owner == player) {
                    enemy.subLife((int)Math.ceil(0.1*(double)unit.life));
                }
                // damage player
                else {
                    player.subLife((int)Math.ceil(0.1*(double)unit.life));
                }
                units.remove(i);
                i--;
            }
            if (unit instanceof AttackableUnit) {
                AttackableUnit atkUnit = (AttackableUnit) unit;
                boolean attackNow = (atkUnit.getAttackTime() >= atkUnit.getAttackPeriod());
                boolean atLeastOneEnemy = false;
                // looking to the right
                if (unit.owner.getDirection() > 0) {
                    // first enemy to the right
                    int j;
                    for (j = i+1; j < units.size(); j++) {
                        if (units.get(j).owner != unit.owner) {
                            if (units.get(j).getPosition().x - unit.getPosition().x < atkUnit.attackRange) {
                                atLeastOneEnemy = true;
                                // enemy is in range of attacking
                                atkUnit.stopMovingX();
                                atkUnit.startAttacking();
                                // inflict damage to enemies
                                if (attackNow) {
                                    units.get(j).takeDamage(atkUnit.attackDamage);
                                    if (units.get(j).isDead()) {
                                        units.remove(j);
                                        j--;
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    if (attackNow) {
                        atkUnit.resetAttackTime();
                    }
                    if (!atLeastOneEnemy) {
                        if (atkUnit.getIsAttacking()) {
                            atkUnit.stopAttacking();
                            atkUnit.startMovingRight();
                        }
                    }
                }
                // looking to the left
                else {
                    int j;
                    for (j = i-1; j >= 0; j--) {
                        if (units.get(j).owner != unit.owner) {
                            if (unit.getPosition().x - units.get(j).getPosition().x < atkUnit.attackRange) {
                                atLeastOneEnemy = true;
                                // enemy is in range of attacking
                                atkUnit.stopMovingX();
                                atkUnit.startAttacking();
                                // inflict damage to enemies
                                if (attackNow) {
                                    units.get(j).takeDamage(atkUnit.attackDamage);
                                    if (units.get(j).isDead()) {
                                        units.remove(j);
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    if (attackNow) {
                        atkUnit.resetAttackTime();
                    }
                    if (!atLeastOneEnemy) {
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

    public void removeUnit(Unit unit) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i) == unit) {
                units.remove(i);
                break;
            }
        }
    }

    public Side getPlayer() {
        return player;
    }
    public Side getEnemy() {
        return enemy;
    }

}
