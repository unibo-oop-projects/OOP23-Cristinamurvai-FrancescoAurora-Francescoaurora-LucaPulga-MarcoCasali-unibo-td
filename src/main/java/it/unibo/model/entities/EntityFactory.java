package it.unibo.model.entities;

import java.io.IOException;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;
import java.util.Set;

/**
 * Entity factory method.
 */
public interface EntityFactory {

    <T> T loadEntity(String jsonFilePath,Position2D position2d, Vector2D direction, Class<T> entityType);

    public Set<Tower> loadAllTowers() throws IOException;
}
