package it.unibo.model.entities;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * .
 */
public interface IMovableEntity extends IEntity {

    /**
     * Entity's direction.
     *
     * @return Entity's direction.
     */
    Vector2D getDirection();

    /**
     * Modify Entity's direction.
     *
     * @param direction2d
     */
    void setDirection(Vector2D direction2d);

    Position2D getPosition();

    /**
     * Modify Entity's direction.
     *
     * @param direction2d
     */
    void setPosition(Position2D position2d);

}
