package com.advancewarsmobile.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthView {
    private final TextureRegion[] healthFrames;
    private Sprite health;

    /* int cols = # of cols of tiles in sheet
     * int rows = # of rows of tiles in sheet */
    public HealthView(Texture healthSheet, int cols, int rows) {
        TextureRegion[][] tmp = TextureRegion.split(healthSheet,
                healthSheet.getWidth() / cols,
                healthSheet.getHeight() / rows);

        healthFrames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                healthFrames[index++] = tmp[i][j];
            }
        }
        health = new Sprite(healthFrames[0]);
    }

    public Sprite sprite() {
        return health;
    }

    // int num = Number to show
    public void changeFrame(int num) {
        num = num / 10;
        if (num < 1) {
            num = 1;
        }

        Sprite tmp = new Sprite(healthFrames[10 - num]);
        tmp.setPosition(health.getX(), health.getY());
        health = tmp;
    }
}
