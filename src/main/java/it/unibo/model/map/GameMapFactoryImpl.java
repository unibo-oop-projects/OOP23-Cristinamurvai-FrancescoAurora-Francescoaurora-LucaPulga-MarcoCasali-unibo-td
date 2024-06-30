package it.unibo.model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.map.tile.TileFactory;
import it.unibo.model.map.tile.TileFactoryImpl;
import it.unibo.model.map.tile.TileFeature;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Implementation of {@link GameMapFactory}.
 */
public class GameMapFactoryImpl implements GameMapFactory {

    private static final String JSON_EXTENSION = ".json";
    private static final String MAP_RESOURCES = "maps/";
    private static final String JSON_ROWS_KEY = "rows";
    private static final String JSON_COLUMNS_KEY = "columns";
    private static final String JSON_TILES_KEY = "tiles";
    private static final String JSON_TILE_NAME_KEY = "tile";
    private static final String JSON_TILE_POSITIONS_KEY = "positions";
    private static final String JSON_FILLER_KEY = "filler";
    private static final String RANGE_SEPARATOR = "-";
    private static final String COLUMN_SEPARATOR = "/";
    private int columns;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromJSON(final String source) {
        final JSONObject json = new JSONObject(source);
        final Map<Integer, Tile> tiles = new HashMap<>();
        final TileFactory tileFactory = new TileFactoryImpl();

        final int rows = json.getInt(JSON_ROWS_KEY);
        this.columns = json.getInt(JSON_COLUMNS_KEY);
        for (final Object tileSet : json.getJSONArray(JSON_TILES_KEY)) {
            tiles.putAll(unpackSet((JSONObject) tileSet, columns));
        }

        /**
         * Filling unspecified tiles with neutral tiles to make json file
         * simpler.
         */
        for (int i = 0; i < rows * columns; i++) {
            if (!tiles.containsKey(i)) {
                tiles.put(i, tileFactory.fromName(json.getString(JSON_FILLER_KEY)));
            }
        }

        return generic(rows, columns, tiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromJSONFile(final String fileName) {
        String fileContent = null;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)))) {
            fileContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            //logger TODO
        }

        return fromJSON(fileContent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromName(final String name) {
        return fromJSONFile(MAP_RESOURCES + name + JSON_EXTENSION);
    }

    private GameMap generic(final int nRows, final int nColumns, final Map<Integer, Tile> tilesMap) {
        return new GameMap() {
            private final Map<Integer, Tile> tiles = tilesMap;
            private final int columns = nColumns;

            @Override
            public int getRows() {
                return nRows;
            }

            @Override
            public int getColumns() {
                return this.columns;
            }

            @Override
            public Stream<Tile> getTiles() {
                return this.tiles.entrySet().stream().sorted(Map.Entry.comparingByKey())
                        .map(Map.Entry::getValue);
            }

            @Override
            public Stream<Tile> getDefenseTiles() {
                return getTiles().filter(Tile::canBuild);
            }

            @Override
            public Position2D getSpawnPosition() {
                return Position2D.intToPos2D(this.tiles.entrySet().stream()
                        .filter(entry -> entry.getValue().getTileFeatures()
                        .contains(TileFeature.PATH_START)).findFirst().get().getKey(), this.columns);
            }

            @Override
            public Position2D getPathEndPosition() {
                return Position2D.intToPos2D(this.tiles.entrySet().stream()
                        .filter(entry -> entry.getValue().getTileFeatures()
                        .contains(TileFeature.PATH_END)).findFirst().get().getKey(), this.columns);
            }

            @Override
            public Vector2D getPathDirection(final Position2D position) {
                final Set<TileFeature> directions = this.tiles.get(Position2D.pos2DtoInt(position,
                        this.columns)).getTileFeatures();
                if (directions.contains(TileFeature.MOVE_DOWN)) {
                    return new Vector2D(0, -1);
                } else if (directions.contains(TileFeature.MOVE_UP)) {
                    return new Vector2D(0, 1);
                } else if (directions.contains(TileFeature.MOVE_RIGHT)) {
                    return new Vector2D(1, 0);
                } else if (directions.contains(TileFeature.MOVE_LEFT)) {
                    return new Vector2D(-1, 0);
                } else {
                    throw new IllegalStateException();
                }
            }

            @Override
            public void buildTower(final Tower tower) {
                this.tiles.get(Position2D.pos2DtoInt(tower.getPosition(), this.columns)).buildTower(tower);
            }
        };
    }

    private Map<Integer, Tile> unpackSet(final JSONObject json, final int columns) {
        final Map<Integer, Tile> map = new HashMap<>();
        final String tileName = json.getString(JSON_TILE_NAME_KEY);
        final JSONArray posArray = json.getJSONArray(JSON_TILE_POSITIONS_KEY);

        /**
         * Supports a singular tile x, a horizontal range x-y or a vertical
         * range x/y.
         */
        for (int i = 0; i < posArray.length(); i++) {
            final String tmp = posArray.getString(i);
            if (tmp.contains(RANGE_SEPARATOR)) {
                IntStream.rangeClosed(Integer.parseInt(tmp.split(RANGE_SEPARATOR)[0]),
                        Integer.parseInt(tmp.split(RANGE_SEPARATOR)[1]))
                        .forEach(e -> createTile(e, tileName, map));
            } else if (tmp.contains(COLUMN_SEPARATOR)) {
                IntStream
                        .iterate(Integer.parseInt(tmp.split(COLUMN_SEPARATOR)[0]), n -> n + columns)
                        .takeWhile(n -> n <= Integer.parseInt(tmp.split(COLUMN_SEPARATOR)[1]))
                        .forEach(e -> createTile(e, tileName, map));
            } else {
                this.createTile(posArray.getInt(i), tileName, map);
            }
        }
        return map;
    }

    /**
     * Create tile.
     *
     * @param index
     * @param name
     * @param map
     */
    private void createTile(final int index, final String name, final Map<Integer, Tile> map) {
        final TileFactory tileFactory = new TileFactoryImpl();
        final Tile t = tileFactory.fromName(name);
        t.setPosition(Position2D.intToPos2D(index, this.columns));
        map.put(index, t);
    }
}
