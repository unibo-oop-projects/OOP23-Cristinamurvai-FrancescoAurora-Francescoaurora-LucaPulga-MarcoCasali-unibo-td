package it.unibo.model.entities;

import java.io.IOException;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Entity factory method.
 */
public interface EntityFactory {

    /**
     * Load a generic {@link IEntity}.
     *
     * @param <T> generic type. {@link IEntity}'s
     * @param jsonFilePath {@link IEntity}'s
     * @param position2d {@link IEntity}'s
     * @param direction {@link IEntity}'s
     * @param entityType {@link IEntity}'s
     * @return parsed {@link IEntity}.
     */
    <T> T loadEntity(String jsonFilePath, Position2D position2d, Vector2D direction, Class<T> entityType);

    /**
     * Load all {@link Tower}s from JSON.
     *
     * @return all the {@link Tower}s istances available.
     * @throws IOException signals that an I/O exception of some sort has
     * occurred.
     */
    Set<Tower> loadAllTowers() throws IOException;

    /**
     * Load a specific {@link Tower}.
     *
     * @param jsonFilePath {@link Tower}'s to know which file to read.
     *
     * @return parsed {@link IEntity}.
     * @throws IOException signals that an I/O exception of some sort has
     * occurred.
     */
    Tower loadTower(String jsonFilePath) throws IOException;
}
