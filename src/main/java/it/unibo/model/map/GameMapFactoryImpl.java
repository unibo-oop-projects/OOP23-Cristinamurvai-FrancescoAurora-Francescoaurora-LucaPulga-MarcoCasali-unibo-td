package it.unibo.model.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import it.unibo.model.map.tile.Tile;
import it.unibo.model.map.tile.TileFactory;
import it.unibo.model.map.tile.TileFactoryImpl;
import it.unibo.model.map.tile.TileFeature;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromJSON(final String source) throws IOException {
        final JSONObject json = new JSONObject(source);
        final Map<Position2D, Tile> tiles = new HashMap<>();

        //rows
        final int rows = json.getInt(JSON_ROWS_KEY);
        //columns
        final int columns = json.getInt(JSON_COLUMNS_KEY);
        //tiles
        for (Object tileSet : json.getJSONArray(JSON_TILES_KEY)) {
            tiles.putAll(unpackSet((JSONObject) tileSet, columns));
        }

        return generic(rows, columns, tiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromJSONFile(final String fileName) throws IOException {
        final Path path = Path.of(ClassLoader.getSystemResource(MAP_RESOURCES + fileName).toString());
        return fromJSON(new String(Files.readAllBytes(path)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromName(final String name) throws IOException {
        return fromJSONFile(name + JSON_EXTENSION);
    }

    private GameMap generic(final int nRows, final int nColumns, final Map<Position2D, Tile> tilesMap) {
        return new GameMap() {
            private final Map<Position2D, Tile> tiles = tilesMap;
            private final int rows = nRows;
            private final int columns = nColumns;

            @Override
            public int getRows() {
                return this.rows;
            }

            @Override
            public int getColumns() {
                return this.columns;
            }

            @Override
            public Stream<Tile> getTiles() {
                return this.tiles.values().stream();
            }

            @Override
            public Stream<Tile> getDefenseTiles() {
                return getTiles().filter(Tile::canBuild);
            }

            @Override
            public Position2D getSpawnPosition() {
                return this.tiles.entrySet().stream()
                    .filter(entry -> entry.getValue().getTileFeatures()
                    .contains(TileFeature.PATH_START)).findFirst().get().getKey();
            }

            @Override
            public Vector2D getPathDirection(final Position2D position) {
                final Set<TileFeature> directions = this.tiles.get(edge(position)).getTileFeatures();
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

            private Position2D edge(final Position2D position) {
                return new Position2D((int) (position.x() * this.columns), (int) (position.y() * this.rows));
            }
        };
    }

    private Map<Position2D, Tile> unpackSet(final JSONObject json, final int columns) throws IOException {
        final Map<Position2D, Tile> map = new HashMap<>();
        final TileFactory tileFactory = new TileFactoryImpl();
        final String tileName = json.getString(JSON_TILE_NAME_KEY);
        final JSONArray posArray = json.getJSONArray(JSON_TILE_POSITIONS_KEY);

        for (int i = 0; i < posArray.length(); i++) {
            map.put(indexToPos2D(posArray.getInt(i), columns),
             tileFactory.fromName(tileName));
        }

        return map;
    }

    private Position2D indexToPos2D(final int i, final int columns) {
        return new Position2D(i % columns, i / columns);
    }
}
