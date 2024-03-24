package it.unibo.model.entities.api;

import it.unibo.utilities.*;

/**
 * Represents an entity in the game, that is, something that has name, position and direction.
 */
public interface Entity {

    /**
     * Returns the entity's id.
     * 
     * @return The entity's id.
     */
    String getId();

    /**
     * Returns the entity's name.
     * 
     * @return The entity's name.
     */
    String getName();

    /**
     * Returns the current entity position.
     * 
     * @return The entity's current position.
     */
    Position2D getPosition();

    /**
     * Returns the direction the entity is facing.
     * 
     * @return The direction the entity is currently facing at.
     */
    Vector2D getDirection();


    /**
     * Returns the path of the renderable entity.
     * 
     * @return The path of the renderable entity.
     */
    String render();
}
