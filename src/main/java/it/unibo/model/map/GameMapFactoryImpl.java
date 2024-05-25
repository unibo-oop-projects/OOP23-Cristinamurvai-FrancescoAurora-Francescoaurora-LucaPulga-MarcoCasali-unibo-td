package it.unibo.model.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
    private static final String FILLER_TILE = "neutral";
    private static final String RANGE_SEPARATOR = "-";

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap fromJSON(final String source) {
        final JSONObject json = new JSONObject(source);
        final Map<Integer, Tile> tiles = new HashMap<>();
        final TileFactory tileFactory = new TileFactoryImpl();

        final int rows = json.getInt(JSON_ROWS_KEY);
        final int columns = json.getInt(JSON_COLUMNS_KEY);
        for (Object tileSet : json.getJSONArray(JSON_TILES_KEY)) {
            tiles.putAll(unpackSet((JSONObject) tileSet, columns));
        }
        
        /**
         * Filling unspecified tiles with neutral tiles to make
         * json file simpler.
        */
        for (int i = 0; i < rows * columns; i++) {
            if(!tiles.containsKey(i)) {
                tiles.put(i, tileFactory.fromName(FILLER_TILE));
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
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println("Error when retrieving json file for map : " + fileName);
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
                return this.tiles.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue);
            }

            @Override
            public Stream<Tile> getDefenseTiles() {
                return getTiles().filter(Tile::canBuild);
            }

            @Override
            public Position2D getSpawnPosition() {
                return indexToPos2D(this.tiles.entrySet().stream()
                    .filter(entry -> entry.getValue().getTileFeatures()
                    .contains(TileFeature.PATH_START)).findFirst().get().getKey());
            }

            @Override
            public Vector2D getPathDirection(final Position2D position) {
                final Set<TileFeature> directions = this.tiles.get(flatten(position)).getTileFeatures();
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

            private int flatten(final Position2D position) {
                return position.y() * this.columns + position.x();
            }

            private Position2D indexToPos2D(final int i) {
                return new Position2D(i % columns, i / this.columns);
            }
        };
    }

    private Map<Integer, Tile> unpackSet(final JSONObject json, final int columns) {
        final Map<Integer, Tile> map = new HashMap<>();
        final TileFactory tileFactory = new TileFactoryImpl();
        final String tileName = json.getString(JSON_TILE_NAME_KEY);
        final JSONArray posArray = json.getJSONArray(JSON_TILE_POSITIONS_KEY);

        for (int i = 0; i < posArray.length(); i++) {
            String tmp = posArray.getString(i);
            //horizontal range of tiles ex 1-20
            if (tmp.contains(RANGE_SEPARATOR)) {
                IntStream.rangeClosed(Integer.parseInt(tmp.split(RANGE_SEPARATOR)[0]),
                Integer.parseInt(tmp.split(RANGE_SEPARATOR)[1]))
                .forEach(e -> map.put(e, tileFactory.fromName(tileName)));
                //TODO vertical range
            } else { //singular tile
                map.put(posArray.getInt(i), tileFactory.fromName(tileName));
            }  
        }

        return map;
    }
}
