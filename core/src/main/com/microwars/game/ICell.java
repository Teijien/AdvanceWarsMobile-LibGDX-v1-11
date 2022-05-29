package com.microwars.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public interface ICell {
    void setCell(ICell cell);

    boolean nextToCell(ICell target);

    boolean inBounds(TiledMapTileLayer tiles);

    boolean equals(ICell cell);

    int x();

    int y();
}
