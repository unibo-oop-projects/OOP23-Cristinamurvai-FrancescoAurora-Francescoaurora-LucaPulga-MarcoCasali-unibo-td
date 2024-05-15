package it.unibo.model.map.tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONObject;
import it.unibo.model.entities.defense.tower.Tower;

/**
 * Implementation of a {@link TileFactory}.
 */
public class TileFactoryImpl implements TileFactory {
    private static final String JSON_EXTENSION = ".json";
    private static final String TILE_RESOURCES = "tiles/";
    private static final String JSON_SPRITE_KEY = "sprite";
    private static final String JSON_FEATURES_KEY = "features";

    /**
     * {@inheritDoc}
     */
    @Override
    public Tile fromJSONFile(final String fileName) throws IOException {
        final Path path = Path.of(ClassLoader.getSystemResource(TILE_RESOURCES + fileName).toString());
        return fromJSON(new String(Files.readAllBytes(path)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tile fromJSON(final String jsonString) {
        final JSONObject source = new JSONObject(jsonString);

        //sprite
        final String sprite = source.getString(JSON_SPRITE_KEY);
        //features
        final Set<TileFeature> features = source.optJSONArray(JSON_FEATURES_KEY) == null
        ? Set.of() : source.optJSONArray(JSON_FEATURES_KEY).toList().stream()
        .map(Object::toString).map(TileFeature::valueOf).collect(Collectors.toSet());

        return generic(sprite, features);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tile fromName(final String name) throws IOException {
        return fromJSONFile(name + JSON_EXTENSION);
    }

    private Tile generic(final String sprite, final Set<TileFeature> features) {
        return new Tile() {
            private final String spriteLocation = sprite;
            private Optional<Tower> tower = Optional.empty();
            private final Set<TileFeature> tileFeatures = features;

            @Override
            public Set<TileFeature> getTileFeatures() {
                return this.tileFeatures;
            }

            @Override
            public String getSprite() {
                return this.spriteLocation;
            }

            @Override
            public boolean canBuild() {
                return this.tower.isEmpty() && this.tileFeatures.contains(TileFeature.DEFENSE);
            }

            @Override
            public void buildTower(final Tower tower) {
                this.tower = Optional.of(tower);
            }

            @Override
            public void destroyTower() {
                this.tower = Optional.empty();
            }
        };
    }
}
