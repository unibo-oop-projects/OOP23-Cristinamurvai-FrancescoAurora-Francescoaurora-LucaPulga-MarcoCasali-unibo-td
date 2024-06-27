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
     * @param <T> generic type.
     * {@link IEntity}'s @param jsonFilePath
     * {@link IEntity}'s @param position2d
     * {@link IEntity}'s @param direction
     * {@link IEntity}'s @param entityType
     * @return parsed {@link IEntity}. 
     */
    <T> T loadEntity(final String jsonFilePath, final Position2D position2d, final Vector2D direction, final Class<T> entityType);

    /**
     * Load all {@link Tower}s from JSON.
     * @return all the {@link Tower}s istances available.
     * @throws IOException signals that an I/O exception of some sort has occurred.
     */
    Set<Tower> loadAllTowers() throws IOException;

    /**
     * Load a specific {@link Tower}.
     * {@link Tower}'s @param jsonFilePath to know which file to read.
     * @return parsed {@link IEntity}.
     * @throws IOException signals that an I/O exception of some sort has occurred.
     */
    Tower loadTower(final String jsonFilePath) throws IOException;
}
