package it.unibo.model.entities;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Represents an entity in the game, that is, something that has name, position and direction.
 */
public interface Entity {
    /**
     * Entity's id.
     * @return Entity's id.
     */
    int getId();

    /**
     * Entity's name.
     * @return Entity's name.
     */
    String getName();

    /**
     * Entity's type.
     * @return Entity's type.
     */
    String getType();

    /**
     * Entity's position.
     * @return Entity's position.
     */
    Position2D getPosition();

    /**
     * Entity's direction.
     * @return Entity's direction.
     */
    Vector2D getDirection();
}
