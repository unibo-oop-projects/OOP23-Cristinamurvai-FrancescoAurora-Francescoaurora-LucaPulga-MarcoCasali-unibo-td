package it.unibo.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Abstract implementation of an abstract entity but movable such as an enemy, a
 * tower or a bullet.
 */
public abstract class AbstractMovableEntity extends AbstractEntity implements IMovableEntity {

    @JsonProperty("direction2d")
    private Vector2D direction2d;
    @JsonProperty("position2d")
    private Position2D position2d;

    /**
     * Constructor.
     *
     * @param id {@link AbstractMovableEntity}'s
     * @param name {@link AbstractMovableEntity}'s
     * @param type {@link AbstractMovableEntity}'s
     * @param imgPath {@link AbstractMovableEntity}'s
     * @param position2d {@link AbstractMovableEntity}'s
     * @param direction2d {@link AbstractMovableEntity}'s
     */
    public AbstractMovableEntity(final int id, final String name, final String type, final String imgPath,
            final Position2D position2d, final Vector2D direction2d) {
        super(id, name, type, imgPath);
        this.direction2d = direction2d;
        this.position2d = position2d;
    }

    /**
     * {@link AbstractMovableEntity}'s {@link Vector2D}.
     *
     * @return {@link AbstractMovableEntity}'s {@link Vector2D}.
     */
    @Override
    public Vector2D getDirection() {
        return this.direction2d;
    }

    /**
     * Modify {@link AbstractMovableEntity}'s {@link Vector2D}.
     *
     * @param direction2d {@link AbstractMovableEntity}'s {@link Vector2D} to be
     * set.
     */
    @Override
    public void setDirection(final Vector2D direction2d) {
        this.direction2d = direction2d;
    }

    /**
     * {@link AbstractMovableEntity}'s {@link Position2D}.
     *
     * @return {@link AbstractMovableEntity}'s {@link Position2D}.
     */
    @Override
    public Position2D getPosition() {
        return this.position2d;
    }

    /**
     * Modify {@link AbstractMovableEntity}'s {@link Position2D}.
     *
     * @param position2d {@link AbstractMovableEntity}'s {@link Position2D} to
     * be set.
     */
    @Override
    public void setPosition(final Position2D position2d) {
        this.position2d = position2d;
    }
}
