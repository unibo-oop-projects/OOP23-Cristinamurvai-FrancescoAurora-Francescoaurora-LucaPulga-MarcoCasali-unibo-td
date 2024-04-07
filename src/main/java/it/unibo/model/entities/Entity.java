package it.unibo.model.entities;

import it.unibo.utilities.*;

/**
 * Represents an entity in the game, that is, something that has name, position and direction.
 */
public interface Entity{

    /**
     * 
     * @return
     */
    int getId();

    /**
     * 
     * @return
     */
    String getName();

    /**
     * 
     * @return
     */
    String getType();

    /**
     * 
     * @return
     */
    Position2D getPosition();

    /**
     * 
     * @return
     */
    Vector2D getDirection();
}