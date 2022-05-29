package com.microwars.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MapListener extends InputListener {
    private final Map map;
    private final Path path;
    private final TiledMapTileLayer tiles;
    private final IView view;

    public MapListener(Map map, Path path, TiledMapTileLayer tiles, View view) {
        super();
        this.map = map;
        this.path = path;
        this.tiles = tiles;
        this.view = view;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (pointer != 0) {
            return false;
        }

        event.getTarget()
                .moveBy(x - (event.getTarget().getWidth() / 2),
                        y - (event.getTarget().getHeight() / 2));

        path.initializeNewPath(new Cell(event, tiles));

        return true;
    }

    // Note that x and y are deltas, not positional values!
    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        Cell cell = new Cell(event, tiles);

        event.getTarget()
                .moveBy(x - (event.getTarget().getWidth() / 2),
                        y - (event.getTarget().getHeight() / 2));

        if (validateCell(cell, tiles)) { return; }

        if (map.unitHere(cell.x(), cell.y())) { return; }

        if (path.removeCellsAfter(cell)) { return; }


        if (path.getLast().nextToCell(cell)
                && map.getUnit(path.getFirst().x(), path.getFirst().y()).mov() >= path.size()) {
            path.add(cell);
            System.out.println("Added tile to path");
        } else {
            path.recalculatePath(cell, map.getUnit(path.getFirst().x(), path.getFirst().y()).mov());
        }

        System.out.println();
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Cell cell = new Cell(event, tiles);

        if (validateCell(cell, tiles)) {
            event.getTarget()
                    .setPosition(
                            path.getFirst().x() * tiles.getTileWidth(),
                            tiles.getHeight() * tiles.getTileHeight()
                                    - (path.getFirst().y() + 1) * tiles.getTileHeight()
                    );
            return;
        }

        if (!path.getFirst().equals(path.getLast())) {
            map.moveUnit(path.getFirst(), path.getLast());
            view.moveActor(path.getFirst(), path.getLast());
        }

        if (cell.nextToCell(path.getLast()) && map.unitHere(cell.x(), cell.y())) {
            map.getUnit(path.getLast().x(), path.getLast().y())
                    .attackUnit(map.getUnit(cell.x(), cell.y()));

            if (map.getUnit(cell.x(), cell.y()).hp() <= 0) {
                map.deleteUnit(cell.x(), cell.y());
                view.deleteActor(cell.x(), cell.y());
            } else {
                ((UnitActor) view.getActor(cell.x(), cell.y())).getHealthView().changeFrame(
                        map.getUnit(cell.x(), cell.y()).hp()
                );
            }

            if (map.getUnit(path.getLast().x(), path.getLast().y()).hp() <= 0) {
                map.deleteUnit(path.getLast().x(), path.getLast().y());
                view.deleteActor(path.getLast().x(), path.getLast().y());
            } else {
                ((UnitActor) view.getActor(path.getLast().x(), path.getLast().y()))
                        .getHealthView()
                        .changeFrame(
                                map.getUnit(path.getLast().x(), path.getLast().y()).hp()
                        );
            }
        }

        event.getTarget()
                .setPosition(
                        path.getLast().x() * tiles.getTileWidth(),
                        tiles.getHeight() * tiles.getTileHeight()
                                - (path.getLast().y() + 1) * tiles.getTileHeight()
                );
    }


    private boolean validateCell(Cell cell, TiledMapTileLayer tiles) {
        if (!cell.inBounds(tiles)) { return true; }
        if (cell.equals(path.getLast())) {
            System.out.println("Moved to new tile!");
        }

        return false;
    }
}