package it.unibo.model.entities;

import java.io.IOException;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Entity factory method.
 */
public interface EntityFactory {

    Tower loadTower(String jsonFilePath) throws IOException; 

    <T> T loadEntity(String jsonFilePath,Position2D position2d, Vector2D direction, Class<T> entityType);
}
