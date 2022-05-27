package com.advancewarsmobile.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class UnitActor extends Actor implements IUnitActor {
    private final Sprite sprite;
    private final HealthView health;

    public UnitActor(Sprite sprite, HealthView health) {
        this.sprite = sprite;
        this.health = health;
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        health.sprite().draw(batch);
    }

    @Override
    public boolean addListener(EventListener listener) {
        return super.addListener(listener);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x, y);
        health.sprite().setPosition(x + 8, y);
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        sprite.setPosition(this.getX(), this.getY());
        health.sprite().setPosition(this.getX() + 8, this.getY());
    }

    public HealthView getHealthView() {
        return health;
    }
}
