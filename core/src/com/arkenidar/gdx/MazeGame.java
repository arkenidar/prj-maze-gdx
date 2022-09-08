package com.arkenidar.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MazeGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture space, wall, player, end;
    float x = 0, y = 0;
    final float spriteSize = 32;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // from GNU-Robot (size: 32x32)
        space = new Texture("-.bmp");
        wall = new Texture("W.bmp");
        player = new Texture("P.bmp");
        end = new Texture("E.bmp");
    }

    @Override
    public void render() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) x--;
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) x++;
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) y--;
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) y++;

        ScreenUtils.clear(0, 1, 0, 1);
        batch.begin();
        batch.draw(player, x*spriteSize, y*spriteSize);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        space.dispose();
        wall.dispose();
        end.dispose();
    }
}
