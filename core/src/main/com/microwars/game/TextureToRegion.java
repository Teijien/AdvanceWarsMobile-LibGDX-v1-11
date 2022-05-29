package com.microwars.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureToRegion {
    public static TextureRegion[] convertToRegions(Texture texture, int cols, int rows) {
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / cols,
                texture.getHeight() / rows);

        TextureRegion[] regions = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regions[index++] = tmp[i][j];
            }
        }
        
        return regions;
    }
}
