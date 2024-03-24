package it.unibo.model.entities.api;

import it.unibo.model.entities.utilities.*;

/**
 * Represents an entity in the game, that is, something that has name, position and direction.
 */
public interface Entity {

    /**
     * Retrieves the entity's id.
     * 
     * @return The entity's id.
     */
    String getId();

    /**
     * Retrieves the entity's name.
     * 
     * @return The entity's name.
     */
    String getName();

    /**
     * Sets the entity's position.
     * 
     * @param coord The next position.
     */
    void setPosition(Position position);

    /**
     * Returns the current entity position.
     * 
     * @return The entity's current position.
     */
    Position getPosition();

    /**
     * Retrieves the direction the entity is facing.
     * 
     * @return The direction the entity is currently facing at.
     */
    Direction getDirection();

    /**
     * Sets the position the entity is facing towards.
     * 
     * @param direction The next direction towards which the entity will face at.
     */
    void setDirection(Direction direction);
}
