package it.unibo.model.entities;

import java.io.IOException;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Entity factory method.
 */
public interface EntityFactory {

    Tower loadTower(final String jsonFilePath) throws IOException; 

    <T> T loadEntity(final String jsonFilePath, final Position2D position2d, Vector2D direction, Class<T> entityType);
}
