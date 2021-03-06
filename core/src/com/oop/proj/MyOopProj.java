package com.oop.proj;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MyOopProj extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
    Sprite background;
    Sprite towerPlayer;
    Sprite towerEnemy;

    float stateTime;
	TextureAtlas atlas;
    Gameplay game;
    Music gameMusic;

	@Override
	public void create () {
		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//        background = new Sprite(new Texture("stage.png"));
//        background.setScale(600f / background.getHeight());
//        background.setPosition(0, 0);
//        towerPlayer = new Sprite(new Texture("side_good.png"));
//        towerPlayer.setScale(0.5f);
//        towerEnemy = new Sprite(new Texture("side_bad.png"));
//        towerEnemy.setScale(0.5f);
//        towerEnemy.setPosition(300, 0);
        atlas = new TextureAtlas(Gdx.files.internal("units.atlas"));
        TextureAtlas stageAtlas = new TextureAtlas(Gdx.files.internal("stage.atlas"));
        background = stageAtlas.createSprite("background");
        background.setSize(2400, 600);
        towerPlayer = stageAtlas.createSprite("tower_player");
        TextureAtlas.AtlasRegion towerPlayerRegion = stageAtlas.findRegion("tower_player");
        towerPlayer.setPosition(-240, -100);
        towerEnemy = stageAtlas.createSprite("tower_enemy");
        towerEnemy.setPosition(460, 0);
//        Animation idleAnimation = new Animation(0.33f, atlas.findRegions("soldier_idle"), Animation.PlayMode.LOOP);
//        walkAnimation = new Animation(0.33f, atlas.findRegions("soldier_move"), Animation.PlayMode.LOOP);
//        soldier = new MovableUnit();
//        soldier.setSpeed(50);
//        soldier.setAnimIdle(idleAnimation);
//        soldier.setAnimMove(walkAnimation);
//        soldier.playAnimIdle();
//        units = new ArrayList<Unit>();
//        units.add(soldier);
        stateTime = 0f;
        game = new Gameplay(atlas);
        Gdx.input.setInputProcessor(this);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("theme_sound.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float deltaTime = Gdx.graphics.getDeltaTime();
        stateTime += deltaTime;
        batch.begin();
        background.draw(batch);
        towerPlayer.draw(batch);
        towerEnemy.draw(batch);
//        for (Unit unit: units) {
//            unit.update(deltaTime);
//            TextureRegion currentFrame = unit.getKeyFrame(stateTime, true);
//            batch.draw(currentFrame, unit.getPosition().x, unit.getPosition().y);
//        }
        //		batch.draw(img, 0, 0);
        game.render(batch, deltaTime);
        batch.end();
	}

//	boolean dragging;
    @Override public boolean mouseMoved (int screenX, int screenY) {
        // we can also handle mouse movement without anything pressed
//      camera.unproject(tp.set(screenX, screenY, 0));
        return false;
    }

	 @Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
//        if (button != Input.Buttons.LEFT || pointer > 0) return false;
//        camera.unproject(tp.set(screenX, screenY, 0));
//        dragging = true;
        return true;
    }

    @Override public boolean touchDragged (int screenX, int screenY, int pointer) {
//        if (!dragging) return false;
//        camera.unproject(tp.set(screenX, screenY, 0));
        return true;
    }

    @Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
//        if (button != Input.Buttons.LEFT || pointer > 0) return false;
//        camera.unproject(tp.set(screenX, screenY, 0));
//        dragging = false;
        return true;
    }

    @Override public boolean keyDown (int keycode) {
        switch (keycode) {
            case Input.Keys.A:
//                soldier.moveLeft();
                game.addUnit(Soldier.CODE, game.getEnemy());
                return true;
            case Input.Keys.D:
//                soldier.moveRight();
                game.addUnit(Soldier.CODE, game.getPlayer());
                return true;
            case Input.Keys.R:
                game.addUnit(Tank.CODE, game.getPlayer());
                return true;
            case Input.Keys.Q:
                game.addUnit(Tank.CODE, game.getEnemy());
                return true;
//            case Input.Keys.W:
//                soldier.moveUp();
//                return true;
//            case Input.Keys.S:
//                soldier.moveDown();
//                return true;
        }
        return false;
    }

    @Override public boolean keyUp (int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
//                soldier.stopMovingX();
                return true;
            case Input.Keys.W:
            case Input.Keys.S:
//                soldier.stopMovingY();
                return true;
        }
        return false;
    }

    @Override public boolean keyTyped (char character) {
        return false;
    }

    @Override public boolean scrolled (int amount) {
        return false;
    }
	
	@Override
	public void dispose () {
		batch.dispose();
//		img.dispose();
	}
}
