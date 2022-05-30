package com.microwars.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;

public class HealthView {
    private final TextureRegion[] healthFrames;
    private Sprite health;

    public HealthView(HealthView healthView) {
        this.healthFrames = Arrays.copyOf(healthView.getFrames(), healthView.getFrames().length);
        this.health = new Sprite(healthView.sprite());
    }

    /* int cols = # of cols of tiles in sheet
     * int rows = # of rows of tiles in sheet */
    public HealthView(Texture healthSheet, int cols, int rows) {
        healthFrames = TextureToRegion.convertToRegions(healthSheet, cols, rows);
        health = new Sprite(healthFrames[0]);
    }

    public TextureRegion[] getFrames() {
        return healthFrames;
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
