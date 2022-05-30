package com.microwars.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InfantryFactory {
    private Texture[] unitTextures;
    private Map gameMap;
    private BasicStats stats;
    private View view;
    private InputListener listener;
    private HealthView health;

    public InfantryFactory(Texture[] unitTextures, Map gameMap, BasicStats stats, View view,
                           InputListener listener, HealthView health) {
        this.unitTextures = unitTextures;
        this.gameMap = gameMap;
        this.stats = stats;
        this.view = view;
        this.listener = listener;
        this.health = health;
    }

    public void makeUnit(int team, int col, int row) {
        Actor actor = new UnitActor(new Sprite(unitTextures[team]), new HealthView(health));
        actor.addListener(listener);
        actor.setPosition(col * 16, (7 - row) * 16);
        view.addActor(actor);

        gameMap.setUnit(new BasicUnit(team, new BasicStats(stats)), col, row);
    }
}
