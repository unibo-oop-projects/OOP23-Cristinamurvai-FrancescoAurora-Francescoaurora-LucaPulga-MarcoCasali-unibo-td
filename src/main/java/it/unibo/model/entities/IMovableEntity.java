package it.unibo.model.entities;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Represents a movable entity such as en {@link Enemy} or a {@link Bullet}.
 */
public interface IMovableEntity extends IEntity {

    /**
     * {@link AbstractMovableEntity}'s {@link Vector2D}.
     *
     * @return {@link AbstractMovableEntity}'s {@link Vector2D}.
     */
    Vector2D getDirection();

    /**
     * Modify {@link AbstractMovableEntity}'s {@link Vector2D}.
     *
     * @param direction2d {@link AbstractMovableEntity}'s {@link Vector2D} to be
     * set.
     */
    void setDirection(Vector2D direction2d);

    /**
     * {@link AbstractMovableEntity}'s {@link Position2D}.
     *
     * @return {@link AbstractMovableEntity}'s {@link Position2D}.
     */
    Position2D getPosition();

    /**
     * Modify {@link AbstractMovableEntity}'s {@link Position2D}.
     *
     * @param position2d {@link AbstractMovableEntity}'s {@link Position2D} to
     * be set.
     */
    void setPosition(Position2D position2d);
}
