package it.unibo.model.entities;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

public interface MovableEntity extends Entity {
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
