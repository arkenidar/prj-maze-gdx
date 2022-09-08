package com.arkenidar.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MazeGame extends ApplicationAdapter {
    final float spriteSize = 32;
    SpriteBatch batch;
    Texture space, wall, player, end;
    int x = 0, y = 0;
    char[][] grid;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // from GNU-Robot (size: 32x32)
        space = new Texture("-.bmp");
        wall = new Texture("W.bmp");
        player = new Texture("P.bmp");
        end = new Texture("E.bmp");


        FileHandle file = Gdx.files.internal("map01.txt");
        String text = file.readString();
        String[] lines = text.split("\n");
        grid = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            char[] row = line.toCharArray();
            grid[i] = row;
        }
    }

    @Override
    public void render() {

        int nx = x, ny = y;
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) nx--;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) nx++;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) ny--;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) ny++;

        int width = 20, height = 15; // 20x15 background

        if (nx >= 0 && nx < width &&
                ny >= 0 && ny < height) {
            if (ny < grid.length && nx < grid[0].length) {
                if (grid[ny][nx] == ' ') {
                    x = nx;
                    y = ny;
                }
            } else {
                x = nx;
                y = ny;
            }
        }
        ScreenUtils.clear(0, 1, 0, 1);
        batch.begin();

        Texture cell = space;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cell = space;
                if (j < grid.length && i < grid[0].length)
                    switch (grid[j][i]) {
                        case '#':
                            cell = wall;
                            break;
                    }
                batch.draw(cell, i * spriteSize, (height - 1 - j) * spriteSize);
            }
        }
        cell = player;
        batch.draw(cell, x * spriteSize, (height - 1 - y) * spriteSize);

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
